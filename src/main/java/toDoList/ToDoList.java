package toDoList;
import java.io.FileWriter;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File; //Import the file class
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ToDoList {

    private static void addTask(String taskSetName, String taskDescription, String taskDueDate, String taskCompletetionStatus){
        try{
            Path path = Paths.get(System.getProperty("user.dir") + "\\"+ "central_folder"+"\\" +"to_do_list_folder"+ "\\" +taskSetName);
            String task = "[ " + taskDescription + " ; " + taskDueDate + " ; " + taskCompletetionStatus + "] \n";
            System.out.println("Checking if this path exists: " + path);
            if (Files.exists(path)){
                System.out.println("Path exists.");
                Files.write(Paths.get(String.valueOf(path)), task.getBytes(), StandardOpenOption.APPEND);
            }
            if (Files.notExists(path)){ //This is to create the new set of task as text file at the specified directory found in path.
                FileWriter myWriter = new FileWriter(String.valueOf(path)); //Use the string value of path to specify location.
                myWriter.write(task); //Writes the string values of arguments into the specified file.
                myWriter.close();
                System.out.println("Added new task.");
            }

        }catch(IOException e) {
            System.out.println("Error Encountered");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);
        int mainOption = 0;
        boolean mainFlag = true;
        boolean flag = true;
        boolean isConfirmed = false;
        String taskSetName;
        String taskDescription;
        String taskDueDate;
        String taskCompletetionStatus;

        System.out.println("Do you want to [1]create a new set of tasks or [2]access an existing set of tasks? or [3]go back: ");

        while (mainFlag) { //Checks to see if user wants to create a new task set along with new tasks or add tasks to an exsiting task set or leave the ToDoList mode.
            try {
                String mainInputOption = myScanner.nextLine();
                mainOption = Integer.parseInt(mainInputOption);

                if (mainOption == 1 || mainOption == 2 || mainOption == 3) {
                    mainFlag = false;

                } else {
                    System.out.println("Please select option 1 or option 2 or option 3.");
                }
            } catch (Exception e) {

                System.out.println("Enter the number one for option 1 or number two for option 2 or number three for option 3.");
            } finally {
                //myScanner.close();
            }
        }

        try {
            if (mainOption == 1) {
                System.out.println("Enter the name for the set you would want to have your created tasks under: ");
                taskSetName = myScanner.nextLine() + ".txt";

                while (flag) {

                    System.out.println("Enter task description: ");
                    taskDescription = myScanner.nextLine();

                    System.out.println("Enter task due date: ");
                    taskDueDate = myScanner.nextLine();

                    taskCompletetionStatus = "not completed";

                    addTask(taskSetName,taskDescription,taskDueDate,taskCompletetionStatus);

                    while(isConfirmed == false) { //This while-loop is to keep the confirmation running.
                        System.out.println("Want to add another task? [Y]yes or [N]no: ");
                        String[] accept = {"y","yes"}; //Possible user confirmation options.
                        String[] deny = {"n","no"}; //Possible user denial options.
                        String confirmation = myScanner.nextLine();

                        confirmation = confirmation.toLowerCase();
                        System.out.println(confirmation);


                        if (Arrays.stream(accept).anyMatch(confirmation::equals) || Arrays.stream(accept).anyMatch(confirmation::equals)) {
                            isConfirmed = true;
                            System.out.println("You have confirmed.");
                        }

                        if (Arrays.stream(deny).anyMatch(confirmation::equals) || Arrays.stream(deny).anyMatch(confirmation::equals)) {
                            System.out.println("You have denied.");
                            flag = false;
                            isConfirmed = true;

                    }
                    }
                }
            }
        } catch (Exception e) {}
        finally {
            myScanner.close();
        }

    }
}
