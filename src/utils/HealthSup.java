package utils;

import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 *
 * Needs uid passed from main login
 *
 */
public class HealthSup {


    static PreparedStatement ps;
    static ResultSet rs;

    public static void profileMod(Connection con) {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        while (selection != 4) {
            System.out.print("Profile Menu\n\n" +
                    "Would you like to add or edit supporters (1-3):\n" +
                    "1. View Supporters\n" +
                    "2. Edit Supports\n" +
                    "3. Add Supporters\n" +
                    "4. Exit to Main Menu");

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    try {
                        removeSupporters(Userid.USER_ID_STATIC,con);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        removeSupporters(Userid.USER_ID_STATIC,con);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        addSupporters(con);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    break;
                default:
                    break;
            }//end switch...
        }
    }

    private static void viewSupporters(Connection con) throws Exception {
        ps = con.prepareStatement(SqlQueries.SQL_LIST_SUPPORTERS_FOR_UID);
        ps.setInt(1, Userid.PID_STATIC);
        rs = ps.executeQuery();
        System.out.println("SID and Name\n");
        while(rs.next())
        {
            System.out.print(getInteger(rs, "SID"));
            System.out.print(rs.getString("NAME") + "\n\n");
        }
    }

    private static void removeSupporters(int userID,Connection con) throws Exception {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        ps = con.prepareStatement(SqlQueries.SQL_LIST_SUPPORTERS_FOR_UID);
        ps.setInt(1, Userid.PID_STATIC);
        rs = ps.executeQuery();
        System.out.println("SID and Name\n");
        while(rs.next())
        {
            System.out.print(getInteger(rs, "SID"));
            System.out.print(rs.getString("NAME") + "\n\n");
        }

        System.out.println("Which supporter would you like to remove? Please enter their SID.\n");

        selection = s.nextInt(); //hold sid to edit

        ps = con.prepareStatement(SqlQueries.SQL_REMOVE_SUPPORTER);
        ps.setInt(1, Userid.PID_STATIC);
        ps.setInt(2, selection);
        rs = ps.executeQuery();

        System.out.println("\n\nSupporter Removed.\n\n");
    }

    private static void addSupporters(Connection con) throws Exception {
        Scanner s = new Scanner(System.in);

        int sup_id;
        String auth_date, role;

        System.out.println("Available Supporters: ");
       ps = con.prepareStatement(SqlQueries.SQL_LIST_ALL_SUPPORTERS);
        rs = ps.executeQuery();
        System.out.println("SID   |   Supporter");
        while (rs.next()) {
            System.out.println(rs.getInt("SID") + "   |   " + rs.getInt("U_ID"));
        }

        System.out.println("Please enter the SID of the supporter you would like to add: ");
        sup_id = s.nextInt();
        System.out.println("Please enter the authorization date of this supporter (e.g. 01-JAN-1990): ");
        auth_date = s.next();
        System.out.println("Enter role of supporter (PRIMARY, SECONDARY): ");
        role = s.next();



        ps = con.prepareStatement(SqlQueries.SQL_INSERT_SUPPORT_TABLE);
        ps.setInt(1, Userid.PID_STATIC);
        ps.setInt(2, sup_id);
        ps.setString(3, auth_date);
        ps.setString(4, role);
        ps.executeQuery();

        System.out.println("Supporter " + sup_id + " added.");
    }

    static public Integer getInteger(ResultSet rs, String strColName) throws Exception {
        int nValue = rs.getInt(strColName);
        return rs.wasNull() ? null : nValue;
    }
}
