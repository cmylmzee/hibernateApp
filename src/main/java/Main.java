import Controller.ControlMenu;
import entity.EmployeeEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;


public class Main {

    static Scanner name = new Scanner(System.in);
    static Scanner choices = new Scanner(System.in);
    static int a = 1;
    static ControlMenu controlMenu = new ControlMenu();
    static EmployeeEntity employeeEntity = new EmployeeEntity();

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        System.out.print("Please type in a password to connect to the database: ");
        String password = "";
        try {
            password = in.next();
        } catch (Exception e) {
            System.out.println("Invalid password typee");
        }

        while (a != 0) {
            System.out.println("MENU\n" +
                    "1-ADD EMPLOYEE\n" +
                    "2-SHOW THE EMPLOYEE LIST\n" +
                    "3-EXIT\n");
            switch (choices.nextInt()) {
                case 1: {
                    System.out.println("Please enter the Name\n");
                    String firstName = name.nextLine();
                    while (isDigigt(firstName)) {
                        System.out.print("Name or surname should not be numbered!\n" +
                                "Try again!!\n");
                        firstName = name.nextLine();
                    }
                    String lastName = name.nextLine();
                    while (isDigigt(lastName)) {
                        System.out.print("Name or surname should not be numbered!\n" +
                                "Try again!!\n");
                        lastName = name.nextLine();
                    }
                    controlMenu.addEmployee(firstName, lastName, password);
                    break;
                }
                case 2: {
                    controlMenu.showTheEmployee(password);
                    break;
                }
                case 3: {
                    a = 0;
                    System.out.println("EXITING...");
                    break;
                }
            }
        }


    }

    public static boolean isDigigt(CharSequence passCode) {
        boolean hasDigit = false;
        for (int i = 0; i < passCode.length(); ++i) {
            if (Character.isDigit(passCode.charAt(i))) {
                hasDigit = true;
                break;
            }
        }
        return hasDigit;
    }

    public static String hashIt(String toHash) {
        return toHash;
    }

}
