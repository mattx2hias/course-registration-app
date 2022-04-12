package dev.matthias.daotests;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class CourseDaoTests {

    static CourseDAO courseDAO = new CourseDAOPostgres();
    Course testCourse = new Course("SPG123", "Spongebob", "An in-depth look at the metaphysics of the coveted children's show.",
            LocalDate.of(2022, 8, 15), LocalDate.of(2022, 12, 14),90);

    @Test
    void createCourseTest() {
        Assertions.assertTrue(courseDAO.createCourse(testCourse));
    }

    @Test
    void readCourseByIdTest() {
        Course c = courseDAO.readCourseById(testCourse.getId());
        Assertions.assertEquals("Spongebob", c.getName());
    }

    @Test
    void updateCourseTest() {
        int capChange = 1;
        testCourse.setCapacity(capChange);
        Assertions.assertTrue(courseDAO.updateCourse(testCourse));

        Assertions.assertEquals(capChange, courseDAO.readCourseById(testCourse.getId()).getCapacity());
    }

    @Test
    void deleteCourseTest() { Assertions.assertTrue(courseDAO.deleteCourseById(testCourse.getId())); }
}
