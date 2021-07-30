import toDoList.ToDoList;
import java.util.Scanner;
import static UserInteractionUtils.InputUtils.InputConfirmation;

public class MainClass {

    public static void main(String[] args) {

        boolean isQuit = false; //To keep program running.

        boolean isSelecting = true; //To keep selection mode running.

        boolean useToDoList = false; //To start a mode.
        boolean useNotes = false; //To start a mode.
        boolean useJournal = false; //To start a mode.

        int option = -1232432;

        String optionArray[] = {"To Do List", "Notes", "Journal", "Exit Program"};

        Scanner myScanner = new Scanner(System.in);
        while (isQuit == false) { //This while-loop will keep the whole program running.

            while (isSelecting) { //This while-loop will keep the selection mode running.
                try {
                    System.out.println("Choose Mode [1]To-Do-List , [2]Notes, [3]Journal [4]Quit:");
                    String modeNum = myScanner.nextLine();

                    option = Integer.parseInt(modeNum);

                    if (option >=1 && option <= 4) {
                        switch(option) {
                            case 1:
                                //System.out.println("You have chosen option " + option + ", To-Do-List");
                                isSelecting = false;
                                useToDoList = true;
                                break;
                            case 2:
                                //System.out.println("You have chosen option " + option + ", Notes");
                                isSelecting = false;
                                useNotes = true;
                                break;
                            case 3:
                                //System.out.println("You have chosen option " + option + ", Journal");
                                isSelecting = false;
                                useJournal = true;
                                break;
                            case 4:
                                isSelecting = false;
                                isQuit = true;


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
            if(!InputConfirmation("Want to continue with the following mode: "+ optionArray[option - 1])){
                useToDoList = false;
                useNotes = false;
                useJournal = false;
                isSelecting = true;
            }
            while(useToDoList) { //This while-loop will run the To Do List program.
                System.out.println("In to do list");
                ToDoList.main();
                useToDoList = false; //Will flag to exit loop.
                isSelecting = true; //Will flag to enter selection mode loop.
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
