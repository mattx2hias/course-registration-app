package dev.matthias.utiltests;

import dev.matthias.utilities.LoginUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests {

    @Test
    void studentLoginTest() {
        Assertions.assertTrue(LoginUtil.login(1, "pstar@uow.edu", "0000"));
    }

    @Test
    void facultyLoginTest() {
        Assertions.assertTrue(LoginUtil.login(2, "ppuff@uow.edu", "0000"));
    }
}
