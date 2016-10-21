package utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


/**
 * Created by Tyler on 10/19/2016.
 *
 * Needs UID from main menu login.
 */

public class Alerts {

    public static void view(int userID) {
        String response = null;
        Scanner s = new Scanner(System.in);
        ArrayList<String> alerts = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();

        //TODO needs UID from main menu login

        //TODO execute sql query to receive alerts for uid into array - alerts[]
        //TODO extract key field for each alert to keys arraylist

        for (int i = 0; i < alerts.size(); i++) {
            System.out.println(alerts.get(i));
            System.out.println("Would you like to clear this alert? (Y/N)");
            response = s.nextLine().toUpperCase();

            if (response.equals("Y"))
            {
                //TODO insert sql to update alert cleared field
                System.out.println("Update cleared.");
            }
            System.out.print("\n\n\n");
        }
    }

}
