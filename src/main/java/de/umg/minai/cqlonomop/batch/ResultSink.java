package de.umg.minai.cqlonomop.batch;

import de.umg.minai.cqlonomop.engine.MapReduceEngine;
import org.opencds.cqf.cql.engine.debug.DebugResult;

import java.util.Map;

/**
 * Implementations of this interface receive intermediate results produced by a {@link MapReduceEngine}, optionally
 * perform some side effect, and finally return a {@link ResultInfo} instance. that summarizes the intermediate
 * results. The summary is in terms numbers of success and failure results as well as, optionally, a merged
 * {@link DebugResult} for all evaluations.
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

    ResultInfo processResults(Map<Object, MapReduceEngine.Outcome> intermediateResults);

}
