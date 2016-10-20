package utils;

import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 */
public class Diagnoses {

    public static void profileMod()
    {
        int selection = 1;

        Scanner s = new Scanner(System.in);

        while (selection != 3) {
            System.out.print("Profile Menu\n\n" +
                    "Please make a selection (1-6):\n" +
                    "1. View Profile\n" +
                    "2. Add Diagnoses\n" +
                    "3. Remove Diagnoses\n" +
                    "4. Exit to Main Menu");

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    viewDiagnoses();
                    break;
                case 2:
                    addDiagnoses();
                    break;
                case 3:
                    removeDiagnoses();
                    break;
                case 4:
                    break;
                default:
                    break;
            }//end switch
        }
    }

    private static void viewDiagnoses()
    {
        //insert sql query call to return diagnoses here
        System.out.print("Query Results Here");
    }

    private static void addDiagnoses()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Please enter a Diagnosis to add.");
        String diag; // holds diagnosis name
        diag = s.nextLine();
        //INSERT sql call here

        //repeat last 3 lines for all parameters and add sql INSERT call to input diagnosis

        System.out.println("Diagnosis "+ diag + " added.");
    }

    private static void removeDiagnoses()
    {
        System.out.println("Please enter a Diagnosis to remove.");
        String diag; // holds diagnosis name

        Scanner s = new Scanner(System.in);

        diag = s.nextLine();

        //sql DELETE statement for defined diagnosis from diag

        System.out.println("Diagnosis removed.\n\n");
    }
}
