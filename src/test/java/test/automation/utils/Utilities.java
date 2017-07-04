package test.automation.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utilities {

    public static String generateDate(int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days);
        return dateFormat.format(cal.getTime());
    }

    public static Integer randomValue() {
        int randValue;
        Random rand = new Random();
        randValue = 1000 + rand.nextInt((9999 - 1000) + 1);
        return randValue;
    }

    public static Integer randomVal(int limit) {
        int randVal;
        Random rand = new Random();
        randVal = 2 + rand.nextInt(limit);
        return randVal;
    }

    public static boolean verifyDateFormat(String strDate, String StrFormat) {
        try {
            DateFormat dateformat = new SimpleDateFormat(StrFormat);
            dateformat.parse(strDate);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}