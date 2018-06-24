package com.yuan.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public final static String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String TIMESTAMPS_FORMAT = "yyyyMMddHHmmss";

    public static String currentTimestampString(){
        DateFormat df = new SimpleDateFormat(DateUtil.TIMESTAMPS_FORMAT);
        return df.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(currentTimestampString());
    }
}
