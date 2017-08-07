package com.skopincev.testtaskdb2.resolver;

/**
 * Created by skopi on 23.07.2017.
 */

public class TimeResolver {

    public static final long MINUTES_PER_DAY = 1440;
    private static final long secondsInMilli = 1000;
    private static final long minutesInMilli = secondsInMilli * 60;
    private static final long hoursInMilli = minutesInMilli * 60;
    private static final long daysInMilli = hoursInMilli * 24;

    public static long getTimeDifferenceByMinutes(long startTime, long endTime) {
        //milliseconds
        long different = endTime - startTime;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;

        return elapsedMinutes;
    }
}
