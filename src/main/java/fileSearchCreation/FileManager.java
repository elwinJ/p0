package fileSearchCreation;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    private static String parent_data_dir = "/main_data_folder";
    private static BufferedReader reader;

//    public static boolean checkFilePath(String taskSetPath){
//
//        Path path = Paths.get(taskSetPath);
//        if (Files.exists(path)){
//            return(true);
//        } else {
//            return(false);
//        }
//    }

    public static void initDir() {

        try { //attempt to get the parent_data_dir and creates if not exist.

            String pwd = getCurrentDirectory(); // gets this project's directory.
            Path path = Paths.get( pwd + parent_data_dir);
            Files.createDirectories(path);
            System.out.println(parent_data_dir + " was created");

        } catch (IOException e) {
            System.err.println("Failed to create direcotry!"+ e.getMessage());
        }
    }

    public static String getCurrentDirectory() {
        String dir = System.getProperty("user.dir");

        return(dir);
    }

    public static ArrayList<String> getFileNames(String targetFolderPath){
        ArrayList<String> fileNames = new ArrayList<>();

        File[] files = new File(targetFolderPath).listFiles();

        for (int index = 0; index < files.length;index++){
            fileNames.add(files[index].getName());
        }

        return fileNames;
    }

    public static void showInFolder(String targetFolderPath) {
        ArrayList<String> fileNames = getFileNames(targetFolderPath);

        System.out.println("__________________________");
        for (int index = 0; index < fileNames.size(); index ++){
            System.out.println(fileNames.get(index));
        }
        System.out.println("__________________________");
    }

    public static ArrayList<String> loadLines(String targetFilePath){

        ArrayList<String> loadedlines = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(targetFilePath));
            String line = reader.readLine(); //will be the variable to hold onto lines read.
            while (line != null){ //Wil continue reading lines until it can't find anymore lines to read.
                loadedlines.add(line); //will add a whole line into the ListArray.
                line = reader.readLine();//reads next line,
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedlines;

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
