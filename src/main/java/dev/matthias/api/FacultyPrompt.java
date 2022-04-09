package dev.matthias.api;

import dev.matthias.data.CourseDAO;
import dev.matthias.data.CourseDAOPostgres;
import dev.matthias.entities.Course;
import dev.matthias.entities.Faculty;
import dev.matthias.services.FacultyService;
import dev.matthias.services.FacultyServiceImpl;
import dev.matthias.utilities.RegexUtil;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FacultyPrompt {

    Faculty fac;
    FacultyService service;
    CourseDAO cDao;

    public FacultyPrompt(Faculty fac) {
        this.fac = fac;
        this.service = new FacultyServiceImpl();
        this.cDao = new CourseDAOPostgres();
    }

    public void mainMenu() {
        System.out.println( "============Faculty_Options============\n" +
                            "1. Create course\t 2. Update course \n" +
                            "3. Delete course\t 4. Logout      \n" +
                            "=======================================");
        Scanner s = new Scanner(System.in);
        try {
            switch(s.nextInt()) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: LoginPrompt.login(); break;
            }
        } catch(InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            this.mainMenu();
        } finally { s.close(); }
    }

    private void createCoursePrompt() {
        Course newCourse = new Course();
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
            this.mainMenu();
        }
        System.out.print("Enter capacity(1-100): ");
        newCourse.setCapacity(s.nextInt());
        System.out.println();

        this.service.createNewCourse(newCourse);
        System.out.println(newCourse.getId() + " Created.");
        this.mainMenu();
    }

    private void updateCoursePrompt() {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        int selection = 0;

        try {
            System.out.print("Enter course id to edit: ");
            String courseId = s.next();
            Course updatedCourse = cDao.readCourseById(courseId);
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
            }
            this.service.updateCourseDetails(updatedCourse);
            System.out.println(updatedCourse.getId() + " Updated.");
            this.mainMenu();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            this.mainMenu();
        } finally {
            s.close();
        }
    }

    private void deleteCoursePrompt() {
        System.out.println("###---Delete Course---###");
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.print("Enter course id to delete: ");
            String courseId = s.next();
            Course courseToDelete = cDao.readCourseById(courseId);
            System.out.print("Delete " + courseToDelete.getId() + " ?[Y or N]");
            String choice = s.next();
            if(RegexUtil.yesOrNoInput(choice)) {
                cDao.deleteCourseById(courseId);
                System.out.println(courseToDelete.getId() + " Deleted.");
                //remove course from students enrolled list
                //this.service.removeAllStudents(courseId);
                this.mainMenu();
            }
            // add password check for confirmation
        } catch(InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            this.mainMenu();
        } finally { s.close(); }
    }
}
