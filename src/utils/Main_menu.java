package utils;
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
 */
public class Main_menu {

    public static void displayMenu()
    {
        int selection;

        Scanner s = new Scanner(System.in);

        System.out.print("Main Menu\n\n" +
                "Please make a selection (1-6):\n" +
                "1. Profile\n" +
                "2. Diagnoses\n" +
                "3. Health Indicator\n" +
                "4. Alerts\n" +
                "5. Health Supporters\n" +
                "6. Logout\n");

        try{
            s.nextInt();
        } catch (Exception e){
            System.out.print("\n\nPlease enter a valid selection (1-6)");
            displayMenu();
        } finally{
            switch (s.nextInt()) {

                case 1:
                    profile();
                    break;
                case 2:
                    diagnoses();
                    break;
                case 3:
                    health_ind();
                    break;
                case 4:
                    alerts();
                    break;
                case 5:
                    health_sup();
                    break;
                case 6:
                    logout();
                default:

                    break;
            }//end switch
        }//end try catch
    }//end of main

    private static void profile()
    {

    }//end profile

    private static void diagnoses()
    {

    }//end diagnoses

    private static void health_ind()
    {

    }//end health_ind

    private static void alerts()
    {

    }//end alerts

    private static void health_sup()
    {

    }//end health_sup

    private static void logout()
    {

    }//end logout
}
