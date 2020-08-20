package org.ernad;

import java.util.Scanner;

public class Emailapp {

    public static void main(String[] args) throws Exception {

        EmailDatabase database = new EmailDatabase();

        Scanner in = new Scanner(System.in);
        boolean exit = false;
        boolean adminSet = false;
        boolean adminLogin = false;
        boolean loggedIn = false;
        String option;

        System.out.println("Welcome to the Email Administration Application 1.0.");
        System.out.println("");

        while (!exit) {
            if (!adminSet)
                adminSet = adminEmailCreation(database);

            if (!adminLogin)
                adminLogin = adminLogin(database);

            while (adminLogin) {
                adminLogin = actionMenu(database);
                System.out.print("Please enter 1 for login, or 0 to exit the application: ");
                option = in.next();
                while (!option.equals("0") && !option.equals("1")) {
                    System.out.println("");
                    System.out.print("Action not found. Enter 1 for login, or 0 to exit the application:");
                    option = in.next();
                }

                if (option.equals("1")) {
                    adminLogin = adminLogin(database);
                }
            }
            exit = true;
        }

    }

    private static boolean adminEmailCreation(EmailDatabase database) throws Exception {

        Scanner in = new Scanner(System.in);
        String firstName, lastName, password, passwordRepeat;

        System.out.println("It is time to create you administration login data! Please enter your information below.");
        System.out.print("Enter your first name: ");
        firstName = in.next();
        System.out.print("Enter your last name: ");
        lastName = in.next();
        System.out.print("Enter your password: ");
        password = in.next();
        System.out.print("Please repeat your password: ");
        passwordRepeat = in.next();

        while (!password.equals(passwordRepeat)) {
            System.out.println("The passwords do not match, please repeat the steps!");
            System.out.print("Enter your password: ");
            password = in.next();
            System.out.print("Please repeat your password: ");
            passwordRepeat = in.next();
        }

        Email adminEmail = new Email();
        adminEmail.setFirstName(firstName);
        adminEmail.setLastName(lastName);
        adminEmail.setEmail();
        adminEmail.setPassword(password);
        adminEmail.setDepartment("Administration");
        adminEmail.setMailboxCapacity(100);
        database.addEmail(adminEmail);

        System.out.println("Administration email " + adminEmail.getEmail() + " created. Please login.");
        String emailMessage = "Welcome to EmailApp, please login with your email: " + adminEmail.getEmail()
                + ", and password: " + adminEmail.getPassword();
        EmailUtil.sendMail(adminEmail.getEmail(), emailMessage);
        System.out.println("");
        return true;
    }

    private static boolean adminLogin(EmailDatabase database) {

        Scanner in = new Scanner(System.in);
        String adminPasswordString, adminEmailString;
        System.out.println("Please login with your administration email.");
        System.out.print("Enter your email: ");
        adminEmailString = in.next();

        while (!adminEmailString.equals(database.getEmail(adminEmailString).getEmail())) {
            System.out.print("Admin email not found, please try again: ");
            adminEmailString = in.next();
        }

        System.out.print("Enter your password: ");
        adminPasswordString = in.next();

        while (!adminPasswordString.equals(database.getEmail(adminEmailString).getPassword())) {
            System.out.print("Password incorrect, please try again: ");
            adminPasswordString = in.next();
        }

        System.out.println("");
        System.out.println("Login successfull. Please choose your action: ");

        return true;
    }

    private static boolean actionMenu(EmailDatabase database) throws Exception {

        Scanner in = new Scanner(System.in);
        boolean logedIn = true;
        String option;

        while (logedIn) {
            System.out.println("0 - Logout");
            System.out.println("1 - Create email");
            System.out.println("2 - View email");
            System.out.println("3 - Edit email");
            System.out.println("4 - Delete email");
            System.out.println("5 - List all emails");
            System.out.print("Action: ");
            option = in.next();
            System.out.println("");

            if (option.equals("0")) {
                logedIn = false;
            } else if (option.equals("1")) {
                createEmail(database);
            } else if (option.equals("2")) {
                viewEmail(database);
            } else if (option.equals("3")) {
                editEmail(database);
            } else if (option.equals("4")) {
                deleteEmail(database);
            } else if (option.equals("5")) {
                database.listEmails();
                System.out.println("");
            } else {
                System.out.println("Action not found. Please enter 0, 1, 2, 3, 4 or 5.");
                System.out.println("");
            }

        }

        System.out.println("You are logged out.");
        return false;
    }

    private static void createEmail(EmailDatabase database) throws Exception {

        Scanner in = new Scanner(System.in);
        boolean legit = true;
        String firstName, lastName, option;
        String department = null;

        System.out.println(
                "Welcome to the Email creation screen. To create an email, please enter the asked information.");
        System.out.print("Enter the firstname of the user: ");
        firstName = in.next();
        System.out.print("Enter your lastname of the user: ");
        lastName = in.next();

        System.out.println("");
        System.out.println("Select select a department:");
        System.out.println("1 - Dev");
        System.out.println("2 - Sys");
        System.out.println("3 - Data");
        System.out.println("4 - Tester");

        System.out.print("Action: ");

        while (legit) {
            option = in.next();
            System.out.println("");

            if (option.equals("1")) {
                department = "Dev";
                legit = false;
            } else if (option.equals("2")) {
                department = "Sys";
                legit = false;
            } else if (option.equals("3")) {
                department = "Data";
                legit = false;
            } else if (option.equals("4")) {
                department = "Tester";
                legit = false;
            } else {
                System.out.println("Action not found. Please enter 1, 2, 3 or 4.");
                System.out.println("");
            }
        }

        Email email = new Email(firstName, lastName, department);
        database.addEmail(email);
        System.out.println("Email: " + email.getEmail() + " created! ");
        String emailMessage = "Welcome to EmailApp, please login with your email: " + email.getEmail()
                + ", and password: " + email.getPassword();
        EmailUtil.sendMail(email.getEmail(), emailMessage);
        System.out.println("");
    }

    private static void viewEmail(EmailDatabase database) {

        Scanner in = new Scanner(System.in);
        boolean legit = true;
        String email, lastName, option;
        String department = null;

        System.out.print("Please enter the email to view the details: ");
        email = in.next();

        while (!email.equals(database.getEmail(email).getEmail())) {
            System.out.print("Email not found, please try again: ");
            email = in.next();
        }

        System.out.println("");
        System.out.println(database.getEmail(email).viewEmail());
        System.out.println("");

    }

    private static void editEmail(EmailDatabase database) {

        Scanner in = new Scanner(System.in);
        boolean legit = true;

        String email, option;

        System.out.print("Please enter the email to edit the details: ");
        email = in.next();

        while (!email.equals(database.getEmail(email).getEmail())) {
            System.out.print("Email not found, please try again: ");
            email = in.next();
        }

        while (legit) {
            System.out.println("0 - Back");
            System.out.println("1 - Edit name");
            System.out.println("2 - Edit department");
            System.out.println("3 - Edit password");
            System.out.println("4 - Edit mailbox capacity");
            System.out.print("Action: ");
            option = in.next();
            System.out.println("");

            if (option.equals("0")) {
                legit = false;
            } else if (option.equals("1")) {

                String firstName, lastName;

                System.out.print("Enter the firstname of the user: ");
                firstName = in.next();
                System.out.print("Enter your lastname of the user: ");
                lastName = in.next();

                database.getEmail(email).setFirstName(firstName);
                database.getEmail(email).setLastName(lastName);
                database.getEmail(email).setEmail();
                System.out.println("Name changed successfully. The new email for this user is: " + database
                        .getEmail(firstName.toLowerCase() + "_" + lastName.toLowerCase() + "@emailapp.com").getEmail());
                System.out.println("");

            } else if (option.equals("2")) {
                if (!database.getEmail(email).getDepartment().equals("Administration")) {
                    boolean departmentOk = true;
                    String department = null;

                    System.out.println("Select select a department:");
                    System.out.println("1 - Dev");
                    System.out.println("2 - Sys");
                    System.out.println("3 - Data");
                    System.out.println("4 - Tester");

                    System.out.print("Action: ");

                    while (departmentOk) {
                        option = in.next();
                        System.out.println("");

                        if (option.equals("1")) {
                            department = "Dev";
                            departmentOk = false;
                        } else if (option.equals("2")) {
                            department = "Sys";
                            departmentOk = false;
                        } else if (option.equals("3")) {
                            department = "Data";
                            departmentOk = false;
                        } else if (option.equals("4")) {
                            department = "Tester";
                            departmentOk = false;
                        } else {
                            System.out.println("Action not found. Please enter 1, 2, 3 or 4.");
                            System.out.println("");
                        }
                    }
                    database.getEmail(email).setDepartment(department);
                    System.out.println("Department changed!");
                    System.out.println("");
                } else {
                    System.out.println("Department can not be changed for an administration account.");
                    System.out.println("");
                }
            } else if (option.equals("3")) {

                String password, passwordRepeat;

                System.out.print("Enter new password: ");
                password = in.next();
                System.out.print("Please repeat the password: ");
                passwordRepeat = in.next();

                while (!password.equals(passwordRepeat)) {
                    System.out.println("The passwords do not match, please repeat the steps!");
                    System.out.print("Enter your password: ");
                    password = in.next();
                    System.out.print("Please repeat your password: ");
                    passwordRepeat = in.next();
                }

                database.getEmail(email).setPassword(password);
                System.out.println("Password changed!");
                System.out.println("");
            } else if (option.equals("4")) {

                int mailBoxCapacity;

                System.out.print("Enter new mailbox capacity: ");

                do {
                    try {
                        String s = in.next();
                        mailBoxCapacity = Integer.parseInt(s);
                        break;
                    } catch (Exception e) {
                        System.out.print("Input a number, please try again: ");
                    }
                } while (true);

                database.getEmail(email).setMailboxCapacity(mailBoxCapacity);
                System.out.println("Mailbox capacity changed!");
                System.out.println("");
            } else {
                System.out.println("Action not found. Please enter 0, 1, 2 or 3.");
                System.out.println("");
            }
        }
    }

    private static void deleteEmail(EmailDatabase database) {

        Scanner in = new Scanner(System.in);
        String email, answer = "y";

        System.out.print("Please enter the email to delete: ");
        email = in.next();

        while (!email.equals(database.getEmail(email).getEmail())) {
            System.out.print("Email not found, please try again: ");
            email = in.next();
        }

        if (database.getEmail(email).getDepartment().equals("Administration")) {
            answer = "n";
            System.out.println("");
            System.out.println("Administration email can not be deleted.");
        }

        if (!answer.equals("n")) {
            System.out.print("Are you sure? Y or N: ");
            answer = in.next().toLowerCase();
            while (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("");
                System.out.print("Action not found. Enter Y for confirmation, or N to exit: ");
                answer = in.next().toLowerCase();
            }
        }

        if (answer.equals("y")) {
            database.deleteEmail(email);
            System.out.println("Email removed! ");
            System.out.println("");
        } else {
            System.out.println("Action aborted!");
            System.out.println("");
        }
    }
}
