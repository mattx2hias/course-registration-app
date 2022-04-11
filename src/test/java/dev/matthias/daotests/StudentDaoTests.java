package dev.matthias.daotests;

import dev.matthias.data.StudentDAO;
import dev.matthias.data.StudentDAOPostgres;
import dev.matthias.entities.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentDaoTests {

    Student s = new Student();
    StudentDAO sDao = new StudentDAOPostgres();

    @Test
    void readEnrolledCoursesTest() {
        Assertions.assertEquals("ECON201", sDao.readEnrolledCourses(7290).get(0));
    }

    @Test
    void registerForCourseTest() {
        Assertions.assertTrue(sDao.registerForCourse(7290, "JEL101"));
    }

    @Test
    void cancelRegistrationTest() {
        Assertions.assertTrue(sDao.cancelRegistration(7290, "JEL101"));
    }
}
