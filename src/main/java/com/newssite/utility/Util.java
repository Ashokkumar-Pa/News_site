package com.newssite.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String getDifference(Timestamp UploadDT)
    {
        if (UploadDT == null)
        {
            return "time?.";
        }
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        long time_difference = date.getTime() - UploadDT.getTime();
//        System.out.println("current time ::  " + formatter.format(date));
//        System.out.println("DB time ::  " + formatter.format(UploadDT));
        long days_difference = (time_difference / (1000*60*60*24)) % 365;
        long years_difference = (time_difference / (1000L *60*60*24*365));
        long seconds_difference = (time_difference / 1000)% 60;
        long minutes_difference = (time_difference / (1000*60)) % 60;
        long hours_difference = (time_difference / (1000*60*60)) % 24;
        if (years_difference >= 1)
        {
            System.out.println("Year condition  " + years_difference);
            if (years_difference > 1)
            {
                System.out.println("more then 2 years");
                return years_difference + " years";
            }
            else {
                System.out.println("less then 1 years");
                return years_difference + " Year";
            }
        }
        else if (days_difference >= 1)
        {
            System.out.println("days condition  " + days_difference);
            if (days_difference > 30)
            {
                System.out.println("months condition");
                return days_difference/30 +" months";
            }
            else if (days_difference > 1)
            {
                System.out.println("days more then 1 condition");
                return days_difference + " days";
            }
            else {
                System.out.println("....day condition");
                return days_difference + " day";
            }
        }
        else if (hours_difference >= 1)
        {
            System.out.println("hours condition  " + hours_difference);
            return hours_difference + " Hours";
        }
        else if (minutes_difference >= 1)
        {
            System.out.println("min condition  " + minutes_difference);
            return minutes_difference + " Mins";
        }
        System.out.println("Sec  " + seconds_difference);
        return seconds_difference + " Secs";
    }
}
