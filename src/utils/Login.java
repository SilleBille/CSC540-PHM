package utils;

import sql.EncryptDecrypt;
import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by mkdin on 16-10-2016.
 */
public class Login {
    private static PreparedStatement pst;

    public static boolean doLogin(Connection connection, String userName, String password) {
        try {
            pst = connection.prepareStatement(SqlQueries.SQL_LOGIN);

            pst.setString(1, userName);
            pst.setString(2, EncryptDecrypt.encrypt(password));


            System.out.println("Password: " + EncryptDecrypt.encrypt(password));
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null)
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return true;
    }

    public static boolean signup(Connection connection) {
        final double pid = Math.random() * 99999 + 1;
        String username;
        String password;
        String name = "";
        String dob = "";
        String ssn = "";
        String gender = "";
        String address = "";
        String status; // TODO: This should be automatically generated! But, how?!?!

        // To check whether the user is a patient or supporter
        boolean isPatient = false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your desired userID: ");
        username = scanner.next();
        System.out.print("Enter your desired password: ");
        password = scanner.next();
        String encryptedPass = EncryptDecrypt.encrypt(password);

        System.out.print("Enter your type: \n" +
                "1. Patient\n" +
                "2. Supporter\n");
        switch (scanner.nextInt()) {

            case 1:
                scanner.nextLine(); // Because it's taking \n in the next input!
                System.out.print("Enter your name (patient): ");
                name = scanner.nextLine();

                System.out.print("DOB (MM/DD/YYYY): ");
                dob = scanner.next();

                System.out.print("SSN: ");
                ssn = scanner.next();

                System.out.print("Gender: ");
                gender = scanner.next().toUpperCase();

                scanner.nextLine(); // To skip the \n input from previous
                System.out.print("Address: ");
                address = scanner.nextLine();

                isPatient = true;

                break;
            case 2:
                scanner.nextLine(); // Because it's taking \n in the next input!
                System.out.print("Enter your name (Supporter): ");
                name = scanner.nextLine();

                System.out.print("Enter your SSN: ");
                ssn = scanner.next();

                isPatient = false;
                break;
            case 3:
                return false;
        }


        try {
            pst = connection.prepareStatement(SqlQueries.SQL_INSERT_USER_USERTABLE);
            pst.setDouble(1, pid);
            pst.setString(2, username);
            pst.setString(3, encryptedPass);

            pst.execute();

            if (isPatient) {
                // PID, SSN, PNAME, DOB, GENDER, ADDR, STATUS
                pst = connection.prepareStatement(SqlQueries.SQL_INSERT_PATIENT_TABLE);
                pst.setDouble(1, pid);
                pst.setString(2, ssn);
                pst.setString(3, name);
                pst.setString(4, dob);
                pst.setString(5, gender);
                pst.setString(6, address);
                pst.setString(7, "w"); // TODO: Need to change this!!!!

                pst.execute();

            } else {
                // SID, SSN, SNAME
                pst = connection.prepareStatement(SqlQueries.SQL_INSERT_SUPPORTER_TABLE);
                pst.setDouble(1, pid);
                pst.setString(2, ssn);
                pst.setString(3, name);

                pst.execute();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pst != null) try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
