package dev.matthias.services;

public interface StudentService {

    void studentPrompt();

    boolean registerForCourse();

    boolean viewCourseCatalog();

    boolean cancelRegistration();

    void viewEnrolledCourses();
}
