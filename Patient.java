package utils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tyler on 10/21/2016.
 *
 * To be used by supporters to modify patients
 *
 */
public class Patient {

    public static void patientMod()
    {

    }

    private static void editPat(int userID)
    {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> patients = new ArrayList();
        int uidSelection = 0;

        //TODO find sid from uid and return the patients uid's of this supporter using sql

        System.out.println("Current Patients:");
        for (int i=0; i < patients.size(); i++)
        {
            System.out.println(patients.get(i));
        }
        System.out.println("\n\n");
        System.out.println("Which user would you like to modify?");
        uidSelection = s.nextInt();

        System.out.println("\n");
        System.out.println("Please enter the user's name.");
        String patName = s.nextLine();

        System.out.println("\n");
        System.out.println("Please enter the user's DOB.");
        String patDOB = s.nextLine(); //use to date with this

        System.out.println("\n");
        System.out.println("Please enter the user's gender.");
        String patGender = s.nextLine();

        System.out.println("\n");
        System.out.println("Please enter the user's address.");
        String patAddress = s.nextLine();

        System.out.println("\n");
        System.out.println("Please enter the user's patient status.");
        String patStatus = s.nextLine();
    }

    private static void viewPat(int userID)
    {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> patients = new ArrayList();
        int uidSelection = 0;

        //TODO find sid from uid and return the patients uid's of this supporter using sql

        System.out.println("Current Patients:");
        for (int i=0; i < patients.size(); i++)
        {
            System.out.println(patients.get(i));
        }
        System.out.println("\n\n");
        System.out.println("Which user would you like to view?");
        uidSelection = s.nextInt();

        //TODO return and print all values from selected patient (uidSelection)
        //name, dob, gender, address, status from patient table.
    }

}
