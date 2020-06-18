package com.me.kaily.beam.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EntryUtils {

    public static String dataFromLong (long time) {
        DateFormat format = new SimpleDateFormat("EEE, dd MMMM yyyy 'at' hh:mm aaa", Locale.US);
        return format.format(new Date(time));
    }
}
