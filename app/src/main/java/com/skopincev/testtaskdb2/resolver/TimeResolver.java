package com.skopincev.testtaskdb2.resolver;

import java.util.Calendar;

/**
 * Created by skopi on 23.07.2017.
 */

public class TimeResolver {

    public interface DATE_TYPE{
        String NONE = "";
        String TODAY = "Today";
        String YESTERDAY = "Yesterday";
        String DATE = "Date";
    }

    public static final long SECONDS_IN_MILLI = 1000;
    public static final long MINUTES_IN_MILLI = SECONDS_IN_MILLI * 60;
    public static final long HOURS_IN_MILLI = MINUTES_IN_MILLI * 60;
    public static final long DAYS_IN_MILLI = HOURS_IN_MILLI * 24;

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

    public static String getDateByMilli(long timeInMilli) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(timeInMilli);
        String day = cl.get(Calendar.DAY_OF_MONTH) > 9 ?
                String.valueOf(cl.get(Calendar.DAY_OF_MONTH)) :
                "0" + String.valueOf(cl.get(Calendar.DAY_OF_MONTH));
        String month = cl.get(Calendar.MONTH) > 9 ?
                String.valueOf(cl.get(Calendar.MONTH)) :
                "0" + String.valueOf(cl.get(Calendar.MONTH));
        String year = cl.get(Calendar.YEAR) > 9 ?
                String.valueOf(cl.get(Calendar.YEAR)) :
                "0" + String.valueOf(cl.get(Calendar.YEAR));
        String date = "" + day + ":" + month + ":" + year;
        return date;
    }

    public static String getMessageDateByDifference(long difference) {
        String date;

        if (difference >= 2 * DAYS_IN_MILLI)
            date = DATE_TYPE.DATE;
        else if (difference < 2 * DAYS_IN_MILLI && difference > DAYS_IN_MILLI)
            date = DATE_TYPE.YESTERDAY;
        else if (difference != 0)
            date = DATE_TYPE.TODAY;
        else
            date = DATE_TYPE.NONE;

        return date;
    }
}
