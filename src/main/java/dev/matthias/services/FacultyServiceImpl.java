package dev.matthias.services;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.FacultyDAO;
import dev.matthias.data.FacultyDAOPostgres;
import dev.matthias.entities.Course;
import dev.matthias.entities.Faculty;
import dev.matthias.utilities.RegexUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FacultyServiceImpl implements FacultyService{

    FacultyDAO fDao;
    CourseDAO cDao;

    public FacultyServiceImpl() {
        this.fDao = new FacultyDAOPostgres();
        this.cDao = new CourseDAOPostgres();
    }


    @Override
    public boolean createNewCourse(Course c) {
        return this.cDao.createCourse(c);
    }

    @Override
    public boolean updateCourseDetails(Course c) {
        return this.cDao.updateCourse(c);
    }

    @Override
    public boolean deleteCourse(String id) {
        if(this.fDao.removeEnrolledStudents(id)) return this.cDao.deleteCourseById(id);
            else return false;
    }

}
