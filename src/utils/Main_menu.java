package utils;
import java.util.Scanner;

/**package utils;
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

    public static void displayMenu() {
        int selection = 1;

        Scanner s = new Scanner(System.in);

        while (selection != 6)
        {
            System.out.print("Main Menu\n\n"+
                    "Please make a selection (1-6):\n"+
                    "1. Profile\n"+
                    "2. Diagnoses\n"+
                    "3. Health Indicator\n"+
                    "4. Alerts\n"+
                    "5. Health Supporters\n"+
                    "6. Logout\n");


            selection = s.nextInt();

            switch(selection) {
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
                    break;
                default:
                    break;
            }//end switch
        }//end while
    }//end main
}//end class
