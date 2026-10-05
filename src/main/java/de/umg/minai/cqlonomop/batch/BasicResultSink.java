package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.opencds.cqf.cql.engine.debug.DebugResult;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;
import org.opencds.cqf.cql.engine.runtime.Interval;
import org.opencds.cqf.cql.engine.runtime.Tuple;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

/**
 * This class provides a basic {@link ResultSink} implementation with protected methods than can be overridden in
 * subclasses to change or extend the basic behavior.
 * <br/>
 * The default behavior provided by this class consists in collecting the information required for returning a
 * correctly populated {@link ResultSink.ResultInfo} instance. In addition, a list of expression result names
 * supplied to the constructor is used to call {@link #processExpressionResult} for expression results for the named
 * expressions. A subclass can override {@link #processExpressionResult} to process those results in some way.
 * <br/>
 * Further customization can be achieved by overriding the methods {@link #processResults},
 * {@link #processSuccess}, {@link #processFailure} and {@link #augmentDebugResult}.
 */
public class BasicResultSink implements ResultSink {

    protected final List<Pattern> resultNames;

    protected final DebugResult debugResult = new DebugResult();

    protected final int[] counts = {0, 0};

    BasicResultSink(final List<String> resultNames) {
        this.resultNames = resultNames.stream().map(Pattern::compile).toList();
    }

    // Intermediate results

    @Override
    public MapReduceEngine.Outcome processIntermediateResult(final Object contextObject,
                                                             final MapReduceEngine.Outcome intermediateResult) {
        if (intermediateResult instanceof MapReduceEngine.Outcome.Success success) {
            processIntermediateSuccess(contextObject, success);
            // Integrate information from debug result into the global DebugResult instance, then remove the debug
            // result.
            synchronized (this) {
                augmentDebugResult(debugResult, contextObject, success);
                this.counts[SUCCESS]++;
            }
            success.result().setDebugResult(null);
            // Detach all objects that are part of expression results in the evaluation result from the evaluator
            // state.
            final var expressionResults = success.result().expressionResults;
            final var newExpressionResults = new HashMap<String, ExpressionResult>();
            expressionResults.forEach((expressionName, expressionResult)
                    -> newExpressionResults.put(expressionName, detachExpressionResult(expressionResult)));
            expressionResults.clear();
            expressionResults.putAll(newExpressionResults);
        } else if (intermediateResult instanceof MapReduceEngine.Outcome.Failure failure) {
            processIntermediateFailure(contextObject, failure);
            synchronized (this) {
                this.counts[FAILURE]++;
            }
        }
        return intermediateResult;
    }

    protected void processIntermediateSuccess(final Object ignoredContextObject,
                                              final MapReduceEngine.Outcome.Success ignoredIntermediateSuccess) {
    }

    protected void processIntermediateFailure(final Object ignoredContextObject,
                                              final MapReduceEngine.Outcome.Failure ignoredIntermediateSuccess) {
    }

    protected DebugResult augmentDebugResult(final DebugResult debugResult,
                                             final Object ignoredContextObject,
                                             final MapReduceEngine.Outcome.Success success) {
        final var oneDebugResult = success.result().getDebugResult();
        if (oneDebugResult != null) {
            final var profile = oneDebugResult.getProfile();
            if (profile != null) {
                debugResult.ensureProfile().merge(profile);
            }
        }
        return debugResult;
    }

    protected ExpressionResult detachExpressionResult(final ExpressionResult expressionResult) {
        return new ExpressionResult(detachObject(expressionResult.value()), Set.of());
    }

    protected Object detachObject(final Object object) {
        if (object instanceof Tuple tuple) {
            final var elements = new LinkedHashMap<String, Object>();
            tuple.getElements().forEach((key, value) -> elements.put(key, detachObject(value)));
            return new Tuple().withElements(elements);
        } else if (object instanceof Iterable<?> iterable) {
            return StreamSupport.stream(iterable.spliterator(), false)
                    .map(this::detachObject)
                    .toList();
        } else if (object instanceof Interval interval) {
            // Objects that can occur as interval end points do not need detaching.
            return new Interval(interval.getLow(),
                    interval.getLowClosed(),
                    interval.getHigh(),
                    interval.getHighClosed());
        } else {
            return object;
        }
    }

    // Final results

    @Override
    public ResultSink.ResultInfo processResults(final Map<Object, MapReduceEngine.Outcome> intermediateResults) {
        intermediateResults.forEach((contextObject, intermediateResult) -> {
            if (intermediateResult instanceof MapReduceEngine.Outcome.Success success) {
                processSuccess(contextObject, success);
            } else if (intermediateResult instanceof MapReduceEngine.Outcome.Failure failure) {
                processFailure(contextObject, failure);
            }
        });
        return new ResultInfo(this.debugResult, this.counts);
    }

    protected void processSuccess(final Object contextObject, final MapReduceEngine.Outcome.Success success) {
        final var result = success.result();
        if (this.resultNames == null) {
            success.result().expressionResults.forEach((name, expressionResult) ->
                    processExpressionResult(contextObject, name, expressionResult));
        } else {
            result.expressionResults.forEach((name, value) -> {
                if (value != null
                        && this.resultNames.stream().anyMatch(pattern -> pattern.matcher(name).matches())) {
                    processExpressionResult(contextObject, name, value);
                }
            });
        }
    }

    protected void processExpressionResult(final Object contextObject,
                                           final String name,
                                           final ExpressionResult expressionResult) {
    }

    protected void processFailure(final Object ignoredContextObject,
                                  final MapReduceEngine.Outcome.Failure ignoredFailure) {
    }

}
