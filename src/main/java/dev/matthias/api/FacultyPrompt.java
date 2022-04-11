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
import java.util.Locale;
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
                            "1->Create course   2->Update course \n" +
                            "3->Delete course   4->Logout      \n" +
                            "=======================================");
        Scanner s = new Scanner(System.in);
        try {
            switch(s.nextInt()) {
                case 1: createCoursePrompt(); break;
                case 2: updateCoursePrompt(); break;
                case 3: deleteCoursePrompt(); break;
                case 4: LoginPrompt.login(); break;
            }
        } catch(InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid input.");
            this.mainMenu();
        }
    }

    private void createCoursePrompt() {
        Course newCourse = new Course();
        Scanner s = new Scanner(System.in).useDelimiter("\n");

        System.out.print("Enter course id: ");
        newCourse.setId(s.next().toUpperCase(Locale.ROOT));
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

        try (Scanner s = new Scanner(System.in).useDelimiter("\n")) {
            int selection = 0;
            System.out.print("Enter course ID to edit: ");
            String courseId = s.next().toUpperCase(Locale.ROOT);
            Course updatedCourse = cDao.readCourseById(courseId);
            if(updatedCourse == null) {
                System.out.println("Course not found.");
                this.mainMenu();
                return;
            }
            System.out.println("######___Editing___######");
            System.out.println(updatedCourse.toString());
            System.out.println("######___Editing___######");
            System.out.println( "1->Course ID  | 2->Course Name | 3->Course Description\n" +
                                "4->Start Date | 5->End Date | 6->Cancel");
            selection = s.nextInt();
            switch (selection) {
                case 1:
                    System.out.print("Enter course ID: ");
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
                default: break;
            }
            byte statusCode = this.service.updateCourseDetails(updatedCourse);
            switch(statusCode) {
                case -2: break;
                case -1:
                    System.out.println();
                    break;
                case 0:
                    System.out.println(updatedCourse.getId() + " Updated.");
                    break;
                case 1: break;
                case 2:
                    System.out.println("End date cannot be before start date.");
                    break;
            }
            this.mainMenu();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            this.mainMenu();
        }
    }

    private void deleteCoursePrompt() {
        System.out.println("######___Delete Course___######");
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.print("Enter course ID to delete: ");
            String courseId = s.next().toUpperCase(Locale.ROOT);
            Course courseToDelete = cDao.readCourseById(courseId);
            System.out.print("Delete " + courseToDelete.getId() + "?[Y or N]: ");
            String choice = s.next();
            if(RegexUtil.yesOrNoInput(choice)) {
                if(this.service.deleteCourse(courseId)) System.out.println(courseToDelete.getId() + " Deleted.");
                    else System.out.println("Course deletion failed.");
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
