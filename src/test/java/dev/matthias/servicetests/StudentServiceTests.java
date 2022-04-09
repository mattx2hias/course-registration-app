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
        String[] test = s.viewEnrolledCourses(7290);
        for(String s : test) System.out.println(s);
        //Assertions.assertNotEquals(0, test[0]);
    }
}
