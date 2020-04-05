package com.wj.myssm.utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void testDateUtils(){
        Date date = new Date("Wed Oct 10 18:10:00 CST 2018");
        System.out.println(DateUtils.date2String(date,"yyyy-MM-dd HH:mm:ss"));
    }
}
