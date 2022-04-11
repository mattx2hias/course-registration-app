package dev.matthias.utilities;

import java.time.LocalDate;
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
        return d.toEpochDay();
    }

    /**
     * -1 password is less than 8 characters
     * 0 password is satisfactory
     * 1 password has no special characters
     * @param pass
     * @return
     */
    public static byte validatePassword(String pass) {
        if(pass.length() < 8) return -1;
        if(!pass.contains("[?!@#$%^&*~-_=+`]")) return 1; //fix
        return 0;
    }
}
