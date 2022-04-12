package dev.matthias.utilities;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;


public class RegexUtil {

    public static boolean yesOrNoInput(String input) {
        Pattern yesPat = Pattern.compile("(yes|y)", Pattern.CASE_INSENSITIVE);
        return yesPat.matcher(input).matches();
    }

    public static long getUnixEpochTime(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
        LocalDate d = LocalDate.parse(date, dtf);
        ZoneId zoneId = ZoneId.systemDefault();
        return d.atStartOfDay(zoneId).toEpochSecond();
    }

    /**
     * -1 password is less than 8 characters
     * 0 password is satisfactory
     * 1 password has no special characters
     * @param pass
     * @return
     */
    public static byte validatePassword(String pass) {
        Pattern passPattern = Pattern.compile("[?!@#$%^&*~_=+`]");
        if(pass.length() < 8) return -1;
        else if(!passPattern.matcher(pass).find()) return 1;
            else return 0;
    }

    public static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^(.+)@uow.edu");
        return emailPattern.matcher(email).matches();
    }
}
