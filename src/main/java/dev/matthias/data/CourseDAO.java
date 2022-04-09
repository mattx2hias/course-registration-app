package dev.matthias.data;

import dev.matthias.entities.Course;

public interface CourseDAO {

    boolean createCourse(Course course);

    Course readCourseById(String id);

    boolean updateCourse(Course course);

    boolean deleteCourseById(String id);

    boolean decrementCapacity(String id);
}
