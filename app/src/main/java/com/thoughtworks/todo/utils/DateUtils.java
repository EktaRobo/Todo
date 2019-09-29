package com.thoughtworks.todo.utils;

import android.annotation.SuppressLint;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



@SuppressLint("SimpleDateFormat")
public class DateUtils {
    public static final String YEAR_FIRST = "yyyy-MM-dd";
    public static final String DATE_FORMAT = "yyyyMMddHHmm";
    public static final String FORMATTED_DATE = "MMM d";


    public static Date getDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);
        return formatter.parse(date);
    }

    public static String getDate(String date, String inputFormat, String outputFormat) {
        String formattedDate = null;
        try {
            formattedDate = new SimpleDateFormat(outputFormat).format(getDate(date, inputFormat));
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        return formattedDate;

    }

    public static String getCurrentDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date();
        return formatter.format(date);
    }

}
