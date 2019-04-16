package com.xelvias.imsms.utils;

public class StringUtils {

    public static boolean isEmpty(String val){
        if(val ==null || val.length()==0){
            return true;
        }
        return false;
    }
}
