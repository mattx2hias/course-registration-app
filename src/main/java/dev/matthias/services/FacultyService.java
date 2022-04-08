package dev.matthias.services;

import dev.matthias.entities.Course;

public interface FacultyService {

    void facultyPrompt();

    void createNewCourse();

    void updateCourseDetails();

    void deleteCourse();
}
