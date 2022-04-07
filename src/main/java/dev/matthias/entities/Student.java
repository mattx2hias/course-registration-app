package dev.matthias.entities;

import dev.matthias.entities.Course;

public class Student {
    private int studentID;
    private String firstName;
    private String lastName;
    private String year;

    public Student(int studentID, String firstName, String lastName, String year) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
    }

}
