package dev.matthias.services;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;
import dev.matthias.utilities.RegexUtil;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FacultyServiceImpl implements FacultyService{
    @Override
    public void facultyPrompt() {
        System.out.println( "============Faculty_Options============\n" +
                            "| 1. Create course\t 2. Update course |\n" +
                            "| 3. Delete course\t 4. Logout\t      |\n" +
                            "=======================================");
        Scanner s = new Scanner(System.in);
        try {
            switch(s.nextInt()) {
                case 1: createNewCourse(); break;
                case 2: updateCourseDetails(); break;
                case 3: deleteCourse(); break;
                case 4: break; // return to login prompt
            }
        } catch(InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            facultyPrompt();
        } finally { s.close(); }
    }

    @Override
    public void createNewCourse() {
        Course newCourse = new Course();
        CourseDAOPostgres courseImpl = new CourseDAOPostgres();
        Scanner s = new Scanner(System.in).useDelimiter("\n");

        System.out.print("Enter course id: ");
        newCourse.setId(s.next());
        System.out.print("Enter course name: ");
        newCourse.setName(s.next());
        System.out.print("Enter course description: ");
        newCourse.setDescription(s.next());
        try {
            System.out.print("Enter start date[DD/MM/YYYY]: ");
            newCourse.setStart(RegexUtil.getUnixEpochTime(s.next()));
            System.out.print("Enter end date[DD/MM/YYYY]: ");
            newCourse.setEnd(RegexUtil.getUnixEpochTime(s.next()));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid format for date.");
            facultyPrompt();
        }
        System.out.print("Enter capacity(1-100): ");
        newCourse.setCapacity(s.nextInt());
        System.out.println();

        courseImpl.createCourse(newCourse);
        System.out.println(newCourse.getId() + " Created.");
        facultyPrompt();
    }

    @Override
    public void updateCourseDetails() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        int selection = 0;

        try {
            System.out.print("Enter course id to edit: ");
            String courseId = s.next();
            CourseDAO courseDAO = new CourseDAOPostgres();
            Course updatedCourse = courseDAO.readCourseById(courseId);
            System.out.println("###---Editing---###");
            System.out.println(updatedCourse.toString());
            System.out.println("###---Editing---###");
            System.out.println("1. Course ID\t2. Course Name\t3. Course Description\n" +
                    "4. Start Date\t 5. End Date\t 6. Capacity");
            selection = s.nextInt();
            switch(selection) {
                case 1:
                    System.out.print("Enter course id: ");
                    updatedCourse.setId(s.next());
                    break;
                case 2:
                    System.out.print("Enter course name: ");
                    updatedCourse.setName(s.next());
                    break;
                case 3:
                    System.out.print("Enter course description: ");
                    updatedCourse.setDescription(s.next());
                    break;
                case 4:
                    System.out.print("Enter start date[DD/MM/YYYY]: ");
                    updatedCourse.setStart(RegexUtil.getUnixEpochTime(s.next()));
                    break;
                case 5:
                    System.out.print("Enter end date[DD/MM/YYYY]: ");
                    updatedCourse.setEnd(RegexUtil.getUnixEpochTime(s.next()));
                    break;
                case 6:
                    System.out.print("Enter capacity(1-100): ");
                    updatedCourse.setCapacity(s.nextInt());
                    break;
                default:
                    System.out.print("Enter a valid input or Esc to quit.");
            }
            courseDAO.updateCourse(updatedCourse);
            System.out.println(updatedCourse.getId() + " Updated.");
            facultyPrompt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            facultyPrompt();
        } finally {
            s.close();
        }
    }

    @Override
    public void deleteCourse() {
        System.out.println("###---Delete Course---###");
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.print("Enter course id to delete: ");
            String courseId = s.next();
            Course courseToDelete = new CourseDAOPostgres().readCourseById(courseId);
            System.out.print("Delete " + courseToDelete.getId() + " ?[Y or N]");
            String choice = s.next();
            if(RegexUtil.yesOrNoInput(choice)) {
                new CourseDAOPostgres().deleteCourseById(courseId);
                System.out.println(courseToDelete.getId() + " Deleted.");
                //remove course from students enrolled list
                facultyPrompt();
            }

            // add password check for confirmation
        } catch(InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            facultyPrompt();
        } finally { s.close(); }
    }
}
