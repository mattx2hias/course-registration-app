package dev.matthias.servicetests;

import dev.matthias.services.StudentService;
import dev.matthias.services.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentServiceTests {

    StudentService s = new StudentServiceImpl();

    @Test
    void registerForCourse() {
        Assertions.assertEquals(0, s.registerForCourse(7290, "jel101"));
    }

    @Test
    void cancelRegistration() {
        Assertions.assertEquals(0, s.cancelRegistration(7290, "jel101"));
    }

    @Test
    void viewEnrolledCoursesTest() {
        Assertions.assertEquals("ECON201", s.viewEnrolledCourses(7290).get(0));
    }

    @Test
    void generateNewStudentId() {
        int rand = s.generateNewStudentId();
        Assertions.assertTrue(rand >= 1000 && rand <= 9999);
    }
}
