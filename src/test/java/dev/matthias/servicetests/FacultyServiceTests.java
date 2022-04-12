package dev.matthias.servicetests;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;
import dev.matthias.services.FacultyService;
import dev.matthias.services.FacultyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FacultyServiceTests {

    FacultyService facultyService = new FacultyServiceImpl();
    CourseDAO courseDAO = new CourseDAOPostgres();
    Course course = new Course("TTT000", "Test", "test course",
            LocalDate.of(2022, 8, 15), LocalDate.of(2022, 12, 14),10);
    Course badCourse = new Course("BAD111", "bad", "bad course",
            LocalDate.of(2022, 8, 15), LocalDate.of(2022, 7, 15), 5);

    @Test
    @Order(1)
    void createNewCourse() {
        Assertions.assertEquals(2, facultyService.createNewCourse(badCourse));
        Assertions.assertEquals(0, facultyService.createNewCourse(course));
    }

    @Test
    @Order(2)
    void updateCourse() {
        course.setName("BLAH");
        Assertions.assertEquals(0, facultyService.updateCourseDetails(course));
    }

    @Test
    @Order(3)
    void deleteCourse() {
        Assertions.assertTrue(facultyService.deleteCourse(course.getId()));
    }
}
