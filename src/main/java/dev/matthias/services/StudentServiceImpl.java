package dev.matthias.services;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.data.StudentDAO;
import dev.matthias.data.StudentDAOPostgres;
import dev.matthias.entities.Course;
import dev.matthias.entities.Student;

import dev.matthias.utilities.List;
import dev.matthias.utilities.RegexUtil;

import java.util.Random;

public class StudentServiceImpl implements StudentService{

    StudentDAO sDao;
    CourseDAO cDao;
    CourseService cService = new CourseServiceImpl();

    public StudentServiceImpl() {
        this.sDao = new StudentDAOPostgres();
        this.cDao = new CourseDAOPostgres();
    }

    /**
     * -2 course doesn't exist
     * -1 error with the student dao register course method<br/>
     * 0 course registered successfully<br/>
     * 1 student is already enrolled<br/>
     * 2 start date for course has already past<br/>
     * 3 if course has reached capacity<br/>
     * Removes 1 from course capacity
     * @param sId student id int
     * @param cId course id string
     * @return digit that denotes status of registering for a course
     */
    @Override
    public byte registerForCourse(int sId, String cId) {
        if(this.cDao.readCourseById(cId) == null) return -2;
        if(this.sDao.isEnrolled(sId, cId)) return 1;
        if(this.cService.afterStartDate(cId)) return 2;
        if(this.cService.atCapacity(cId)) return 3;
        if(this.sDao.registerForCourse(sId, cId)) {
            Course c = this.cDao.readCourseById(cId);
            int cap = c.getCapacity();
            c.setCapacity(--cap);
            this.cDao.updateCourse(c);
            return 0;
        } else return -1;
    }

    @Override
    public List<String> viewCourseCatalog() {
        return this.sDao.readCourseCatalog();
    }

    /**
     * -2 if course doesn't exist
     * -1 error with student dao cancel registration method<br/>
     * 0 record based on course id removed from student_course table<br/>
     * 1 student is not enrolled<br/>
     * Adds 1 to course capacity
     * @param sId student id int
     * @param cId course id string
     * @return digit that denotes status of withdrawing from a course
     */
    @Override
    public byte cancelRegistration(int sId, String cId) {
        if(this.cDao.readCourseById(cId) == null) return -2;
        if(!this.sDao.isEnrolled(sId, cId)) return 1;
        if(this.sDao.cancelRegistration(sId, cId)) {
            Course c = this.cDao.readCourseById(cId);
            int cap = c.getCapacity();
            c.setCapacity(++cap);
            this.cDao.updateCourse(c);
            return 0;
        }
        return -1;
    }

    @Override
    public List<String> viewEnrolledCourses(int cId) {
        return this.sDao.readEnrolledCourses(cId);
    }

    /**
     * -1 error with student dao register new account method<br/>
     * 0 inserted new student record into student table<br/>
     * 1 student with email address already exists<br/>
     * @param student student object tied to new account
     * @return digit that denotes status of registering a new student account
     */
    @Override
    public byte registerNewAccount(Student student) {
        if(this.sDao.emailExists(student.getEmail())) return 1;
        if(this.sDao.registerNewAccount(student)) return 0;
            else return -1;
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
