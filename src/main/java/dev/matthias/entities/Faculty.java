package dev.matthias.entities;

public class Faculty {
    private int fId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Faculty() {}

    public Faculty(int fId, String firstName, String lastName, String email, String password) {
        this.fId = fId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
