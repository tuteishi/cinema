package util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    String error = "The entered data is not correct, try again..";
    String error1 = "The username must contain at least 3 characters consisting of: A-z, 0-9,\"_\" .";
    String error2 = "The password must contain at least 6 characters consisting of: A-z, 0-9,\"_\" .";

    public boolean dateValid(String date) {
        String regex = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((202)[3-99])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(System.lineSeparator() + error + System.lineSeparator());
            return false;
        }
    }

    public boolean timeValid(String time) {
        String regex = "^([01]?[0-9]|2[0-3])-[0-5][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(System.lineSeparator() + error + System.lineSeparator());
            return false;
        }
    }

    public boolean numberValid(String number) {
        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(System.lineSeparator() + error + System.lineSeparator());
            return false;
        }
    }

    public boolean usernameValid(String username) {
        String regex = "^[[A-Z]a-z0-9_]{3,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(System.lineSeparator() + error1 + System.lineSeparator());
            return false;
        }
    }

    public boolean passwordValid(String password) {
        String regex = "^[[A-Z]a-z0-9_]{6,18}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(System.lineSeparator() + error2 + System.lineSeparator());
            return false;
        }
    }
}
