package dev.matthias.data;

import dev.matthias.entities.Student;
import java.util.List;

public interface StudentDAO {

    boolean registerNewAccount(Student student);

    boolean registerForCourse(int studentID, String courseId);

    String[] readEnrolledCourses(int studentId);

    boolean cancelRegistration(int studentId, String courseId);

    List<String> readCourseCatalog();

    boolean checkIfIdExists(int sId);

    boolean checkIfEmailExists(String email);

}
