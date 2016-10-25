package utils;

import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static utils.Alerts.con;
import static utils.HealthInd.rs;

/**
 * Created by Tyler on 10/19/2016.
 *
 * Needs uid passed from main login
 *
 */
public class HealthSup {


    static PreparedStatement ps;

    public static void profileMod(Connection con) {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        while (selection != 4) {
            System.out.print("Profile Menu\n\n" +
                    "Would you like to add or edit supporters (1-3):\n" +
                    "1. View Supporters\n" +
                    "2. Edit Supports\n" +
                    "3. Add Supporters\n" +
                    "3. Exit to Main Menu");

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    try {
                        viewSupporters(con);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    removeSupporters(Userid.USER_ID_STATIC);
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
            }//end switch
        }
    }

    private static void viewSupporters(Connection con) throws SQLException {
        //needs uid to find supporters for user
        //TODO execute sql statement to return supporters to uid
        System.out.println("Available Supporters: ");
        ps = con.prepareStatement(SqlQueries.SQL_LIST_ALL_SUPPORTERS);
        rs = ps.executeQuery();
        System.out.println("SID   |   Supporter");
        while (rs.next()) {
            System.out.println(rs.getInt("SID") + "   |   " + rs.getInt("U_ID"));
        }
       // System.out.print("results here");
    }

    private static void removeSupporters(int userID)
    {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        //needs uid to find supporters for user - user UserID.USER_ID_STATIC
        //TODO execute sql statement to return supporters to uid
        System.out.println("name and sid here");

        System.out.println("Which supporter would you like to remove? Please enter their SID.");

        selection = s.nextInt(); //hold sid to edit

        //TODO form sql around sid selection


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
        /*if (rs.next()) {
            if (rs.getInt(1) == 0) {
                ps = con.prepareStatement(SqlQueries.SQL_ADD_DIAGNOSIS);
                ps.setInt(1, diagId);
                ps.setInt(2, Userid.PID_STATIC);
                ps.execute();
            } else {
                ps = con.prepareStatement(SqlQueries.SQL_UPDATE_DIAGNOSIS);
                ps.setInt(1, diagId);
                ps.setInt(2, Userid.PID_STATIC);
                ps.execute();
            }
        }*/

        System.out.println("Supporter " + sup_id + " added.");



        //TODO  use Date() to create jbdc compatible date object - how can we handle format?

        //TODO INSERT statement to form relationship between userID's in Support table.
    }

}
