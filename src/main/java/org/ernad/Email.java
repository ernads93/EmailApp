package org.ernad;

public class Email {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String department;
    private int defaultMailboxCapacity = 10;
    private int defaultPasswordLength = 7;

    // Constructor
    public Email() {
    }

    // Constructor to receive the first name and last name
    public Email(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = this.firstName.toLowerCase() + "_" + this.lastName.toLowerCase() + "@emailapp.com";
        this.department = department;

        // Generate random password
        this.password = randomPassword(defaultPasswordLength);
    }

    // Generates random password
    private String randomPassword(int length) {
        String passwordSet = "abcdefghijklmnopqrstuwxyz0123456789";
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    public String viewEmail() {
        return "Name: " + this.firstName + " " + this.lastName + "; " + "Email: " + this.email + "; " + "Password: "
                + this.password + "; " + "Department: " + this.department + "; " + "Mailbox capacity: "
                + this.defaultMailboxCapacity + ";";
    }

    /*
     * Setter methods start here!
     */

    // Set first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Set last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Set email
    public void setEmail() {
        this.email = this.firstName.toLowerCase() + "_" + this.lastName.toLowerCase() + "@emailapp.com";
    }

    // Set department
    public void setDepartment(String department) {
        this.department = department;
    }

    // Set mailbox capacity
    public void setMailboxCapacity(int capacity) {
        this.defaultMailboxCapacity = capacity;
    }

    // Set the password
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * Getter methods start here!
     */

    // Get lastname
    public String getFirstName() {
        return this.firstName;
    }

    // Get lastname
    public String getLastName() {
        return this.lastName;
    }

    // Get the password
    public String getPassword() {
        return this.password;
    }

    // Get the email
    public String getEmail() {
        return this.email;
    }

    // Get the email
    public String getDepartment() {
        return this.department;
    }
}
