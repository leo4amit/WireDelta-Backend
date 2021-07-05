package com.wiredelta.backend.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getStringFromDate(Date date) {
        String dateString = null;

        if(date!=null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateString = simpleDateFormat.format(date);
        }
        return dateString;
    }
}
