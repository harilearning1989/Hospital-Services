package com.web.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HospitalUtils {

    public static String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }

 /*   System.out.println("1234567890 : " + validateNumber("1234567890"));
      System.out.println("1234 567 890 : " + validateNumber("1234 567 890"));
      System.out.println("123 456 7890 : " + validateNumber("123 456 7890"));
      System.out.println("123-567-8905 : " + validateNumber("123-567-8905"));
      System.out.println("9866-767-545 : " + validateNumber("9866-767-545"));
      System.out.println("9866.767.545 : " + validateNumber("9866.767.545"));
      System.out.println("9866-767.545 : " + validateNumber("9866-767.545"));
      System.out.println("123-456-7890 ext4444 : " + validateNumber("123-456-7890 ext4444"));
      System.out.println("123-456-7890 x4444 : " + validateNumber("123-456-7890 x4444"));*/
    public static boolean validateNumber(String mobNumber) {
        //validates phone numbers having 10 digits (9998887776)
        if (mobNumber.matches("\\d{10}"))
            return true;
        //validates phone numbers having digits, -, . or spaces
        else if (mobNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        else if (mobNumber.matches("\\d{4}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}"))
            return true;
        //validates phone numbers having digits and extension (length 3 to 5)
        else if (mobNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        //validates phone numbers having digits and area code in braces
        else if (mobNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
        else if (mobNumber.matches("\\(\\d{5}\\)-\\d{3}-\\d{3}"))
            return true;
        else if (mobNumber.matches("\\(\\d{4}\\)-\\d{3}-\\d{3}"))
            return true;
        //return false if any of the input matches is not found
        else
            return false;
    }
}
