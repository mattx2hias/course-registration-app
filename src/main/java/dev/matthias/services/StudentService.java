package dev.matthias.services;

import dev.matthias.entities.Course;
import dev.matthias.entities.Student;

import java.util.List;

public interface StudentService {

    boolean registerForCourse(int id, String courseID);

    List<String> viewCourseCatalog();

    boolean cancelRegistration(Student s, String courseID);

    String[] viewEnrolledCourses(int id);
}
