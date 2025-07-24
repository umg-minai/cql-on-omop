package de.umg.minai.cqlonomop.engine;

import org.cqframework.cql.cql2elm.LibrarySourceProvider;
import org.hl7.elm.r1.FunctionDef;
import org.hl7.elm.r1.VersionedIdentifier;
import org.opencds.cqf.cql.engine.debug.DebugResult;
import org.opencds.cqf.cql.engine.execution.Cache;
import org.opencds.cqf.cql.engine.execution.EvaluationResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This primary purpose of this class is performing CQL evaluations for multiple CQL context bindings independently
 * (and in parallel) and allowing clients to process (map) and aggregate (reduce) the resulting
 * {@link EvaluationResult} objects.
 */
public class MapReduceEngine extends CQLonOMOPEngine {

    public static final String UNFILTERED_CONTEXT_KEY = "Unfiltered Context";

    /**
     * Instances of classes which implement this interface contain information about a successful or failed CQL
     * evaluation for a single context object.
     */
    public sealed interface Outcome {

        record Success(EvaluationResult result) implements Outcome {}

        record Failure(Exception error) implements Outcome {}

        static Outcome success(final EvaluationResult value) {
            return new Success(value);
        }

        static Outcome failure(final Exception error) {
            return new Failure(error);
        }

    }

    // The number of threads that should be used for parallel evaluation.
    private final Integer threadCount;

    private ExecutorService executorService = null;

    public MapReduceEngine(final Configuration configuration,
                           final List<LibrarySourceProvider> additionalLibrarySourceProviders) {
        super(configuration, additionalLibrarySourceProviders);
        this.threadCount = configuration.getThreadCount();
    }

    public MapReduceEngine(final Configuration configuration) {
        this(configuration, List.of());
    }

    private ExecutorService ensureThreadPool() {
        if (this.executorService == null) {
            this.executorService = this.threadCount != null
                    ? Executors.newWorkStealingPool(this.threadCount)
                    : Executors.newWorkStealingPool();
        }
        return this.executorService;
    }

    private <R> R internalEvaluate(final String library,
                                   final Object contextObject,
                                   final Map<String, Object> parameterBindings,
                                   final Cache initialCache,
                                   final Function<Outcome, R> continuation) {
        try {
            return withEvaluationResult(library, contextObject, parameterBindings, initialCache,
                    result -> continuation.apply(Outcome.success(result)));
        } catch (final Exception e) {
            return continuation.apply(Outcome.failure(e));
        }
    }

    /**
     * Evaluate {@code library} for each CQL context binding specified by {@code contextObject}, that is, if
     * {@code contextObject} is iterable, evaluate {@code library} once for each of the elements. The evaluations are
     * independent of each other. Each evaluation result, together with the context object for which the result was
     * produced, is passed to {@code mapper} which must return an intermediate value. After all evaluations have
     * finished, {@code reducer} is called with a map that maps each context object to the respective intermediate
     * value produced by {@code mapper}. {@code reducer} has to compute and return a single object of type {@code R}
     * which becomes the return value of the function.
     *
     * @param library The name of a CQL library which should be evaluated for each context object.
     * @param contextObject A single context object or a collection of context objects. The latter is assumed when
     *                      the object is iterable.
     * @param parameterBindings Either {@code null} or a map from CQL parameter names to values for those parameters.
     * @param initialCache If non-{@code null}, the entries of the supplied cache are copied into the cache of the CQL
     *                     engine before the evaluation of {@code library} starts.
     * @param mapper A function which computes an intermediate result of type {@code I} from an {@link Outcome}
     *               instance. There are no restrictions on the computed value except that the {@code reducer} function
     *               has to be able to process it. If the {@code mapper} function returns {@code null}, the outcome
     *               will not be considered in the computation of the final result.
     *               <br/>
     *               The function may be called from arbitrary threads and multiple calls may happen in parallel.
     * @param reducer A function which computes the final result of type {@code R} from a map of context objects to
     *                intermediate results. This function called from the same thread that called the surrounding
     *                {@code evaluateLibraryMapReduce} function.
     * @return The reduce value computed by {@code reducer}.
     * @param <I> The type of intermediate results produced by {@code mapper} and consumed by @{code reducer}. The
     *            caller chooses this type through its choice of {@code mapper} and {@code reducer}.
     * @param <R> The type of the overall result returned by {@code reducer}. The caller chooses this type through its
     *            choice of {@code reducer}.
     */
    public <I, R> R evaluateLibraryMapReduce(final String library,
                                             final Object contextObject,
                                             final Map<String, Object> parameterBindings,
                                             final Cache initialCache,
                                             final BiFunction<Object, Outcome, I> mapper,
                                             final Function<Map<Object, I>, R> reducer) {
        if (contextObject instanceof Iterable<?> iterable) {
            // Count objects.
            int i = 0;
            for (var ignored : iterable) { i++; }
            final var objectCount = i;
            // Prepare thread pool, termination and intermediate object collection.
            final var progress = new Semaphore(0);
            final var intermediateResults = new ConcurrentHashMap<Object, I>();
            final var executor = ensureThreadPool();
            try {
                iterable.forEach(oneContextObject ->
                        executor.submit(() -> {
                            internalEvaluate(library,
                                    oneContextObject,
                                    parameterBindings,
                                    initialCache,
                                    outcome -> {
                                        final var intermediateResult = mapper.apply(oneContextObject, outcome);
                                        if (intermediateResult != null) {
                                            intermediateResults.put(oneContextObject, intermediateResult);
                                        }
                                        return intermediateResult;
                                    });
                            progress.release();
                        }));
                progress.acquire(objectCount);
            } catch (InterruptedException _e) {
                executor.shutdownNow();
                boolean success = false;
                try {
                    success = executor.awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException ignored) {
                }
                if (!success) {
                    System.out.println("Cancellation sent; waiting for threads to finish ...");
                    try {
                        success = executor.awaitTermination(30, TimeUnit.SECONDS);
                    } catch (InterruptedException ignored) {
                    }
                    if (!success) {
                        System.out.println("Threads did not finish");
                    }
                }
                throw new RuntimeException("Evaluation interrupted");
            }
            return reducer.apply(intermediateResults);
        } else {
            // Roundabout creation and population of the map is due to the fact that it has to be mutable.
            final var intermediate = new HashMap<Object, I>();
            return internalEvaluate(library, contextObject, parameterBindings, initialCache, outcome -> {
                intermediate.put(contextObject, mapper.apply(contextObject, outcome));
                return reducer.apply(intermediate);
            });
        }
    }

    public <I, R> R prepareAndEvaluateLibraryMapReduce(final String libraryName,
                                                       final Object contextObject,
                                                       final Map<String, Object> parameterBindings,
                                                       final BiFunction<Object, Outcome, I> mapper,
                                                       final Function<Map<Object, I>, R> reducer) {
        // Compile library. The compilation results will be
        // cached. This helps with parallel evaluation since compilation
        // would happen at the start of each task otherwise.
        //
        // Also, if the compilation fails, an exception is thrown here
        // and evaluation is aborted.
        final var libraries = prepareLibrary(new VersionedIdentifier().withId(libraryName));

        // Within libraries, collect expression definitions which are defined in the "Unfiltered" context. Evaluate
        // these definitions once now (before the parallelized processing starts) and put the results into the CQL
        // engine cache so that they are available to each of the parallel evaluations.
        final var unfilteredResult = new EvaluationResult();
        final var unfilteredEngine = withEngineSession(session -> {
            final var engine = session.cqlEngine();
            engine.getCache().setExpressionCaching(true);
            libraries.forEach(library -> {
                if (library.getStatements() != null) {
                    // Collect expression (but not function) definitions that are defined in the "Unfiltered" context.
                    final var expressions = new HashSet<String>();
                    library.getStatements().getDef().forEach(definition -> {
                        if (!(definition instanceof FunctionDef) && definition.getContext().equals("Unfiltered")) {
                            expressions.add(definition.getName());
                        }
                    });
                    // Evaluate those expressions and store the results, qualified by the library name, into the
                    // overall result  unfilteredResult.
                    final var result = engine.evaluate(library.getIdentifier(), expressions);
                    result.expressionResults.forEach((expression, expressionResult) -> {
                        final var key = String.format("%s.%s", library.getIdentifier().getId(), expression);
                        unfilteredResult.expressionResults.put(key, expressionResult);
                    });
                    // Merge any message and/or profile data that were generated into the debug result of the overall
                    // result unfilteredResult.
                    final var debugResult = result.getDebugResult();
                    if (debugResult != null) {
                        var mergedDebugResult = unfilteredResult.getDebugResult();
                        if (mergedDebugResult == null) {
                            mergedDebugResult = new DebugResult();
                            unfilteredResult.setDebugResult(mergedDebugResult);
                        }
                        mergedDebugResult.getMessages().addAll(debugResult.getMessages());
                        final var profile = debugResult.getProfile();
                        if (profile != null) {
                            mergedDebugResult.ensureProfile().merge(profile);
                        }
                    }
                }
            });
            return engine;
        });
        final var unfilteredIntermediate = mapper.apply(null, Outcome.success(unfilteredResult));
        final var unfilteredCache = unfilteredEngine.getCache();
        final Function<Map<Object, I>, R> wrappedReducer = (intermediate) -> {
            intermediate.put(UNFILTERED_CONTEXT_KEY, unfilteredIntermediate);
            return reducer.apply(intermediate);
        };
        // Start the (potentially) parallel evaluation of libraryName for all context objects. The evaluation results
        // produced above are used in two ways: 1) to pre-fill the expression cache of the CQL engine 2) the
        // caller-supplied reducer function is augmented so that unfilteredResult is merged into the final overall
        // result.
        return evaluateLibraryMapReduce(libraryName,
                contextObject,
                parameterBindings,
                unfilteredCache,
                mapper,
                wrappedReducer);
    }

}
