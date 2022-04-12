package dev.matthias.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {

    public static void log(String message, LogLevel level){
        String logMessage = "["+level+"]"+ " " + message + ". " + new Date() + "\n";

        try {
            Files.write(Paths.get("C:\\Users\\mattm\\Desktop\\CourseRegistrationApp\\courseRegistrationLogs.log"),
                    logMessage.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
