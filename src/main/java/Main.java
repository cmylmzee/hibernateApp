import Controller.EmployeeController;
import entity.EmployeeEntity;

import java.util.Scanner;


public class Main {

    static Scanner name = new Scanner(System.in);
    static Scanner choices = new Scanner(System.in);
    static int a = 1;
    static EmployeeController employeeController = new EmployeeController();
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
                        System.out.print("Name should not be numbered!\n" +
                                "Try again!!\n");
                        firstName = name.nextLine();
                    }
                    System.out.println("Please enter the lastname\n");
                    String lastName = name.nextLine();
                    while (isDigigt(lastName)) {
                        System.out.print("Name should not be numbered!\n" +
                                "Try again!!\n");
                        lastName = name.nextLine();
                    }
                    System.out.println("Please enter the department id\n");
                    int departmentId = name.nextInt();
                    while (departmentId > 2 || departmentId < 1) {
                        System.out.print("Departmen ıd isn't valid\n" +
                                "Try again!!\n");
                        departmentId = name.nextInt();
                    }
                    employeeController.addEmployee(firstName, lastName,departmentId, password);
                    break;
                }
                case 2: {
                    employeeController.showTheEmployee(password);
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
