package toDoList;
import java.io.*;
import java.nio.file.*;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

import static UserInteractionUtils.InputUtils.InputConfirmation;
import static fileSearchCreation.FileManager.loadLines;
import static fileSearchCreation.FileManager.showInFolder;

public class ToDoList {

    private static final String toDoListMainFolder = System.getProperty("user.dir") + "\\"+ "central_folder"+"\\" +"to_do_list_folder"+ "\\";

    private static void addTask(String taskSetName, String taskDescription, String taskDueDate, String taskCompletionStatus){
        try{
            Path path = Paths.get(toDoListMainFolder +taskSetName);
            String task = "[ " + taskDescription + " ; " + taskDueDate + " ; " + taskCompletionStatus + " ] \n";
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

    private static void readTask(String fullPath){
        Path path = Paths.get( fullPath);
        int counter = 0;
        ArrayList<String> tasks = loadLines(fullPath);

        for (int index = 0; index < tasks.size(); index ++){
            counter ++;
            System.out.println(counter + " " + tasks.get(index));
        }
    }

    private static void showTaskSets(){
        showInFolder(toDoListMainFolder);
    }


    public static void main(String ...args) {
        Scanner myScanner = new Scanner(System.in);
        int mainOption = 0;
        boolean mainFlag = true;
        boolean flag = true;
        String taskSetName;
        String taskDescription;
        String taskDueDate;
        String taskCompletionStatus;

        while (mainFlag) { //Checks to see if user wants to create a new task set along with new tasks or add tasks to an already existing task set or leave the ToDoList mode.

            System.out.println("The Following are existing task sets:");
            showTaskSets();
            System.out.println("Do you want to [1]create a new set of tasks or [2]access an existing set of tasks? or [3]delete an existing set if tasks? [4]go back: ");
            try {
                String mainInputOption = myScanner.nextLine();
                mainOption = Integer.parseInt(mainInputOption);

                if (mainOption == 1 || mainOption == 2 || mainOption == 3 || mainOption == 4) {
                    //mainFlag = false;

                } else {
                    System.out.println("Please select an available option.");
                }
            } catch (Exception e) {

                System.out.println("Enter the number associated with the desired option.");
            }

            //try {
                if (mainOption == 1) {
                    System.out.println("Enter the name for the set you would want to have your created tasks under: ");
                    taskSetName = myScanner.nextLine() + ".txt";

                    while (flag) {

                        System.out.println("Enter task description: ");
                        taskDescription = myScanner.nextLine();

                        System.out.println("Enter task due date: ");
                        taskDueDate = myScanner.nextLine();

                        taskCompletionStatus = "not completed";

                        addTask(taskSetName, taskDescription, taskDueDate, taskCompletionStatus);

                        if (!InputConfirmation("Want to add another task?")) {
                            flag = false;
                        }
                    }
                }
                if (mainOption == 2) {
                    String taskSetPath;
                    int taskSelector = 0;
                    ArrayList<String> allTask;
                    while (flag) {
                        System.out.println("What set you want to access? :");
                        taskSetName = myScanner.nextLine() + ".txt";
                        Path path = Paths.get(toDoListMainFolder + taskSetName);
                        System.out.println(toDoListMainFolder + taskSetName);
                        if (Files.exists(path)) {
                            taskSetPath = path.toString();
                            readTask(taskSetPath);

                            if (!InputConfirmation("Want to access another set?")) {
                                flag = false;
                            }
                            if (!InputConfirmation("Want to modify this set?")) {
                                flag = false;
                            }else {
                                readTask(taskSetPath);

                                System.out.print("Which task you want to modify");

                                switch(taskSelector){
                                    case 1 :
                                }
                            }
                        }
                    }
                }
                if (mainOption == 3){
                    String taskSetPath;
                    int taskSelector = 0;
                    while (flag){
                        System.out.println("What set you want to delete? :");
                        taskSetName = myScanner.nextLine() + ".txt";
                        Path path = Paths.get(toDoListMainFolder + taskSetName);
                        try {

                            if (Files.exists(path)) {
                                Files.delete(path);
                                showTaskSets();
                                System.out.println(taskSetName + " has been deleted.");
                            } else {
                                System.out.println(taskSetName + " doesn't exist.");
                            }
                        } catch (Exception e){e.printStackTrace();}

                        if (!InputConfirmation("Want to delete another set?")) {
                            flag = false;
                        }
                    }





                }
            //}
            //catch (Exception e) {e.printStackTrace(); }
            if(InputConfirmation("Do you want to exit back to Main Mode Menu?")){
                mainFlag = false;
                //flag = true;
                //myScanner.close();
            }
            flag = true;
        } //catch (IOException e) {
               // e.printStackTrace();
            //}
        }
}
