package com.xelvias.imsms.Models;

import com.xelvias.imsms.utils.FileUtils;

public class Phonenumber implements FileUtils.CSVwritable {
    String number = "947xxxxxxxx";

    @Override
    public String[] toStringArray() {
        return  new String[]{number};
    }
}
