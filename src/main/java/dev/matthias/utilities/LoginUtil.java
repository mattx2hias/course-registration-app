package dev.matthias.utilities;

import dev.matthias.data.*;
import dev.matthias.entities.Faculty;
import dev.matthias.entities.Student;

public class LoginUtil {

    static StudentDAO sDao = new StudentDAOPostgres();
    static FacultyDAO fDao = new FacultyDAOPostgres();

    public static Student sLogin(String email, String password) {
        return sDao.readStudent(email, password);
    }

    public static Faculty fLogin(String email, String password) {
        return fDao.readFaculty(email, password);
    }
}
