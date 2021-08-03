package UserInteractionUtils;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputUtils {
    static Scanner myScanner = new Scanner(System.in);

//    static public String arrayToString(String array[]){
//        StringBuilder sb = new StringBuilder(); //Create StringBuilder object.
//        for (int i = 0; i < array.length; i++){ //Appends array elements into sb.
//            if (i == array.length-1){ //Checks if element is last element to not add the separator.
//                sb.append(array[i]);
//                System.out.println("StringBuilder column: ");
//            }
//            else{ //If not last element then appends with separator.
//                sb.append(array[i] + " ; ");
//            }
//        }
//        return(sb.toString()); //returns a String.

//    }
    static public boolean InputConfirmation(String customMessage){
        List<String> deny = new ArrayList<>(2);
        List<String> accept = new ArrayList<>(2);
        accept.add("y");
        accept.add("yes"); //Possible user confirmation options.
        deny.add("n");
        deny.add("no"); //Possible user denial options.

        //boolean isConfirmed = false;
//        myScanner.nextLine(); //This is used to handle wrong input types.
        while(true) { //This while-loop is to keep the confirmation running.
            System.out.println("\n" + customMessage + " [Y]yes or [N]no: ");

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


    public static int confirmOption(int maxIntRange){
    //This is for the user to input their selections from ints one up to the maxInRange, and it will be confirmed.
        int userOptionInt;

        while(true){
            System.out.println();
            try{
                System.out.println("Select an option from 1 to " + maxIntRange + ": ");
                userOptionInt = myScanner.nextInt();/////////////////////
                myScanner.nextLine();
                if(userOptionInt >= 1 & userOptionInt <= maxIntRange+1){
                    return userOptionInt;
                }
            }
            catch (Exception e){
                //System.out.println("");
                myScanner.next(); //This is used to handle wrong input types.
            }
        }
    }

    //This method's return type is generic in order to handle int and String.
    //static <T> T confirmOption(ArrayList<T> rangeOfOptions, String customMessage, String inputType){
    public static <T> T confirmOption(ArrayList<T> rangeOfOptions, String customMessage, String inputType){
        //This for the user to confirm options by typing in the desire selection that exist as an element in a list.
        //So far methods handles String and int types of ArrayLists.
        ArrayList<T> myList = rangeOfOptions;
        //int maxSize = myList.size();
        int userOptionInt;
        String userOptionString;
        while(true){
            try {
                System.out.println(customMessage);
                if (inputType == "int") { //this will check for the appropriate input type needed to scan in.

                    userOptionInt = myScanner.nextInt();
                    myScanner.nextLine();

                    if (rangeOfOptions.contains(userOptionInt)) {
                        return rangeOfOptions.get(rangeOfOptions.indexOf(userOptionInt));
                    }
                }
                if (inputType == "String") {

                    userOptionString = myScanner.nextLine();
                    if (rangeOfOptions.contains(userOptionString)) {
                        return rangeOfOptions.get(rangeOfOptions.indexOf(userOptionString));
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
                //System.out.println(customMessage);
                myScanner.next(); //This is used to handle wrong input types.
                //return null;
            }
        }
    }
    public static void main(String[] args){

        //Test case with string options:
//        ArrayList<String> myListString = new ArrayList<>();
//        myListString.add("Dog");
//        myListString.add("Cat");
//        myListString.add("Fish");
//        myListString.add("Bird");
//
//        for(String option : myListString){System.out.println(option);}
//        System.out.println(confirmOption(myListString, "Please choose an animal from the list :", "String"));

        //Test case with string options:
        ArrayList<Integer> myListInt = new ArrayList<>();
        myListInt.add(78);
        myListInt.add(765);
        myListInt.add(500);
        myListInt.add(24);

        for(Integer option : myListInt){System.out.println(option);}
        System.out.println(confirmOption(myListInt, "Please choose an number from the list :", "int"));
    }

}
