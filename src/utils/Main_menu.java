package utils;

import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Tyler on 10/18/2016.
 *
 * Description:
 * Presents the user with the systems main menu.
 * User inputs a single integer for item selection.
 * Queries reflecting the user selection are executed
 * upon receipt of user input
 *
 * Inputs:
 * int - via scanner
 * int - uid from main method
 */
public class Main_menu {

    public static void displayMenu(Connection con) {
        int selection = 1;
        int uid = 0;

        Scanner s = new Scanner(System.in);

        //TODO insert sql statement to return boolean value of whether uid belongs to a health supporter or not.
        //use Userid.USER_ID_STATIC
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.SQL_FIND_IS_SUPPORTER);
            ps.setInt(1, Userid.USER_ID_STATIC);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if(rs.getInt(1) == 1) {
                    Userid.IS_SUPPORTER = true;
                }

            }

            if (!Userid.IS_SUPPORTER) {
                ps = con.prepareStatement(SqlQueries.SQL_FIND_PID_FOR_PATIENT);
                ps.setInt(1, Userid.USER_ID_STATIC);
                rs = ps.executeQuery();
                if(rs.next()) {
                    Userid.PID_STATIC = rs.getInt("PID");
                }
                while (selection != 6) {
                    System.out.print("Main Menu\n\n" +
                            "Please make a selection (1-6):\n" +
                            "1. Profile\n" +
                            "2. Diagnoses\n" +
                            "3. Health Indicator\n" +
                            "4. Alerts\n" +
                            "5. Health Supporters\n" +
                            "6. Logout\n");

                    selection = s.nextInt();

                    switch (selection) {
                        case 1:
                            Profile.profileMod(con);
                            break;
                        case 2:
                            Diagnoses.diagnosisMod(con);
                            break;
                        case 3:
                            //health_ind();
                            break;
                        case 4:
                            Alerts.view(Userid.USER_ID_STATIC);
                            break;
                        case 5:
                            HealthSup.profileMod();
                            break;
                        case 6:
                            Userid.USER_ID_STATIC = 0;
                            Userid.IS_SUPPORTER = false;
                            //logout();
                            break;
                        default:
                            break;
                    }//end switch
                }//end while
            } else {
                while (selection != 7) {
                    System.out.print("Main Menu\n\n" +
                            "Please make a selection (1-6):\n" +
                            "1. Profile\n" +
                            "2. Diagnoses\n" +
                            "3. Health Indicator\n" +
                            "4. Alerts\n" +
                            "5. Health Supporters\n" +
                            "6. Patient Edit\n" +
                            "7. Logout\n");

                    selection = s.nextInt();

                    switch (selection) {
                        case 1:
                            Profile.profileMod(con);
                            break;
                        case 2:
                            Diagnoses.diagnosisMod(con);
                            break;
                        case 3:
                            //health_ind();
                            break;
                        case 4:
                            Alerts.view(Userid.USER_ID_STATIC);
                            break;
                        case 5:
                            HealthSup.profileMod();
                            break;
                        case 6:
                            Patient.patientMod();
                            break;
                        case 7:
                            Userid.USER_ID_STATIC = 0;

                            //logout();
                            break;
                        default:
                            break;
                    }//end switch
                }//end while
            }//end if
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//end main
}//end class


