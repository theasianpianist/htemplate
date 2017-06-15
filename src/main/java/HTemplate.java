import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by theasianpianist on 6/10/17.
 */
public class HTemplate {

    private static final String marker = "***HTEMPLATE***"; //Start of the content markers that should be contained in template and content files
                                                            //e.g. Template file: <html><head></head><body>***HTEMPLATE***</body></html>
                                                            //e.g. Content file: ***HTEMPLATE***<h1>TEXT</h1><p>text</p>

    public static void main(String[] args){
        System.out.print("Please enter the template file path: ");
        Scanner input = new Scanner(System.in);
        String templatePath = input.nextLine(); //Location of the template file
        System.out.print("Please enter the directory for your content files: ");
        input = new Scanner(System.in);
        String contentPath = input.nextLine();
        processFiles(templatePath, contentPath);

    }

    public static void processFiles(String templatePath, String contentPath) {
        String template = getFileContents(templatePath); //String containing text of the template file
        if (template.equals("")) {
            System.out.println("Empty template file");
            System.exit(0);
        }
        ArrayList<File> contentFiles = getDirectoryFiles(contentPath, templatePath);
        ArrayList<Integer> templateMarkerLocations = getMarkerLocations(template);
        for (File file : contentFiles) {
            ArrayList<Integer> contentMarkerLocations = getMarkerLocations(getFileContents(file.getPath()));
            if (contentMarkerLocations.size() == templateMarkerLocations.size()){
                String newFile = template;
                for (int i = contentMarkerLocations.size() - 1; i >= 0; i--){ //Loop backwards because inserting content messes up later marker indices
                    String toBeInserted;
                    if (i == contentMarkerLocations.size() - 1){ //if processing last marker location, then substring without an end index
                        toBeInserted = getFileContents(file.getPath()).substring(contentMarkerLocations.get(i) + marker.length() + 1);
                        toBeInserted = toBeInserted.substring(0, toBeInserted.length() - 1);
                    }
                    else{
                        toBeInserted = getFileContents(file.getPath()).substring(contentMarkerLocations.get(i) + marker.length() + 1, contentMarkerLocations.get(i + 1) - 1); //offset start and end indices by 1 to account for newline characters
                    }
                    newFile = newFile.substring(0, templateMarkerLocations.get(i)) + toBeInserted + newFile.substring(templateMarkerLocations.get(i) + marker.length(), newFile.length());
                }
                String oldFileName = file.getName().substring(0, file.getName().length() - 4); //grabs original filename minus .htp extension"
                String newFileName = oldFileName + ".html";
                try{
                    PrintWriter writer = new PrintWriter(contentPath + "/" + newFileName, "UTF-8");
                    writer.print(newFile);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Your file could not be written to!");
                }
            }
            else{
                System.out.println("Template markers and content markers do not match up, skipping file " + file.getPath());
            }
        }

    }

    private static String getFileContents(String filePath){
        String str = "";
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            str = new String(data, "UTF-8");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static ArrayList<File> getDirectoryFiles(String path, String templatePath){
        File folder = new File(path);
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(folder.listFiles()));
        HashSet nonHtpFiles = new HashSet();
        for (File file : files){
            String filePath = file.getPath();
            String extension = filePath.substring(filePath.length() - 4, filePath.length());
            if (file.isDirectory()){
                processFiles(templatePath, file.getPath());
            }
            if (!extension.equals(".htp")){ //If the file doesn't end in .htp add it to a hashset to be removed
                nonHtpFiles.add(file);
            }
        }
        files.removeAll(nonHtpFiles);
        return files;
    }

    private static ArrayList<Integer> getMarkerLocations(String fileText) {
        ArrayList<Integer> markerLocations = new ArrayList<Integer>();
        int location = fileText.indexOf(marker);
        if (location == -1){
            System.out.println("Your template file has no content markers");
            System.exit(1);
        }
        while (location != -1){
            markerLocations.add(location);
            location = fileText.indexOf(marker, location + 1);
        }
        return markerLocations;
    }
}
