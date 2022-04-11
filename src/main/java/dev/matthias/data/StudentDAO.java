package dev.matthias.data;

import dev.matthias.entities.Student;
import dev.matthias.utilities.List;

public interface StudentDAO {

    boolean registerNewAccount(Student student);

    boolean registerForCourse(int sID, String cId);

    List<String> readEnrolledCourses(int sId);

    boolean cancelRegistration(int sId, String cId);

    List<String> readCourseCatalog();

    boolean checkIfIdExists(int sId);

    boolean emailExists(String email);

    boolean isEnrolled(int sId, String cId);

}
