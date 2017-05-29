package com.epam.webapp.util;


import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtilTest {
    @Test
    public void stringToDateTest(){
        GregorianCalendar expected = new GregorianCalendar(1995, 10, 4);
        GregorianCalendar actual = DateUtil.stringToDate("1995-10-04");
        int actualDay = actual.get(Calendar.DATE);
        int expectedDay = expected.get(Calendar.DATE);
        Assert.assertEquals(actualDay, expectedDay);
    }
}
