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

    /**
     * -1 error with course dao create course
     * 0 successfully created course
     * 1 course cannot start before current time
     * 2 end date cannot be less than start date
     * @param c course
     * @return digit denoting status of faculty creating new course
     */
    @Override
    public byte createNewCourse(Course c) {
        //if(this.cService.afterStartDate(c.getId())) return 1;
        if(c.getEnd() < c.getStart()) return 2;
        if(this.cDao.createCourse(c)) return 0;
            else return -1;
    }

    /**
     * -1 error with course dao update course
     * 0 successfully created course
     * 1 course cannot start before current time
     * 2 end date cannot be less than start date
     * @param c course
     * @return digit denoting status of faculty updating a course
     */
    @Override
    public byte updateCourseDetails(Course c) {
        //if(this.cService.afterStartDate(c.getId())) return 1;
        if(c.getEnd() < c.getStart()) return 2;
        if(this.cDao.updateCourse(c)) return 0;
         else return -1;
    }

    @Override
    public boolean deleteCourse(String cId) {
        if(this.fDao.removeEnrolledStudents(cId)) return this.cDao.deleteCourseById(cId);
            else return false;
    }

}
