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
    public static void diagnosisMod(Connection con)
    {
        int selection = 1;

        s = new Scanner(System.in);

        while (selection != 4) {
            System.out.print("\nProfile Menu\n\n" +
                    "Please make a selection (1-4):\n" +
                    "1. View Diagnoses\n" +
                    "2. Add Diagnoses\n" +
                    "3. Remove Diagnoses\n" +
                    "4. Exit to Main Menu");

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
                default:
                    break;
            }//end switch
        }
    }

    private static void viewDiagnoses(Connection con)
    {
        try {
           System.out.println("PID : " + Userid.PID_STATIC);
            if(!Userid.IS_SUPPORTER) {
                ps = con.prepareStatement(SqlQueries.SQL_GET_DIAGNOSIS_FOR_PATIENT);
                ps.setInt(1, Userid.PID_STATIC);
                rs = ps.executeQuery();
                while(rs.next()) {
                    System.out.println("DID: " + rs.getInt("DID"));
                    System.out.println("Disease Name: " + rs.getString("DNAME"));
                }
            } else {
                System.out.println("Available Patients for you to view: ");
                ps = con.prepareStatement(SqlQueries.SQL_GET_PATIENT_LIST_FOR_SUPPORTER);
                ps.setInt(1, Userid.USER_ID_STATIC);
                rs = ps.executeQuery();
                while(rs.next()) {
                    System.out.println("PID: " + rs.getInt("PID"));
                }


                System.out.println("Enter PID to view Diagnosis: ");
                int pid = s.nextInt();
                ps = con.prepareStatement(SqlQueries.SQL_GET_DIAGNOSIS_FOR_PATIENT);
                ps.setInt(1, pid);
                rs = ps.executeQuery();

                while(rs.next()) {
                    System.out.println("DID: " + rs.getInt("DID"));
                    System.out.println("Disease Name: " + rs.getString("DNAME"));
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void addDiagnoses(Connection con)
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Please enter a Diagnosis to add.");
        String diag; // holds diagnosis name
        diag = s.nextLine();
        //TODO INSERT sql call here

        //TODO repeat last 3 lines for all parameters and add sql INSERT call to input diagnosis

        System.out.println("Diagnosis "+ diag + " added.");
    }

    private static void removeDiagnoses(Connection con)
    {
        System.out.println("Please enter a Diagnosis to remove.");
        String diag; // holds diagnosis name

        Scanner s = new Scanner(System.in);

        diag = s.nextLine();

        //TODO sql DELETE statement for defined diagnosis from diag

        System.out.println("Diagnosis removed.\n\n");
    }
}
