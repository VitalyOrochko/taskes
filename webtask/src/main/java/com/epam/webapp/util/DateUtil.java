package com.epam.webapp.util;


import java.util.GregorianCalendar;
import java.util.Scanner;

public class DateUtil {
    public static GregorianCalendar stringToDate(String date){
        GregorianCalendar res = new GregorianCalendar();
        Scanner scanner = new Scanner(date);
        scanner.useDelimiter("-");
        int year = Integer.valueOf(scanner.next());
        int month = Integer.valueOf(scanner.next());
        int day = Integer.valueOf(scanner.next());
        res.set(year, month, day);
        return res;
    }
}











