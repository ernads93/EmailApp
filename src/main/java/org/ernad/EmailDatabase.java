package org.ernad;

import java.util.List;
import java.util.ArrayList;

/*
* EmailDatabase
*
* Class stores all created Email Accounts in a List
*
* */
public class EmailDatabase {

    private List<Email> database;

    public EmailDatabase() {
        this.database = new ArrayList<Email>();
    }

    // Adds email to database
    public void addEmail(Email email) {
        this.database.add(email);
    }

    // Return the searched Email
    public Email getEmail(String emailString) {
        for (Email email : this.database) {
            if (emailString.equals(email.getEmail())) {
                return email;
            }
        }
        Email email = new Email();
        email.setFirstName("notfound");
        email.setLastName("notfound");
        email.setEmail();
        return email;
    }

    // Removes email from database
    public void deleteEmail(String emailString) {
        List<Email> toRemove = new ArrayList<Email>();

        for (Email email : this.database) {
            if (emailString.equals(email.getEmail())) {
                toRemove.add(email);
            }
        }
        this.database.removeAll(toRemove);
    }

    // Removes email from database
    public void listEmails() {
        for (Email email : this.database) {
            System.out.println(email.viewEmail());
        }
    }
}
