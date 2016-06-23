package com.nms.utils;

import java.util.regex.Pattern;

/**
 * Created by somebody on 2016/6/23.
 */
public class DataValidationUtil {

    public static Boolean isMACValid(String mac) {

        String pattern="^[A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5}$";

        return Pattern.compile(pattern).matcher(mac).find();
    }
}
