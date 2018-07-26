package com.chatapp.ipme.chatapp.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {

    public static final String SERVER_FORMAT = "dd-MM-yyyy"; //input format
    public static final String LOCAL_FORMAT = "dd-MM-yyyy"; //output format

    public static String getDateFormatted(String dateString) {
        String dateLocalString = "";

        SimpleDateFormat dfServer = new SimpleDateFormat(SERVER_FORMAT, Locale.getDefault());

        try {
            Date date = dfServer.parse(dateString);
            SimpleDateFormat dfLocal = new SimpleDateFormat(LOCAL_FORMAT, Locale.getDefault());
            dateLocalString = dfLocal.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateLocalString;
    }
}
