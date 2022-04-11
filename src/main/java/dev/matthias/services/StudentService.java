package dev.matthias.services;

import dev.matthias.entities.Student;

import dev.matthias.utilities.List;

public interface StudentService {

    byte registerForCourse(int id, String cId);

    List<String> viewCourseCatalog();

    byte cancelRegistration(int sId, String cId);

    List<String> viewEnrolledCourses(int sId);

    byte registerNewAccount(Student student);

    int generateNewStudentId();
}
