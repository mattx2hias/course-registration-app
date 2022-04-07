package dev.matthias.entities;

public class Faculty {
    private int fId;
    private String firstName;
    private String lastName;
    private String department;

    public Faculty() {}

    public Faculty(int fId, String firstName, String lastName, String department) {
        this.fId = fId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
}
