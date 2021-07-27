import java.util.Arrays;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        boolean isQuit = false; //To keep program running.

        boolean isSelecting = true; //To keep selection mode running.

        boolean useToDoList = false; //To start a mode.
        boolean useNotes = false; //To start a mode.
        boolean useJournal = false; //To start a mode.

        Scanner myScanner = new Scanner(System.in);
        while (isQuit == false) { //This while-loop will keep the whole program running.

            while (isSelecting) { //This while-loop will keep the selection mode running.
                try {
                    System.out.println("Choose Mode [1]To-Do-List , [2]Notes, [3]Journal :");
                    String modeNum = myScanner.nextLine();

                    int option = Integer.parseInt(modeNum);

                    if (option >=1 && option <= 3) {
                        switch(option) {
                            case 1:
                                System.out.println("You have choosen option " + option + ", To-Do-List");
                                isSelecting = false;
                                useToDoList = true;
                                break;
                            case 2:
                                System.out.println("You have choosen option " + option + ", Notes");
                                isSelecting = false;
                                useNotes = true;
                                break;
                            case 3:
                                System.out.println("You have choosen option " + option + ", Journal");
                                isSelecting = false;
                                useJournal = true;
                                break;
                        }
                    } else {
                        System.out.println("Please enter 1 for To-Do-List, 2 for Notes, 3 for Journal");
                    }

                } catch(Exception e) {
                    System.out.println("Please enter 1 for To-Do-List, 2 for Notes, 3 for Journal");

                } finally {
                    //myScanner.close();
                }
            }

            boolean isConfirmed = false;
            boolean contains;

            while(isConfirmed != true) { //This while-loop is to keep the confirmation running.
                String[] accept = {"y","yes"}; //Possible user confirmation options.
                String[] deny = {"n","no"}; //Possible user denial options.
                System.out.println("Do you want to coninue [Y]yes [N]no ");
                String confirmation = myScanner.nextLine();

                confirmation = confirmation.toLowerCase();
                System.out.println(confirmation);


                if (Arrays.stream(accept).anyMatch(confirmation::equals) || Arrays.stream(accept).anyMatch(confirmation::equals)) {
                    isConfirmed = true;
                    System.out.println("You have confirmed.");
                }

                if (Arrays.stream(deny).anyMatch(confirmation::equals) || Arrays.stream(deny).anyMatch(confirmation::equals)) {
                    System.out.println("You have denied.");
                    isConfirmed = true;
                    useToDoList = false;
                    useNotes = false;
                    useJournal = false;
                    isSelecting = true;
                }
            }


            while(useToDoList) { //This while-loop will run the To Do List program.
                System.out.println("In to do list");
            }


            while(useNotes) { //This while-loop will run the notes.
                System.out.println("In Notes");
            }

            while(useJournal) { //This while-loop will run the journal.
                System.out.println("In journal");
            }

//				System.out.println("stall line");
//				String stall = myScanner.nextLine();

        }

    }

}
