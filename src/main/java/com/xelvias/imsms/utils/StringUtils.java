package com.xelvias.imsms.utils;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(String val){
        if(val ==null || val.length()==0){
            return true;
        }
        return false;
    }

    public static boolean matchRegularExpression(String valtoMatch,String regEx){
        return Pattern.matches(regEx,valtoMatch);
    }
}
