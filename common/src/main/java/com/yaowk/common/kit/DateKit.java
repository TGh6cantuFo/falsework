package com.yaowk.common.kit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class DateKit extends com.jfinal.ext.kit.DateKit {

    public static String toStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
