package com.sridhar.upcomingmovies.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SriMaddy on 4/24/2017.
 */

public class DateUtils {

    public static String getFormattedDate(String date) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = parser.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
