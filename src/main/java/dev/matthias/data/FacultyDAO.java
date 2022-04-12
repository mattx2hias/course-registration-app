package dev.matthias.data;

import dev.matthias.entities.Faculty;

public interface FacultyDAO {

    Faculty readFaculty(String email, String password);

    boolean addFaculty(Faculty faculty);


}
