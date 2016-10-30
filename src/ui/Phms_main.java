package ui;

import sql.ConnectionClass;
import utils.Login;
import utils.Main_menu;

import java.sql.Connection;
import java.util.Scanner;

/* CSC540 - DBMS - P1 - Personal Health Management System
 * Created by:
 * Dinesh Prasanth M K
 * Tianpei Xia
 * Tyler Cannon 
 * Ruth Okoilu
 */
public class Phms_main {
    private static final int LOGIN = 1;
    private static final int SIGNUP = 2;
    private static final int EXIT = 3;
    private static final int IMPLEMENTED_QUERIES = 4;
    private static Connection conn;

    private static void showMenu() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("PERSONAL HEALTH SUPPORT SYSTEM\n" +
                    "1. Login\n" +
                    "2. Signup\n" +
                    "3. Exit\n" +
                    "4. Implemented Queries\n" +
                    "Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case LOGIN:
                    System.out.print("Enter your userID: ");
                    int uid = scanner.nextInt();
                    System.out.print("Enter your pass: ");
                    String pass = scanner.next();

                    String name = Login.doLogin(conn, uid, pass);
                    if(name != null)
                    {
                        System.out.println("WELCOME Mr./Mrs. " + name);


                        Main_menu.displayMenu(conn); //displays main menu for user options
                    }
                    else {
                        System.out.println("Incorrect userid/password!");
                    }
                    break;
                case SIGNUP:
                    boolean isSignedUp = Login.signup(conn);
                    if (!isSignedUp)
                        System.out.println("Something went wrong on signup!");
                    else
                        System.out.println("User successfully added!");
                    break;
                case EXIT:
                    ConnectionClass.closeConnection();
                    System.exit(0);

                    break;
            }
        }
    }

    public static void main(String[] args) {
        conn = ConnectionClass.getDBConnection();
        if (conn != null) {
            showMenu();
        } else {
            System.out.println("Connection to DB Failed!");
        }



    }

}
