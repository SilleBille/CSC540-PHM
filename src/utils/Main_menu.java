package utils;

import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Tyler on 10/18/2016.
 * <p>
 * Description:
 * Presents the user with the systems main menu.
 * User inputs a single integer for item selection.
 * Queries reflecting the user selection are executed
 * upon receipt of user input
 * <p>
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
            if (rs.next()) {
                if (rs.getInt(1) == 1) {
                    Userid.IS_SUPPORTER = true;
                }
            }

            PreparedStatement ps2 = con.prepareStatement(SqlQueries.SQL_FIND_IS_PATIENT);
            ps2.setInt(1, Userid.USER_ID_STATIC);
            ResultSet rs2 = ps.executeQuery();
            if (rs2.next()) {
                if (rs2.getInt(1) == 1) {
                    Userid.IS_PATIENT = true;
                }
            }

            if (Userid.IS_PATIENT == true && Userid.IS_SUPPORTER == true)
            {
                System.out.println("\n\n Would you like to login as a patient or a supporter? (P or S)");
                String psSelect = s.nextLine().toUpperCase();
                System.out.print("\n\n");

                if(psSelect.equals("P"))
                {
                    Userid.IS_SUPPORTER = false;
                }
                else
                {
                    Userid.IS_PATIENT = false;
                }
            }

            //if they aren't a supporter
            if (!Userid.IS_SUPPORTER) {
                ps = con.prepareStatement(SqlQueries.SQL_FIND_PID_FOR_PATIENT);
                ps.setInt(1, Userid.USER_ID_STATIC);
                rs = ps.executeQuery();
                if (rs.next()) {
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
                            HealthInd.healthIndMenu(con);
                            break;
                        case 4:
                            Alerts.view(Userid.USER_ID_STATIC);
                            break;
                        case 5:
                            HealthSup.profileMod(con);
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
                while (selection != 5) {
                    System.out.print("Main Menu\n\n" +
                            "Please make a selection (1-6):\n" +
                            "1. Profile\n" +
                            "2. Diagnoses\n" +
                            "3. Health Indicator\n" +
                            "4. Alerts\n" +
                            "5. Logout\n");

                    selection = s.nextInt();

                    switch (selection) {
                        case 1:
                            Profile.profileMod(con);
                            break;
                        case 2:
                            Diagnoses.diagnosisMod(con);
                            break;
                        case 3:
                            HealthInd.healthIndMenu(con);
                            break;
                        case 4:
                            Alerts.view(Userid.USER_ID_STATIC);
                            break;
                        case 5:
                            Userid.USER_ID_STATIC = 0;
                            Userid.IS_SUPPORTER = false;
                            //logout();
                            break;
                        default:
                            break;
                    }//end switch
                }//end while
            }//end if
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end main
}//end class


