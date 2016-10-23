package utils;

import sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 */
public class Diagnoses {
    static PreparedStatement ps;
    static ResultSet rs;
    static Scanner s;

    public static void diagnosisMod(Connection con) {
        int selection = 1;

        s = new Scanner(System.in);
        try {
            while (selection != 4) {
                System.out.print("\nProfile Menu\n\n" +
                        "Please make a selection (1-4):\n" +
                        "1. View Diagnoses\n" +
                        "2. Add Diagnoses\n" +
                        "3. Remove Diagnoses\n" +
                        "4. Exit to Main Menu");
                /*if (Userid.IS_SUPPORTER)
                    System.out.println("5. Add a new Diagnosis");*/

                selection = s.nextInt();

                switch (selection) {
                    case 1:
                        viewDiagnoses(con);
                        break;
                    case 2:
                        addDiagnoses(con);
                        break;
                    case 3:
                        removeDiagnoses(con);
                        break;
                    case 4:
                        break;
                    case 5:
                        //addNewDiagnosis(con);
                        break;
                    default:
                        break;
                }//end switch
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewDiagnoses(Connection con) throws Exception {

        if (!Userid.IS_SUPPORTER) {
            ps = con.prepareStatement(SqlQueries.SQL_GET_DIAGNOSIS_FOR_PATIENT);
            ps.setInt(1, Userid.PID_STATIC);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("DID: " + rs.getInt("DID"));
                System.out.println("Disease Name: " + rs.getString("DNAME"));
            }
        } else {
            System.out.println("Available Patients for you to view: ");
            ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
            ps.setInt(1, Userid.USER_ID_STATIC);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("PID: " + rs.getInt("PID"));
            }


            System.out.println("Enter PID to view Diagnosis: ");
            int pid = s.nextInt();
            ps = con.prepareStatement(SqlQueries.SQL_GET_DIAGNOSIS_FOR_PATIENT);
            ps.setInt(1, pid);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("DID: " + rs.getInt("DID"));
                System.out.println("Disease Name: " + rs.getString("DNAME"));
            }

        }

    }

    private static void addDiagnoses(Connection con) throws Exception {
        s = new Scanner(System.in);
        if (!Userid.IS_SUPPORTER) {
            System.out.println("Available Diagnoses: ");
            ps = con.prepareStatement(SqlQueries.SQL_LIST_ALL_DIAGNOSIS);
            rs = ps.executeQuery();
            System.out.println("DID   |   Diagnosis");
            while (rs.next()) {
                System.out.println(rs.getInt("DID") + "   |   " + rs.getString("DNAME"));
            }
            System.out.println("Please enter a DID to add: ");
            int diagId; // holds diagnosis id
            diagId = s.nextInt();
            ps = con.prepareStatement(SqlQueries.SQL_IS_WELL_PATIENT_DIAGNOSIS);
            ps.setInt(1, Userid.PID_STATIC);
            rs = ps.executeQuery();
            if (rs.next()) {
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
            }

            System.out.println("Diagnosis " + diagId + " added.");
        } else {
            System.out.println("Available Patients for you to view: ");
            ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
            ps.setInt(1, Userid.USER_ID_STATIC);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("PID: " + rs.getInt("PID"));
            }


            System.out.println("Enter PID to add Diagnosis: ");
            int pid = s.nextInt();

            System.out.println("Available Diagnoses: ");
            ps = con.prepareStatement(SqlQueries.SQL_LIST_ALL_DIAGNOSIS);
            rs = ps.executeQuery();
            System.out.println("DID   |   Diagnosis");
            while (rs.next()) {
                System.out.println(rs.getInt("DID") + "   |   " + rs.getString("DNAME"));
            }
            System.out.println("Please enter a DID to add: ");
            int diagId; // holds diagnosis id
            diagId = s.nextInt();
            ps = con.prepareStatement(SqlQueries.SQL_IS_WELL_PATIENT_DIAGNOSIS);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    ps = con.prepareStatement(SqlQueries.SQL_ADD_DIAGNOSIS);
                    ps.setInt(1, diagId);
                    ps.setInt(2, pid);
                    ps.execute();
                } else {
                    ps = con.prepareStatement(SqlQueries.SQL_UPDATE_DIAGNOSIS);
                    ps.setInt(1, diagId);
                    ps.setInt(2, pid);
                    ps.execute();
                }
            }

            System.out.println("Diagnosis " + diagId + " added for pid: " + pid);
        }

    }

    private static void removeDiagnoses(Connection con) throws Exception {
        if (!Userid.IS_SUPPORTER) {
            int count = 0;
            System.out.println("Diagnoses you can remove: ");
            ps = con.prepareStatement(SqlQueries.SQL_DIAGNOSIS_FOR_PATIENT);
            ps.setInt(1, Userid.PID_STATIC);
            rs = ps.executeQuery();
            System.out.println("DID    |   DNAME");
            while (rs.next()) {
                System.out.println(rs.getInt("DID") + "  |  " + rs.getString("DNAME"));
                count++;
            }
            System.out.println("Please enter a Diagnosis ID to remove.");
            int diagId; // holds diagnosis name
            s = new Scanner(System.in);
            diagId = s.nextInt();

            if (count == 1) {
                // Need to update the diagnosis to WELL
                ps = con.prepareStatement(SqlQueries.SQL_UPDATE_DIAGNOSIS_TO_WELL_PATIENT);
                ps.setInt(1, Userid.PID_STATIC);
                ps.setInt(2, diagId);
                ps.execute();
            } else {
                // Delete the diagnosis
                ps = con.prepareStatement(SqlQueries.SQL_DELETE_DIAGNOSIS);
                ps.setInt(1, Userid.PID_STATIC);
                ps.setInt(2, diagId);
                ps.execute();
            }

            System.out.println("Diagnosis " + diagId + "removed.\n\n");
        } else {
            int count = 0;
            /* Supporter Related */
            System.out.println("Available Patients for you to view: ");
            ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
            ps.setInt(1, Userid.USER_ID_STATIC);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("PID: " + rs.getInt("PID"));
            }


            System.out.println("Enter PID to remove Diagnosis: ");
            int pid = s.nextInt();

            /* Supporter Related ends */

            System.out.println("Diagnoses you can remove: ");
            ps = con.prepareStatement(SqlQueries.SQL_DIAGNOSIS_FOR_PATIENT);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            System.out.println("DID    |   DNAME");
            while (rs.next()) {
                System.out.println(rs.getInt("DID") + "  |  " + rs.getString("DNAME"));
                count++;
            }
            System.out.println("Please enter a Diagnosis ID to remove.");
            int diagId; // holds diagnosis name
            s = new Scanner(System.in);
            diagId = s.nextInt();

            if (count == 1) {
                // Need to update the diagnosis to WELL
                ps = con.prepareStatement(SqlQueries.SQL_UPDATE_DIAGNOSIS_TO_WELL_PATIENT);
                ps.setInt(1, pid);
                ps.setInt(2, diagId);
                ps.execute();
            } else {
                // Delete the diagnosis
                ps = con.prepareStatement(SqlQueries.SQL_DELETE_DIAGNOSIS);
                ps.setInt(1, pid);
                ps.setInt(2, diagId);
                ps.execute();
            }

            System.out.println("Diagnosis " + diagId + "removed for " + pid + "\n\n");
        }
    }

   /* static void addNewDiagnosis(Connection con) {
        String dName;
        int WEIGHT_LOWER;
        int WEIGHT_UPPER;
        int WEIGHT_FREQ;
        int BPS_LOWER;
        int BPS_UPPER;
        int BPD_LOWER;
        int BPD_UPPER;
        int BP_FREQ;
        int OXY_LOWER;
        int OXY_UPPER;
        int OXY_FREQ;
        int PAIN;
        int PAIN_FREQ;
        int TEMP_LOWER;
        int TEMP_UPPER;
        int TEMP_FREQ;
        String MOOD;
        int MOOD_FREQ;

        System.out.println("Enter the Diagnosis Name: ");
        s = new Scanner(System.in);

        dName = s.next();

        System.out.println("Enter Recommended Lower Weight : " );
        WEIGHT_LOWER = s.nextInt();
        System.out.println("Enter Recommended Upper Weight: " );
        WEIGHT_UPPER = s.nextInt();
        System.out.println("Enter Recommended Weight Check Frequency: " );
        WEIGHT_FREQ = s.nextInt();
        System.out.println("Enter Recommended Lower BPS : " );
        BPS_LOWER = s.nextInt();
        System.out.println("Enter Recommended Upper BPS: " );
        BPS_UPPER = s.nextInt();
        System.out.println("Enter Recommended Lower BPD : " );
        BPD_LOWER = s.nextInt();
        System.out.println("Enter Recommended Upper BPD: " );
        BPD_UPPER = s.nextInt();
        System.out.println("Enter Recommended BP Check Frequency: " );
        BP_FREQ = s.nextInt();
        System.out.println("Enter Recommended Lower Oxygen : " );
        OXY_LOWER = s.nextInt();
        System.out.println("Enter Recommended Upper Oxygen: " );
        OXY_UPPER = s.nextInt();
        System.out.println("Enter Recommended Oxygen Check Frequency: " );
        OXY_FREQ = s.nextInt();
        System.out.println("Enter Recommended Pain : " );
        PAIN = s.nextInt();
        System.out.println("Enter Recommended Pain Check Frequency: " );
        PAIN_FREQ = s.nextInt();
        System.out.println("Enter Recommended Lower Temperature : " );
        TEMP_LOWER = s.nextInt();
        System.out.println("Enter Recommended Upper Temperature: " );
        TEMP_UPPER = s.nextInt();
        System.out.println("Enter Recommended Temperature Check Frequency: " );
        TEMP_FREQ = s.nextInt();
        System.out.println("Enter Recommended Mood {HAPPY, SAD, NEUTRAL}: " );
        MOOD = s.next().toUpperCase();
        System.out.println("Enter Recommended Mood Check Frequency: " );
        MOOD_FREQ = s.nextInt();







    }*/
}
