package dev.matthias.utiltests;

import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;
import org.junit.jupiter.api.Test;

public class LoggerTest {

    @Test
    void infoLogTest(){
        Logger.log("this is a info log test", LogLevel.INFO);
    }
    @Test
    void errorLogTest(){
        Logger.log("this is an error log test", LogLevel.ERROR);
    }
    @Test
    void warningLogTest(){
        Logger.log("this is a warning log test", LogLevel.WARNING);
    }
    @Test
    void debugLogTest(){
        Logger.log("this is a debug log test", LogLevel.DEBUG);
    }
    @Test
    void multipleLogTest() {
        Logger.log("this is a info log test", LogLevel.INFO);
        Logger.log("this is an error log test", LogLevel.ERROR);
        Logger.log("this is a warning log test", LogLevel.WARNING);
        Logger.log("this is a debug log test", LogLevel.DEBUG);
    }
}
