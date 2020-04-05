package com.wj.myssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转换成字符串
    public static String date2String(Date date, String patt) {
        return new SimpleDateFormat(patt).format(date);
    }
    //字符串转换成日期
    public static Date string2Date(String str,String patt) throws ParseException {
        return new SimpleDateFormat(patt).parse(str);
    }
}
