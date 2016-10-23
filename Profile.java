package utils;

import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 */
public class Profile {

    public static void profileMod()
    {
        int selection = 1;

        Scanner s = new Scanner(System.in);

        while (selection != 3) {
            System.out.print("Profile Menu\n\n" +
                    "Please make a selection (1-6):\n" +
                    "1. View Profile\n" +
                    "2. Edit Profile\n" +
                    "3. Exit to Main Menu");

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    viewProfile(Userid.USER_ID_STATIC);
                    break;
                case 2:
                    editProfile(Userid.USER_ID_STATIC);
                    break;
                case 3:
                    break;
                default:
                    break;
            }//end switch
        }
    }

    private static void viewProfile(int userID)
    {
        //TODO insert sql to return current user profile here. Must receive uid from main method
        //set at login.

        System.out.print("Query Results Here");//print query results here
    }

    private static void editProfile(int userID)
    {
        String uidQuery;
        String dobQuery;
        String nameQuery;
        String addrQuery;
        String genderQuery;
        String patCatQuery;


        Scanner s = new Scanner(System.in);

        System.out.println("Enter UID");
        uidQuery = s.nextLine();
        //TODO insert sql call here to UPDATE tuple based on uid
        System.out.println("UID set to " + uidQuery);

        System.out.println("Enter DOB");
        dobQuery = s.nextLine();
        //TODO insert sql call here to UPDATE tuple based on uid
        System.out.println("UID set to " + dobQuery);

        System.out.println("Enter Name");
        nameQuery = s.nextLine();
        //TODO insert sql call here to UPDATE tuple based on uid
        System.out.println("UID set to " + nameQuery);

        System.out.println("Enter Address");
        addrQuery = s.nextLine();
        //TODO insert sql call here to UPDATE tuple based on uid
        System.out.println("UID set to " + addrQuery);

        System.out.println("Enter Gender");
        genderQuery = s.nextLine();
        //TODO insert sql call here to UPDATE tuple based on uid
        System.out.println("UID set to " + genderQuery);

        System.out.println("Enter Patient Category");
        patCatQuery = s.nextLine();
        //TODO insert sql call here to UPDATE tuple based on uid
        System.out.println("UID set to " + patCatQuery);



    }
}
