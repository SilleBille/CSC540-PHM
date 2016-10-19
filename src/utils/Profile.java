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
                    Profile.viewProfile();
                    break;
                case 2:
                    Profile.editProfile();
                    break;
                case 3:
                    break;
                default:
                    break;
            }//end switch
        }
    }

    public static void viewProfile()
    {
        //print query results here
        System.out.print("Query Here");
    }

    public static void editProfile()
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
        System.out.println("UID set to " + uidQuery);

        System.out.println("Enter DOB");
        dobQuery = s.nextLine();
        System.out.println("UID set to " + dobQuery);

        System.out.println("Enter Name");
        nameQuery = s.nextLine();
        System.out.println("UID set to " + nameQuery);

        System.out.println("Enter Address");
        addrQuery = s.nextLine();
        System.out.println("UID set to " + addrQuery);

        System.out.println("Enter Gender");
        genderQuery = s.nextLine();
        System.out.println("UID set to " + genderQuery);

        System.out.println("Enter Patient");
        patCatQuery = s.nextLine();
        System.out.println("UID set to " + patCatQuery);

    }
}
