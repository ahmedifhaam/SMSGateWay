package com.xelvias.imsms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static Date getCurrentDateTime(){
        Date date = new Date();
        System.out.println(formatDate(date)); //2016/11/16 12:08:43
        return date;
    }

    public static String formatDate(Date date){
        return dateFormat.format(date);
    }
}
