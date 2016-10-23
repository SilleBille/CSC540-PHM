package utils;

import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 */
public class HealthInd {
    // the indicator name, low, high values, frequency, option to enter the observation
    // PID

    static Scanner s;
    static PreparedStatement ps;
    static ResultSet rs;

    public static void healthIndMenu(Connection con) {
        int selection = 1;
        s = new Scanner(System.in);
        try {
            if (!Userid.IS_SUPPORTER) {
                while (selection != 3) {
                    System.out.print("Health Indicator Menu (1-3)\n\n");
                    System.out.print("1. View Recommendations\n" +
                            "2. Enter My Observations\n" +
                            "3. Back To Main Menu\n");

                    selection = s.nextInt();

                    switch (selection) {
                        case 1:
                            viewRecommendation(con);
                            break;
                        case 2:
                            enterObs(con);
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }//end switch
                }
            } else {
                while (selection != 4) {
                    System.out.print("Health Indicator Menu (1-3)\n\n");
                    System.out.print("1. View Recommendations for a patient\n" +
                            "2. Add Observation for a patient\n" +
                            "3. Add specific recommendations for a patient\n" +
                            "4. Back To Main Menu\n");

                    selection = s.nextInt();

                    switch (selection) {
                        case 1:
                            viewRecommendationSupporter(con);
                            break;
                        case 2:
                            enterObs(con);
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }//end switch
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static public Integer getInteger(ResultSet rs, String strColName) throws Exception {
        int nValue = rs.getInt(strColName);
        return rs.wasNull() ? null : nValue;
    }

    private static void viewRecommendation(Connection con) throws Exception {
        ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
        ps.setInt(1, Userid.PID_STATIC);
        rs = ps.executeQuery();

        while (rs.next()) {
            Integer temp;
            temp = getInteger(rs, "WEIGHT_LOWER");
            if (temp != null) System.out.println("WEIGHT_LOWER: " + temp);

            temp = getInteger(rs, "WEIGHT_UPPER");
            if (temp != null) System.out.println("WEIGHT_UPPER: " + temp);

            temp = getInteger(rs, "WEIGHT_FREQ");
            if (temp != null) System.out.println("WEIGHT_FREQ: " + temp);

            temp = getInteger(rs, "BPS_LOWER");
            if (temp != null) System.out.println("BPS_LOWER: " + temp);

            temp = getInteger(rs, "BPS_UPPER");
            if (temp != null) System.out.println("BPS_UPPER: " + temp);

            temp = getInteger(rs, "BPD_LOWER");
            if (temp != null) System.out.println("BPD_LOWER: " + temp);

            temp = getInteger(rs, "BPD_UPPER");
            if (temp != null) System.out.println("BPD_UPPER: " + temp);

            temp = getInteger(rs, "BP_FREQ");
            if (temp != null) System.out.println("BP_FREQ: " + temp);

            temp = getInteger(rs, "OXY_LOWER");
            if (temp != null) System.out.println("OXYGEN_LOWER: " + temp);

            temp = getInteger(rs, "OXY_UPPER");
            if (temp != null) System.out.println("OXYGEN_UPPER: " + temp);

            temp = getInteger(rs, "OXY_FREQ");
            if (temp != null) System.out.println("OXYGEN_FREQUENCY: " + temp);

            temp = getInteger(rs, "PAIN");
            if (temp != null) System.out.println("PAIN: " + temp);

            temp = getInteger(rs, "PAIN_FREQ");
            if (temp != null) System.out.println("PAIN_FREQ: " + temp);

            temp = getInteger(rs, "TEMP_LOWER");
            if (temp != null) System.out.println("TEMP_LOWER: " + temp);

            temp = getInteger(rs, "TEMP_UPPER");
            if (temp != null) System.out.println("TEMP_UPPER: " + temp);

            temp = getInteger(rs, "TEMP_FREQ");
            if (temp != null) System.out.println("TEMP_FREQ: " + temp);

            temp = getInteger(rs, "MOOD");
            if (temp != null) System.out.println("MOOD: " + temp);

            temp = getInteger(rs, "MOOD_FREQ");
            if (temp != null) System.out.println("MOOD_FREQ: " + temp);

        }
    }

    private static void viewRecommendationSupporter(Connection con) throws Exception {
        s = new Scanner(System.in);
        System.out.println("Available Patients for you to view: ");
        ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
        ps.setInt(1, Userid.USER_ID_STATIC);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("PID: " + rs.getInt("PID"));
        }


        System.out.println("Enter PID to View their Recommendations: ");
        int pid = s.nextInt();

        ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
        ps.setInt(1, pid);
        rs = ps.executeQuery();

        while (rs.next()) {
            Integer temp;
            temp = getInteger(rs, "WEIGHT_LOWER");
            if (temp != null) System.out.println("WEIGHT_LOWER: " + temp);

            temp = getInteger(rs, "WEIGHT_UPPER");
            if (temp != null) System.out.println("WEIGHT_UPPER: " + temp);

            temp = getInteger(rs, "WEIGHT_FREQ");
            if (temp != null) System.out.println("WEIGHT_FREQ: " + temp);

            temp = getInteger(rs, "BPS_LOWER");
            if (temp != null) System.out.println("BPS_LOWER: " + temp);

            temp = getInteger(rs, "BPS_UPPER");
            if (temp != null) System.out.println("BPS_UPPER: " + temp);

            temp = getInteger(rs, "BPD_LOWER");
            if (temp != null) System.out.println("BPD_LOWER: " + temp);

            temp = getInteger(rs, "BPD_UPPER");
            if (temp != null) System.out.println("BPD_UPPER: " + temp);

            temp = getInteger(rs, "BP_FREQ");
            if (temp != null) System.out.println("BP_FREQ: " + temp);

            temp = getInteger(rs, "OXY_LOWER");
            if (temp != null) System.out.println("OXYGEN_LOWER: " + temp);

            temp = getInteger(rs, "OXY_UPPER");
            if (temp != null) System.out.println("OXYGEN_UPPER: " + temp);

            temp = getInteger(rs, "OXY_FREQ");
            if (temp != null) System.out.println("OXYGEN_FREQUENCY: " + temp);

            temp = getInteger(rs, "PAIN");
            if (temp != null) System.out.println("PAIN: " + temp);

            temp = getInteger(rs, "PAIN_FREQ");
            if (temp != null) System.out.println("PAIN_FREQ: " + temp);

            temp = getInteger(rs, "TEMP_LOWER");
            if (temp != null) System.out.println("TEMP_LOWER: " + temp);

            temp = getInteger(rs, "TEMP_UPPER");
            if (temp != null) System.out.println("TEMP_UPPER: " + temp);

            temp = getInteger(rs, "TEMP_FREQ");
            if (temp != null) System.out.println("TEMP_FREQ: " + temp);

            temp = getInteger(rs, "MOOD");
            if (temp != null) System.out.println("MOOD: " + temp);

            temp = getInteger(rs, "MOOD_FREQ");
            if (temp != null) System.out.println("MOOD_FREQ: " + temp);

        }
    }

    private static void enterObs(Connection con) throws Exception {
        int selection = 1;
        int uid = 0;
        boolean isSupporter = Userid.IS_SUPPORTER;

        s = new Scanner(System.in);

        int weight, bps, bpd, oxygen, pain, temperature;
        String w_otime, bps_otime, bpd_otime, oxygen_otime, pain_otime, temp_otime, mood_otime;
        String mood;


        if (isSupporter) {
            System.out.println("Available Patients for you to view: ");
            ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
            ps.setInt(1, Userid.USER_ID_STATIC);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("PID: " + rs.getInt("PID"));
            }


            System.out.println("Enter PID to View their Recommendations: ");
            int pid = s.nextInt();
            ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
            ps.setInt(1, pid);

        } else {
            ps = con.prepareStatement(SqlQueries.SQL_GET_RECOMMENDATION_FOR_PATIENT);
            ps.setInt(1, Userid.PID_STATIC);
        }
        rs = ps.executeQuery();

        while (rs.next()) {
            Integer temp;

            temp = getInteger(rs, "WEIGHT_FREQ");
            if (temp != null) {
                System.out.println("Enter WEIGHT: ");
                weight = s.nextInt();
                System.out.println("Enter Observed Time in (MM/DD/YYYY HH:MM:SS)");

            }


            temp = getInteger(rs, "BP_FREQ");
            if (temp != null) {
                System.out.println("Enter BPS: ");
                bps = s.nextInt();
                System.out.println("Enter BPD: ");
                bpd = s.nextInt();
            }


            temp = getInteger(rs, "OXY_FREQ");
            if (temp != null) {
                System.out.println("Enter Oxygen Saturation: ");
                oxygen = s.nextInt();
            }

            temp = getInteger(rs, "PAIN_FREQ");
            if (temp != null) {
                System.out.println("Enter pain: ");
                pain = s.nextInt();
            }


            temp = getInteger(rs, "TEMP_FREQ");
            if (temp != null) {
                System.out.println("Enter Temperature:  ");
                temperature = s.nextInt();
            }

            temp = getInteger(rs, "MOOD_FREQ");
            if (temp != null) {
                System.out.println("Enter Mood {HAPPY, SAD, NEUTRAL}");
                mood = s.next();
            }
        }
    }


}