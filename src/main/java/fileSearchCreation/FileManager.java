package fileSearchCreation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    private static String parent_data_dir = "/main_data_folder";

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
