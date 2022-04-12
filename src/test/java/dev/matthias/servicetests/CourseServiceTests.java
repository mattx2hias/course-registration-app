package dev.matthias.servicetests;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;
import dev.matthias.services.CourseService;
import dev.matthias.services.CourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CourseServiceTests {

    CourseService courseService = new CourseServiceImpl();
    CourseDAO courseDAO = new CourseDAOPostgres();
    Course course = new Course("SPG123", "Spongebob", "An in-depth look at the metaphysics of the coveted children's show.",
            LocalDate.of(2022, 2, 15), LocalDate.of(2022, 12, 14),0);

    @Test
    void createCourse() {
        Assertions.assertTrue(courseDAO.createCourse(course));
    }

    @Test
    void atCapacity() {
        Assertions.assertTrue(courseService.atCapacity(course.getId()));
    }

    @Test
    void afterStartDate() {
        Assertions.assertTrue(courseService.afterStartDate(course.getId()));
    }

    @Test
    void deleteCourse() {
        Assertions.assertTrue(courseDAO.deleteCourseById(course.getId()));
    }
}
