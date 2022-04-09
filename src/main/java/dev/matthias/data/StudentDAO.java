package dev.matthias.data;

import dev.matthias.entities.Student;
import java.util.List;

public interface StudentDAO {

    boolean registerNewAccount(Student stud);

    boolean registerForCourse(int studentID, String courseID);

    String[] readEnrolledCourses(int studentID);

    boolean cancelRegistration(int studentID, String courseID);

    List<String> readCourseCatalog();
}
