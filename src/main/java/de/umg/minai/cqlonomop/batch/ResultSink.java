package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.opencds.cqf.cql.engine.debug.DebugResult;

import java.util.Map;

/**
 * Implementations of this interface receive intermediate results produced by a {@link MapReduceEngine}, optionally
 * perform some side effects, and finally return a {@link ResultInfo} instance that summarizes the intermediate
 * results. The summary is in terms numbers of success and failure results as well as, optionally, a merged
 * {@link DebugResult} for all evaluations.
 * <br/>
 * The above processing happens in two phases
 * <ol>
 *     <li>
 *         In the first phase, intermediate results are reported as they arise via calls of the
 *         {@link processIntermediateResult} method. This phase can be used to collect information from the results
 *         as well as modify the results in some way. One example of such a modification would be to remove certain
 *         parts of the results to save memory.
 *     </li>
 *     <li>
 *         In the second phase, all computed intermediate results are submitted at once via a call of the
 *         {@link processResults} method. This method call is responsible for producing the final {@link ResultInfo}
 *         object.
 *     </li>
 * </ol>
 */
public interface ResultSink {

    int SUCCESS = 0;
    int FAILURE = 1;

    /**
     * A summary of intermediate results: counts of success and failure results in {@code outcomeCounts} as well as
     * an optional {@link DebugResult} in {@code debugResult}.
     *
     * @param debugResult Either a {@link DebugResult} instance which consists of merged information from debug
     *                    results of all evaluations or {@code null}.
     * @param outcomeCounts Counts of success results via {@code outcomeCount[SUCCESS]} and failure results via
     *                      {@code outcomeCount[FAILURE]}.
     */
    record ResultInfo(DebugResult debugResult, int[] outcomeCounts) {

        /**
         * Indicates whether the result info represents a successful operation overall.
         *
         * @return true if the result info does not represent any failures.
         */
        public boolean isSuccess() {
            return this.outcomeCounts()[FAILURE] == 0;
        }

    }

    /**
     * Process the intermediate result {@param intermediateResult} for {@param contextObject} and return the processed
     * object or a new object.
     * <br/>
     * This method is intended to either destructively modify {@param intermediateResult}, for example to safe memory by
     * removing aspects that are no longer required, replace the object entirely or extract information from the object
     * before the whole map-reduce computation finishes.
     *
     * @param contextObject The CQL context object for which the intermediate result has been computed.
     * @param intermediateResult The computed intermediate result.
     * @return Either the modified {@param intermediateResult} or new object of the same type.
     */
    default MapReduceEngine.Outcome processIntermediateResult(Object contextObject,
                                                              MapReduceEngine.Outcome intermediateResult) {
        return intermediateResult;
    }

    /**
     * Process all results after the map-reduce computation has finished and return aggregated information.
     *
     * @param intermediateResults All results represented as a map from CQL context object to respective result object.
     * @return Aggregated result information, including the counts of success and failure results and optionally a debug
     *         result.
     */
    ResultInfo processResults(Map<Object, MapReduceEngine.Outcome> intermediateResults);

}
