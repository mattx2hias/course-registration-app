package dev.matthias.services;

import dev.matthias.entities.Course;

public interface FacultyService {



    boolean createNewCourse();

    boolean updateCourseDetails();

    boolean deleteCourse();
}
