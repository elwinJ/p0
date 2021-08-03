package fileSearchCreation;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    private static final String parent_data_dir = "/main_data_folder";
    public static BufferedReader reader;

    public static void initDir() {
        /*
          Will create directory "main_data_folder" within the present working directory.

          <p>
          This method will create the directory "main_data_folder" to archive the directories needed
          for each mode.
          </>
          {@code null} Won't return anything.
         */
        try { //attempt to get the parent_data_dir and creates if not exist.

            String pwd = getCurrentDirectory(); // gets this project's directory.
            Path path = Paths.get( pwd + parent_data_dir);
            Files.createDirectories(path);
            System.out.println(parent_data_dir + " was created");

        } catch (IOException e) {
            System.err.println("Failed to create directory!"+ e.getMessage());
        }
    }

    public static String getCurrentDirectory() {

        return(System.getProperty("user.dir"));
    }

    public static ArrayList<String> getFileNames(String targetFolderPath){
        ArrayList<String> fileNames = new ArrayList<>();

        File[] files = new File(targetFolderPath).listFiles();

        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }

        return fileNames;
    }

    public static void showInFolder(String targetFolderPath) {
        ArrayList<String> fileNames = getFileNames(targetFolderPath);

        System.out.println("__________________________");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
        System.out.println("__________________________");
    }

    public static ArrayList<String> loadLines(String targetFilePath){

        ArrayList<String> loadedlines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(targetFilePath))){
            String line = reader.readLine(); //will be the variable to hold onto lines read.
            while (line != null){ //Wil continue reading lines until it can't find anymore lines to read.
                loadedlines.add(line); //will add a whole line into the ListArray.
                line = reader.readLine();//reads next line,
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedlines;

    }

    public static void createFile(String filePath){
        Path path = Paths.get(filePath);

        try{
            FileWriter myWriter = new FileWriter((String.valueOf(path))); //Creates file.
            myWriter.close();
        }catch(IOException e){}
    }

    public static void deleteFile(String filePath){
        Path path = Paths.get(filePath);
        try{
            Files.delete(path); //Deletes the file.
        }catch(IOException e){}
    }

    public static void writeArrayListToFile(ArrayList<String> allTask, String filePath){
        try {
            //Path path = Paths.get(filePath);
            FileWriter myWriter = new FileWriter(filePath);
            for (String task : allTask) {
                myWriter.write(task + "\n");
            }
            myWriter.close();
        } catch (Exception e){}
    }

    public static void main(String[] args) {

        System.out.println(getCurrentDirectory());

        try {

            Path path = Paths.get(getCurrentDirectory() + "/central_folder");

            //java.nio.file.Files;
            Files.createDirectories(path);

            System.out.println("central_folder Directory is created at "+ getCurrentDirectory());

            path = Paths.get(getCurrentDirectory() + "/central_folder" +"/to_do_list_folder");

            //java.nio.file.Files;
            Files.createDirectories(path);

            System.out.println("to_do_list_folder Directory is created at "+ getCurrentDirectory() + "/central_folder" + "/to_do_list_folder");

        } catch (IOException e) {

            System.err.println("Failed to create directory!" + e.getMessage());

        }
    }
}
