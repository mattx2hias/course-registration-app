package dev.matthias.utiltests;

import dev.matthias.utilities.LoginUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests {

    @Test
    void studentLoginTest() {
        Assertions.assertNotNull(LoginUtil.sLogin("pstar@uow.edu", "0000"));
    }

    @Test
    void facultyLoginTest() {
        Assertions.assertNotNull(LoginUtil.fLogin("ppuff@uow.edu", "0000"));
    }
}
