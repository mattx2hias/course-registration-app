package dev.matthias.services;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.StudentDAO;
import dev.matthias.data.StudentDAOPostgres;
import dev.matthias.entities.Student;

import dev.matthias.utilities.List;
import java.util.Random;

public class StudentServiceImpl implements StudentService{

    StudentDAO sDao;
    CourseDAO cDao;

    public StudentServiceImpl() {
        this.sDao = new StudentDAOPostgres();
        this.cDao = new CourseDAOPostgres();
    }

    @Override
    public boolean registerForCourse(int id, String cId) {
        //check if prereqs are satisfied
        //check if already enrolled
        if(this.sDao.registerForCourse(id, cId)) {
            this.cDao.decrementCapacity(cId);
            return true;
        } else return false;
    }

    @Override
    public List<String> viewCourseCatalog() {
        return this.sDao.readCourseCatalog();
    }

    @Override
    public boolean cancelRegistration(Student s, String cId) {
        //check if student is enrolled in requested course
        return this.sDao.cancelRegistration(s.getStudentID(), cId);
    }

    @Override
    public List<String> viewEnrolledCourses(int cId) {
        return this.sDao.readEnrolledCourses(cId);
    }

    @Override
    public boolean registerNewAccount(Student student) {
        return this.sDao.registerNewAccount(student);
    }

    @Override
    public int generateNewStudentId() {
        Random rand;
        int sId;
        do {
            rand = new Random();
            sId = rand.nextInt(9999-1000) + 1000;
        } while(this.sDao.checkIfIdExists(sId));
        return sId;
    }

}
