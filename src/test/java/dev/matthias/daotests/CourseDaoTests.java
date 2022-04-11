package dev.matthias.daotests;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class CourseDaoTests {

    static CourseDAO courseDAO = new CourseDAOPostgres();
    static Course testCourse = new Course("SPG123", "Spongebob", "An in-depth look at the metaphysics of the coveted children's show.",
            LocalDate.of(2022, 8, 15), LocalDate.of(2022, 12, 14), 1,90);

    @Test
    void createCourseTest() { courseDAO.createCourse(testCourse); }

    @Test
    void createCourseMissingAttributesTest() {}

    @Test
    void readCourseByIdTest() {
        Course addedCourse = courseDAO.readCourseById("SPG123");
        Assertions.assertEquals("Spongebob", addedCourse.getName());
    }

    @Test
    void readCourseByIdNoneTest() {
        Course addedCourse = courseDAO.readCourseById("YUM000");
        Assertions.assertNull(addedCourse);
    }

    @Test
    void readCoursebyIdLowerCase() {
        Course addedCourse = courseDAO.readCourseById("spg123");
        Assertions.assertEquals("Spongebob", addedCourse.getName());
    }

    @Test
    void updateCourseTest() {
        Course testCourse = courseDAO.readCourseById("HOW111");
        testCourse.setCapacity(1);

        Assertions.assertTrue(courseDAO.updateCourse(testCourse));
    }

    @Test
    void deleteCourseTest() { Assertions.assertTrue(courseDAO.deleteCourseById(testCourse.getId())); }

    @Test
    void deleteCourseFailedTest() {
        Assertions.assertTrue(courseDAO.deleteCourseById(testCourse.getId()));
    }


}
