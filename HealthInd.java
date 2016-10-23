package utils;

import java.util.Scanner;

/**
 * Created by Tyler on 10/19/2016.
 */
public class HealthInd {
    // the indicator name, low, high values, frequency, option to enter the observation
    // PID


    public static void healthIndMenu()
    {
        int selection = 1;
        Scanner s = new Scanner(System.in);

        while (selection != 4) {
            System.out.print("Health Indicator Menu\n\n";

            selection = s.nextInt();

            switch (selection) {
                case 1:
                    enterObs(Userid.USER_ID_STATIC);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }//end switch
        }
    }


    private static void enterObs(int userID) {
        int selection = 1;
        int uid = 0;
        boolean isSupporter = false;

        Scanner s = new Scanner(System.in);

        //TODO insert sql statement to return boolean value of whether uid belongs to a health supporter or not.
        //use Userid.USER_ID_STATIC

        if (isSupporter = false) {
            System.out.println("Please enter the Health Indicator name.");
            String hIndName = s.nextLine();
        } else {

        }
    }

    private static void
}