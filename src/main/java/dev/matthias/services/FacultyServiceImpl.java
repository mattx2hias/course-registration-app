package dev.matthias.services;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.FacultyDAO;
import dev.matthias.data.FacultyDAOPostgres;
import dev.matthias.entities.Course;

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
    public boolean deleteCourse(String cId) {
        if(this.fDao.removeEnrolledStudents(cId)) return this.cDao.deleteCourseById(cId);
            else return false;
    }

}
