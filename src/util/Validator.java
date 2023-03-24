package util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    String error = "The entered data is not correct, try again..";
    public boolean dateValid(String date) {
        String regex = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((202)[3-99])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(error);
        return false;
        }
    }

    public boolean timeValid(String time){
        String regex = "^([01]?[0-9]|2[0-3])-[0-5][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(error);
            return false;
        }
    }

    public boolean numberValid(String number){
        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(error);
            return false;
        }

    }
}
