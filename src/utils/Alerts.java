package utils;

import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;


/**
 * Created by Tyler on 10/19/2016.
 * <p>
 * Needs UID from main menu login.
 */

public class Alerts {

    private static PreparedStatement ps2;
    private static PreparedStatement ps1;
    private static ResultSet rs2;
    private static ResultSet rs1;

    static public Integer getInteger(ResultSet rss, String strColName) throws Exception {
        int nValue = rss.getInt(strColName);
        return rss.wasNull() ? null : nValue;
    }

    public static void view(Connection connn) {
        String response;
        Scanner s = new Scanner(System.in);
        Integer currAID = null;

        //return all alerts for give UID
        try {
            ps1 = connn.prepareStatement(SqlQueries.SQL_VIEW_ALERT_FOR_UID);
            ps1.setInt(1, Userid.USER_ID_STATIC);
            rs1 = ps1.executeQuery();

            while (rs1.next()) {
                // rs1.next();
                currAID = getInteger(rs1, "AID");
                System.out.println("AID = " + currAID);

                Date tempDate = rs1.getDate("ALERT_DATE");
                System.out.println("Alert Date = " + tempDate.toString());

                String tempType = rs1.getString("ALERT_TYPE");
                System.out.println("Alert Type = " + tempType);

                Integer currUID = getInteger(rs1, "U_ID");
                System.out.println("UID = " + currUID);

                String tempStatus = rs1.getString("STATUS");
                System.out.println("Alert Status = " + tempStatus);

                Integer tempPid = getInteger(rs1, "PID");
                System.out.println("PID = " + tempPid);

                String tempDesc = rs1.getString("DESCRIPTION");
                System.out.println("Description = " + tempDesc);

                System.out.println("\n\nWould you like to clear this alert? (Y/N)");
                response = s.next().toUpperCase();

                if (response.equals("Y")) {
                    if (tempType.equalsIgnoreCase("LOW-ACTIVITY")) {
                        HealthInd.enterObs(connn);
                    }
                    ps2 = connn.prepareStatement(SqlQueries.SQL_CLEAR_ALERT);
                    ps2.setInt(1, currAID);
                    rs2 = ps2.executeQuery();
                    System.out.println("Update cleared.");
                }
                System.out.print("\n\n\n");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
