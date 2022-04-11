package dev.matthias.services;

import dev.matthias.entities.Student;

import dev.matthias.utilities.List;

public interface StudentService {

    boolean registerForCourse(int id, String courseId);

    List<String> viewCourseCatalog();

    boolean cancelRegistration(Student s, String courseId);

    List<String> viewEnrolledCourses(int id);

    boolean registerNewAccount(Student student);

    int generateNewStudentId();
}
