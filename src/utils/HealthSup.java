package utils;

import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 *
 * Needs uid passed from main login
 *
 */
public class HealthSup {

    public static void profileMod() {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        while (selection != 3) {
            System.out.print("Profile Menu\n\n" +
                    "Would you like to add or edit supporters (1-2):\n" +
                    "1. View Supporters\n" +
                    "2. Edit Add Supports\n" +
                    "3. Exit to Main Menu");

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    viewSupporters(Userid.USER_ID_STATIC);
                    break;
                case 2:
                    editSupporters();
                    break;
                case 3:
                    break;
                default:
                    break;
            }//end switch
        }
    }

    private static void viewSupporters(String userID)
    {
        //needs uid to find supporters for user
        //execute sql statement to return supporters to uid
        System.out.print("results here");
    }

    private static void editSupporters(String userID)
    {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        //needs uid to find supporters for user - user UserID.USER_ID_STATIC
        //execute sql statement to return supporters to uid
        System.out.println("name and sid here");

        System.out.println("Which supporter would you like to edit?");

        selection = s.nextInt(); //hold sid to edit

        //form sql around sid selection

        //edit fields here
    }

}
