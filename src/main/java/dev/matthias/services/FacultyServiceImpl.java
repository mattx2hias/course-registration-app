package dev.matthias.services;

import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FacultyServiceImpl implements FacultyService{
    @Override
    public boolean createNewCourse() {
        Course newCourse = new Course();
        CourseDAOPostgres courseImpl = new CourseDAOPostgres();
        Scanner s = new Scanner(System.in);

        System.out.println("Enter course id: ");
        newCourse.setId(s.next());
        System.out.println("Enter course name: ");
        newCourse.setName(s.next());
        System.out.println("Enter course description: ");
        newCourse.setDescription(s.next());
        System.out.println("Enter start date[DD/MM/YYYY]: ");
        newCourse.setStart(s.nextLong());
        System.out.println("Enter end date[DD/MM/YYYY]: ");
        newCourse.setEnd(s.nextLong());
        System.out.println("Enter capacity(1-100): ");
        newCourse.setCapacity(s.nextInt());

        return courseImpl.createCourse(newCourse);
    }

    @Override
    public boolean updateCourseDetails() {
        Scanner s = new Scanner(System.in);
        System.out.println("1. Course ID \t 2. Course Name \t 3. Course Description \n" +
                "4. Start Date \t 5. End Date \t 6. Capacity\nEnter course id you wish to update: ");
        int selection = 0;
        Course updatedCourse = new Course();
        try {
            selection = s.nextInt();
            switch(selection) {
                case 1:
                    System.out.println("Enter course id: ");
                    newCourse.setId(s.next());
                    break;
                case 2:
                    System.out.println("Enter course name: ");
                    break;
                case 3:
                    System.out.println("Enter course description: ");
                    break;
                case 4:
                    System.out.println("Enter start date[DD/MM/YYYY]: ");
                    break;
                case 5:
                    System.out.println("Enter end date[DD/MM/YYYY]: ");
                    break;
                case 6:
                    System.out.println("Enter capacity(1-100): ");
                    break;
                default:
                    System.out.println("Enter a valid number or Esc to quit.");
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
            return false;
        } finally {
            s.close();
        }


        return false;
    }

    @Override
    public boolean deleteCourse() {
        return false;
    }
}
