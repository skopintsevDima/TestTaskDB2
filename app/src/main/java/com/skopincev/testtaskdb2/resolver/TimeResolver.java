package com.skopincev.testtaskdb2.resolver;

import java.util.Calendar;

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

    public static String getTimeOfDayByMilli(long timeInMilli) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(timeInMilli);
        String hours = cl.get(Calendar.HOUR_OF_DAY) > 9 ?
                String.valueOf(cl.get(Calendar.HOUR_OF_DAY)) :
                "0" + String.valueOf(cl.get(Calendar.HOUR_OF_DAY));
        String minutes =  cl.get(Calendar.MINUTE) > 9 ?
                String.valueOf(cl.get(Calendar.MINUTE)) :
                "0" + String.valueOf(cl.get(Calendar.MINUTE));
        String timeOfDay = "" + hours + ":" + minutes;
        return timeOfDay;
    }
}
