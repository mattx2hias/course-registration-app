package dev.matthias.services;

import dev.matthias.entities.Course;
import dev.matthias.entities.Student;

import java.util.List;

public interface StudentService {

    boolean registerForCourse(int id, String courseId);

    List<String> viewCourseCatalog();

    boolean cancelRegistration(Student s, String courseId);

    String[] viewEnrolledCourses(int id);

    boolean registerNewAccount(Student student);

    int generateNewStudentId();
}
