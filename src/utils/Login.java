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

    public static String doLogin(Connection connection, int uid, String password) {
        try {
            pst = connection.prepareStatement(SqlQueries.SQL_LOGIN);

            pst.setInt(1, uid);
            pst.setString(2, EncryptDecrypt.encrypt(password));

            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                Userid.USER_ID_STATIC = rs.getInt("U_ID"); //sets userid for reuse in methods
                return rs.getString("NAME");
            }
            return null;
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
        return null;
    }

    public static boolean signup(Connection connection) {
        int uid;
        String password;
        String name = "";
        String dob = "";
        String ssn = "";
        String gender = "";
        String address = "";

        // To check whether the user is a patient or supporter
        boolean isPatient = false;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your desired userID: ");
        uid = scanner.nextInt();
        System.out.print("Enter your desired password: ");
        password = scanner.next();
        String encryptedPass = EncryptDecrypt.encrypt(password);

        System.out.print("Enter your name: ");
        scanner.nextLine(); // to skip \n
        name = scanner.nextLine();
        System.out.println("NAME: " + name);

        System.out.print("DOB (MM-DD-YYYY): ");
        dob = scanner.next();

        System.out.print("Gender: ");
        gender = scanner.next().toUpperCase();

        System.out.print("Address: ");
        scanner.nextLine(); // to skip \n
        address = scanner.nextLine();
        System.out.print("Enter your type: \n" +
                "1. Patient\n" +
                "2. Supporter\n");

        switch (scanner.nextInt()) {

            case 1:
                isPatient = true;

                break;
            case 2:
                isPatient = false;
                break;
        }


        try {
            pst = connection.prepareStatement(SqlQueries.SQL_INSERT_USER_USERTABLE);
            pst.setInt(1, uid);
            pst.setString(2, encryptedPass);
            pst.setString(3, name);
            pst.setString(4, dob);
            pst.setString(5, gender);
            pst.setString(6, address);

            pst.execute();

            if (isPatient) {
                pst = connection.prepareStatement(SqlQueries.SQL_INSERT_PATIENT_TABLE);
                pst.setInt(1, uid);
                pst.execute();

                pst = connection.prepareStatement(SqlQueries.SQL_FIND_PID_FOR_PATIENT);
                pst.setInt(1, uid);
                ResultSet rs = pst.executeQuery();
                if(rs.next()) {
                    pst = connection.prepareStatement(SqlQueries.SQL_INSERT_HAVE_TABLE);
                    pst.setInt(1, rs.getInt("PID"));
                    pst.execute();
                }

            } else {
                pst = connection.prepareStatement(SqlQueries.SQL_INSERT_SUPPORTER_TABLE);
                pst.setInt(1, uid);

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
