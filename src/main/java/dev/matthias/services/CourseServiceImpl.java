package dev.matthias.services;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;

public class CourseServiceImpl implements CourseService{

    CourseDAO cDao;

    public CourseServiceImpl() {
        this.cDao = new CourseDAOPostgres();
    }

    /**
     *
     * @param cId course id string
     * @return true if capacity is at 0, no more students can enroll
     */
    @Override
    public boolean atCapacity(String cId) {
        return (this.cDao.readCourseById(cId).getCapacity() == 0);
    }

    /**
     *
     * @param cId course id string
     * @return true if current time is after course start date
     */
    @Override
    public boolean afterStartDate(String cId) {
        Course dummy = this.cDao.readCourseById(cId);
        long currentUnixEpoch = (System.currentTimeMillis()/1000);
        return dummy.getStart() < currentUnixEpoch;
    }
}
