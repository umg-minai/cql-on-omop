package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.opencds.cqf.cql.engine.debug.DebugResult;
import org.opencds.cqf.cql.engine.execution.ExpressionResult;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

    BasicResultSink(final List<String> resultNames) {
        this.resultNames = resultNames.stream().map(Pattern::compile).toList();
    }

    @Override
    public ResultInfo processResults(final Map<Object, MapReduceEngine.Outcome> intermediateResults) {
        final var debugResult = new DebugResult();
        final int[] counts = {0, 0};
        intermediateResults.forEach((contextObject, expressionResult) -> {
            if (expressionResult instanceof MapReduceEngine.Outcome.Success success) {
                processSuccess(contextObject, success);
                augmentDebugResult(debugResult, contextObject, success);
                counts[SUCCESS]++;
            } else if (expressionResult instanceof MapReduceEngine.Outcome.Failure failure) {
                processFailure(contextObject, failure);
                counts[FAILURE]++;
            }
        });
        return new ResultInfo(debugResult, counts);
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

    protected DebugResult augmentDebugResult(final DebugResult debugResult,
                                             final Object contextObject,
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

    protected void processFailure(final Object contextObject, final MapReduceEngine.Outcome.Failure failure) {
    }

}
