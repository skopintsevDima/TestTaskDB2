package com.skopincev.testtaskdb2.resolver;

/**
 * Created by skopi on 23.07.2017.
 */

public class TimeResolver {

    public static String getTimeDifferenceByOneHours(long startTime, long endTime) {
        //milliseconds
        long different = endTime - startTime;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String time = "0 hr";

        if (elapsedDays == 0){
            if (elapsedHours == 0){
                if (elapsedMinutes == 0){
                    time = String.format("%d s%n", elapsedSeconds);
                } else {
                    time = String.format("%d m%n", elapsedMinutes);
                }
            }
            else {
                time = String.format("%d hr%n", elapsedHours);
            }
        } else {
            time = String.format("%d d%n", elapsedDays);
        }

        return time;
    }
}
