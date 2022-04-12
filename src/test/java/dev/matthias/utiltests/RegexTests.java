package dev.matthias.utiltests;

import dev.matthias.utilities.RegexUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class RegexTests {

    @Test
    void yesOrNoTest() {
        Assertions.assertTrue(RegexUtil.yesOrNoInput("yEs"));
    }

    @Test
    void getUnixEpochTime() {
        System.out.println(RegexUtil.getUnixEpochTime("01/05/2022"));
    }
}
