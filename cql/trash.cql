// Trash

// Interesting function:
// expand ([Measurement] m return Interval[m.measurementDatetime, m.measurementDatetime + 1 'second']) per day

    /* aggregate Result starting ({previous: null , intervals: {}}): if Result.previous is null then */
    /*   { previous: {time: Interval[e.time, e.time], good: e.good}, intervals: Result.intervals } */
    /* else if ((Result.previous as Tuple{time Interval<DateTime>, good Boolean}).good = e.good) then */
    /*   { previous: {time: Interval[start of (Result.previous as Tuple{time Interval<DateTime>, good Boolean}).time, e.time], good: e.good}, */
    /*     intervals: Result.intervals } */
    /* else */
    /*   { previous:  {time: Interval[e.time, e.time], good: e.good}, */
    /*     intervals: {{time:Interval[start of (Result.previous as Tuple{time Interval<DateTime>, good Boolean}).time, e.time], */
    /*                  good:(Result.previous as Tuple{time Interval<DateTime>, good Boolean}).good}} */
    /*                union Result.intervals }).intervals) y */

// Interval[m.measurementDatetime, m.measurementDatetime + 1 's'] sort by

/* define function DayCoverage(Intervals List<Interval<DateTime>>): */
/*   ( */
/*     Flatten((expand Intervals per day) i */
/*               return { Interval[(date from start of i), */
/*                                 (date from start of i) + 1 day], */
/*                        Interval[(date from end   of i), */
/*                                 (date from end   of i) + 1 day] }) */
/*     union { Last((Intervals) i */
/*                    return Interval[date from end of i, */
/*                                    (date from end of i) + 1 day] */
/*                    sort by high) } */
/*   ) d sort by low */
