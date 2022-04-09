package dev.matthias.services;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.StudentDAO;
import dev.matthias.data.StudentDAOPostgres;
import dev.matthias.entities.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService{

    StudentDAO sDao;
    CourseDAO cDao;

    public StudentServiceImpl() {
        this.sDao = new StudentDAOPostgres();
        this.cDao = new CourseDAOPostgres();
    }

    @Override
    public boolean registerForCourse(int id, String courseID) {
        //check if prereqs are satisfied
        //check if already enrolled
        if(this.sDao.registerForCourse(id, courseID)) {
            this.cDao.decrementCapacity(courseID);
            return true;
        } else return false;
    }

    @Override
    public List<String> viewCourseCatalog() {
        return this.sDao.readCourseCatalog();
    }

    @Override
    public boolean cancelRegistration(Student s, String courseID) {
        //check if student is enrolled in requested course
        return this.sDao.cancelRegistration(s.getStudentID(), courseID);
    }

    @Override
    public String[] viewEnrolledCourses(int id) {
        return this.sDao.readEnrolledCourses(id);
    }
}
