package UserInteractionUtils;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputUtils {

    static public boolean InputConfirmation(String customMessage){
        Scanner myScanner = new Scanner(System.in);
        //boolean isConfirmed = false;
        while(true) { //This while-loop is to keep the confirmation running.
            System.out.println( customMessage + " [Y]yes or [N]no: ");
            List<String> deny = new ArrayList<>(2);
            List<String> accept = new ArrayList<>(2);
            accept.add("y");
            accept.add("yes"); //Possible user confirmation options.
            deny.add("n");
            deny.add("no"); //Possible user denial options.
            String confirmation = myScanner.nextLine();

            confirmation = confirmation.toLowerCase();
            //System.out.println(confirmation);

            if (accept.contains(confirmation)) {
                //isConfirmed = true;
                System.out.println("You have confirmed.");
                return true;
            }

            if (deny.contains(confirmation)){
                System.out.println("You have denied.");
                //isConfirmed = true;
                return false;

            }
        }
        //return false;

    }
}
