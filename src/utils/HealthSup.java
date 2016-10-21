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

        while (selection != 4) {
            System.out.print("Profile Menu\n\n" +
                    "Would you like to add or edit supporters (1-2):\n" +
                    "1. View Supporters\n" +
                    "2. Edit Supports\n" +
                    "3. Add Supporters\n" +
                    "3. Exit to Main Menu");

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    viewSupporters(Userid.USER_ID_STATIC);
                    break;
                case 2:
                    removeSupporters(Userid.USER_ID_STATIC);
                    break;
                case 3:
                    addSupporters(Userid.USER_ID_STATIC);
                    break;
                case 4:
                    break;
                default:
                    break;
            }//end switch
        }
    }

    private static void viewSupporters(int userID)
    {
        //needs uid to find supporters for user
        //execute sql statement to return supporters to uid
        System.out.print("results here");
    }

    private static void removeSupporters(int userID)
    {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        //needs uid to find supporters for user - user UserID.USER_ID_STATIC
        //execute sql statement to return supporters to uid
        System.out.println("name and sid here");

        System.out.println("Which supporter would you like to remove? Please enter their SID.");

        selection = s.nextInt(); //hold sid to edit

        //form sql around sid selection


    }

    private static void addSupporters(int userID)
    {
        Scanner s = new Scanner(System.in);
        String sUid = null;
        String aDate = null;

        System.out.println("Please enter the UID of the supporter you would like to add.");
        sUid = s.nextLine();
        System.out.println("Please enter the authorization date of this supporter."); //need to find and add date format
        aDate = s.nextLine();

        // use Date() to create jbdc compatible date object - how can we handle format?

        //INSERT statement to form relationship between userID's in Support table.
    }

}
