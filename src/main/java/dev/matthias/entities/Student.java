package dev.matthias.entities;

public class Student {
    private int studentID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String year;
    private String major;

    public Student(int studentID, String firstName, String lastName, String year) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
    }

    public Student() {}

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "Email: " + email + "\n" +
                "Password " + password + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n";
    }
}
