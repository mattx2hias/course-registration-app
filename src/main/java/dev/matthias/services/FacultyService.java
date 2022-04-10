package dev.matthias.services;

import dev.matthias.entities.Course;

public interface FacultyService {

    boolean createNewCourse(Course c);

    boolean updateCourseDetails(Course c);

    boolean deleteCourse(String cId);
}
