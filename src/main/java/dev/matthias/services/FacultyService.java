package dev.matthias.services;

import dev.matthias.entities.Course;

public interface FacultyService {

    byte createNewCourse(Course c);

    byte updateCourseDetails(Course c);

    boolean deleteCourse(String cId);
}
