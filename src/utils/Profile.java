package utils;

import sql.EncryptDecrypt;
import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 */
public class Profile {

    public static void profileMod(Connection con) {
        int selection = 1;

        Scanner s = new Scanner(System.in);

        while (selection != 3) {
            System.out.println("\nProfile Menu\n\n" +
                    "Please make a selection (1-3):\n" +
                    "1. View Profile\n" +
                    "2. Edit Profile\n" +
                    "3. Exit to Main Menu");

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    viewProfile(con);
                    break;
                case 2:
                    editProfile(con);
                    break;
                case 3:
                    break;
                default:
                    break;
            }//end switch
        }
    }

    private static void viewProfile(Connection con) {

        try {
            if (Userid.IS_SUPPORTER) {
                PreparedStatement ps = con.prepareStatement(SqlQueries.SQL_SELECT_USER_DETAILS_SUPPORTER);
                ps.setInt(1, Userid.USER_ID_STATIC);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {// Move cursor to first record (and only)

                    System.out.println(
                            "UID: " + rs.getInt("U_ID") + "\n" +
                                    "DOB: " + rs.getString("DOB") + "\n" +
                                    "NAME: " + rs.getString("NAME") + "\n" +
                                    "ADDRESS: " + rs.getString("ADDRESS") + "\n" +
                                    "GENDER: " + rs.getString("GENDER") + "\n");
                }
            } else {
                PreparedStatement ps = con.prepareStatement(SqlQueries.SQL_SELECT_USER_DETAILS_PATIENT);
                ps.setInt(1, Userid.USER_ID_STATIC);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {// Move cursor to first record (and only)

                    System.out.println(
                            "UID: " + rs.getInt("U_ID") + "\n" +
                                    "DOB: " + rs.getString("DOB") + "\n" +
                                    "NAME: " + rs.getString("NAME") + "\n" +
                                    "ADDRESS: " + rs.getString("ADDRESS") + "\n" +
                                    "GENDER: " + rs.getString("GENDER") + "\n" +
                                    "CATEGORY: " + rs.getString("STATUS"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void editProfile(Connection con) {
        String dobQuery;
        String nameQuery;
        String addrQuery;
        String genderQuery;
        String passwordQuery;

        PreparedStatement ps;
        Scanner s = new Scanner(System.in);
        try {

            System.out.println("Enter Password: ");
            passwordQuery = s.next();
            ps = con.prepareStatement(SqlQueries.SQL_SET_USER_DETAILS_PASSWORD);
            ps.setString(1, EncryptDecrypt.encrypt(passwordQuery));
            ps.setInt(2, Userid.USER_ID_STATIC);
            ps.execute();


            System.out.println("Enter DOB: ");
            dobQuery = s.next();
            ps = con.prepareStatement(SqlQueries.SQL_SET_USER_DETAILS_DOB);
            ps.setString(1, dobQuery);
            ps.setInt(2, Userid.USER_ID_STATIC);
            ps.execute();
            System.out.println("DOB set to " + dobQuery);

            System.out.println("Enter Name");
            s.nextLine();
            nameQuery = s.nextLine();
            ps = con.prepareStatement(SqlQueries.SQL_SET_USER_DETAILS_NAME);
            ps.setString(1, nameQuery);
            ps.setInt(2, Userid.USER_ID_STATIC);
            ps.execute();
            System.out.println("Name set to " + nameQuery);

            System.out.println("Enter Address");
            addrQuery = s.nextLine();
            ps = con.prepareStatement(SqlQueries.SQL_SET_USER_DETAILS_ADDRESS);
            ps.setString(1, addrQuery);
            ps.setInt(2, Userid.USER_ID_STATIC);
            ps.execute();
            System.out.println("Address set to " + addrQuery);

            System.out.println("Enter Gender");
            genderQuery = s.next().toUpperCase();
            ps = con.prepareStatement(SqlQueries.SQL_SET_USER_DETAILS_GENDER);
            ps.setString(1, genderQuery);
            ps.setInt(2, Userid.USER_ID_STATIC);
            ps.execute();
            System.out.println("Gender set to " + genderQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
