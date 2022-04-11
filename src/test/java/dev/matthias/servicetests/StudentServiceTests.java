package dev.matthias.servicetests;

import dev.matthias.services.StudentService;
import dev.matthias.services.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentServiceTests {

    StudentService s = new StudentServiceImpl();

    @Test
    void registerForCourse() {
        Assertions.assertTrue(s.registerForCourse(7290, "BOAT101"));
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
