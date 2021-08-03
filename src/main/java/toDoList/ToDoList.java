package toDoList;
import java.io.*;
import java.nio.file.*;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

import static UserInteractionUtils.InputUtils.*;
import static fileSearchCreation.FileManager.*;

public class ToDoList {

    private static final String toDoListMainFolder = System.getProperty("user.dir") + "\\"+ "central_folder"+"\\" +"to_do_list_folder"+ "\\";

    private static void addTask(String taskSetName, String taskDescription, String taskDueDate, String taskCompletionStatus){
        try{
            Path path = Paths.get(toDoListMainFolder +taskSetName);
            String task = taskDescription + " ; " + taskDueDate + " ; " + taskCompletionStatus + "\n";
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



    private static ArrayList<String> updateTask(ArrayList<String> tasks, int option, String path){
        readTask(path); //To print out options.
        ArrayList<String> taskList = tasks; //Tasks of a task set, each as an element of the array.
        Scanner myScanner = new Scanner(System.in);
        int userOption = 0; //Variable to hold on to the index of the task the user wants to modify.
        String userInput;
        String taskToBeParsed; //Variable that holds on to the task from the array the user wants to modify.
        String[] taskParsed; //Array that will have all fields a task with each field having a respective index.
        String modifiedTask = "";

        switch (option){
            case 1:

                while(true) {

                    userOption = confirmOption(taskList.size()); //Get's the size of the array and use it for user to decide which task to modify.
                    taskToBeParsed = taskList.get(userOption - 1); //retrieves the task that is to be modified.
                    taskParsed = taskToBeParsed.split(" ; "); //parses the task String.
                    System.out.println(taskParsed[0]);
                    System.out.println(taskParsed[1]);
                    System.out.println(taskParsed[2]);

                    System.out.println("Enter new description or leave blank to remain the same: ");
                    userInput = myScanner.nextLine();

                    if (InputConfirmation("Are you sure you want to apply changes? ") ) {
                        //System.out.println("This is UpdateTask userInput: " + userInput);
                        if (!userInput.isEmpty()) {
                            taskParsed[0] = userInput;
                        }
                    }

                    System.out.println("Enter new deadline or leave blank to remain the same: ");
                    userInput = myScanner.nextLine();
                    if (userInput != "") {
                        if (InputConfirmation("Are you sure you want to apply changes? ")) {
                            taskParsed[1] = userInput;
                        }
                    }

                    System.out.println("You have entered : " + taskParsed[0] + " and " + taskParsed[1]);
                    //modifiedTask = arrayToString(taskParsed);
                    modifiedTask = String.join(" ; ",  taskParsed);
                    //System.out.println("We are checking it out!!!");
                    System.out.print(modifiedTask);
                    taskList.set(userOption - 1, modifiedTask); //The new task is added back into the ArrayList.


                    if(!InputConfirmation("Do you want to update another task? ")) {
                        for (String task :taskList){
                            System.out.print(task);
                        }
                        return taskList;
                    }
                }
            case 2:
                while (true){
                    userOption = confirmOption(taskList.size()); //Get's the size of the array and use it for user to decide which task to modify.
                    taskList.remove(userOption - 1); //removes the task at the chosen index.
                    return taskList;
                }
            case 3:
                while(true){
                    System.out.println("Add a description: ");
                    userInput = myScanner.nextLine();
                    modifiedTask += userInput + " ; ";
                    System.out.println("Add a deadline: ");
                    userInput = myScanner.nextLine();
                    modifiedTask += userInput + " ; " + "not completed";

                    taskList.add(modifiedTask);

                    return taskList;
            }
            case 4:
                while (true){
                    userOption = confirmOption(taskList.size()); //Get's the size of the array and use it for user to decide which task to modify.
                    taskToBeParsed = taskList.get(userOption -1);
                    taskParsed = taskToBeParsed.split(" ; "); //parses the task String.
                    taskParsed[2] = "COMPLETED!!!!!";
                    //modifiedTask = arrayToString(taskParsed);
                    modifiedTask = String.join(" ; ",  taskParsed);
                    taskList.set(userOption-1, modifiedTask);

                    return taskList;


                }

        }
        return taskList;
    }

    private static void readTask(String fullPath){
        Path path = Paths.get( fullPath);
        int counter = 0;
        ArrayList<String> tasks = loadLines(fullPath);

        for (int index = 0; index < tasks.size(); index ++){
            counter ++;
            System.out.println(counter + ") " + tasks.get(index));
        }
    }

    private static void showTaskSets(){
        showInFolder(toDoListMainFolder);
    }

    private  static void deleteTaskSets(){
        boolean flag = true;
        String taskSetName;
        Scanner myScanner = new Scanner(System.in);
        Path path;

        int taskSelector = 0;
        while (flag){ //Will keep deletion loop going.
            System.out.println("What set you want to delete? :");
            taskSetName = myScanner.nextLine() + ".txt"; //get name of the task set the user wants to access.
            path = Paths.get(toDoListMainFolder + taskSetName); //get actual path of selected task set.
            try {
                if (Files.exists(path)) { //Checks if task set's path exists.
                    Files.delete(path); //Deletes the file.
                    showTaskSets(); //Shows all tasks once more.
                    System.out.println(taskSetName + " has been deleted.");
                } else {
                    showTaskSets();
                    System.out.println(taskSetName + " doesn't exist.");
                }
            } catch (Exception e){e.printStackTrace();}

            if (!InputConfirmation("Want to delete another set?")) {
                flag = false;
            }
        }

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

                if (mainOption == 1) {
                    System.out.println("Enter the name for the set you would want to have your created tasks under: ");
                    taskSetName = myScanner.nextLine() + ".txt";
                    // Handle case of an existing file *******************************************************
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
                    ArrayList<String> allTask = null;
                    while (flag) {
                        System.out.println("What set you want to access? :");
                        taskSetName = myScanner.nextLine() + ".txt";
                        Path path = Paths.get(toDoListMainFolder + taskSetName);
                        if (Files.exists(path)) {
                            taskSetPath = path.toString();
                            readTask(taskSetPath);

                            if (!InputConfirmation("Want to access another set?")) {
                                flag = false;
                                if (!InputConfirmation("Want to modify this set?")) {
                                    flag = false;
                                }else {
                                    ArrayList<Integer> modificationOptions = new ArrayList<>();
                                    modificationOptions.add(1);
                                    modificationOptions.add(2);
                                    modificationOptions.add(3);
                                    modificationOptions.add(4);
                                    modificationOptions.add(5);

                                    allTask = loadLines(taskSetPath);
                                    String localMessage ="Want to [1]update a task or [2]delete a task? or [3]add a task or [4] Complete a task ";
                                    System.out.println("WE ARE HERE !!!!!!!");
                                    int userModificationOption = confirmOption(modificationOptions,localMessage,"int");

                                    allTask = updateTask(allTask,userModificationOption, taskSetPath);//Modification of a task occurs here.

                                    try {
                                        if (Files.exists(path) & allTask != null) ; //Checks if task set's path exists.
                                        Files.delete(path); //Deletes the file.  //Step 1: Delete outdated file.
                                        System.out.println("File deleted");
                                        createFile(toDoListMainFolder + taskSetName); //Step 2: Create new empty file.
                                        System.out.println("File has been created");
                                        writeArrayListToFile(allTask, taskSetPath); // Step 3: Write into the new empty file.
                                        System.out.println("Now file has been edited");
                                        allTask = null;
                                    } catch (Exception e) {e.printStackTrace();}
                                }
                            }
                        }
                    }
                }
                if (mainOption == 3){
                    deleteTaskSets();
                }

            if(InputConfirmation("Do you want to exit back to Main Mode Menu?")){
                mainFlag = false;
            }
            flag = true;
            mainOption = 0;
        }
    }
}
