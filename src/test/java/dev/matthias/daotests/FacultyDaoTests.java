package dev.matthias.daotests;

import dev.matthias.data.FacultyDAO;
import dev.matthias.data.FacultyDAOPostgres;
import dev.matthias.entities.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class FacultyDaoTests {

    FacultyDAO fDao = new FacultyDAOPostgres();
    Faculty faculty = new Faculty(1111, "John", "Doe", "jdoe@uow.edu", "0");

    @Test
    void addFaculty() {
        Assertions.assertTrue(this.fDao.addFaculty(faculty));
    }

    @Test
    void getFaculty() {
        Assertions.assertNotNull(this.fDao.readFaculty(faculty.getEmail(), "0"));
    }
}
