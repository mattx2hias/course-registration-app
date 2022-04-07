package dev.matthias.data;

import dev.matthias.entities.Course;

public interface CourseDAO {

    boolean createCourse(Course course);

    Course readCourseById(String id);

    Course updateCourse(Course course);

    boolean deleteCourseById(String id);
}
