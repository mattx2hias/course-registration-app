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

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
