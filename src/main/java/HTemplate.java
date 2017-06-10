import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by theasianpianist on 6/10/17.
 */
public class HTemplate {

    public static void main(String[] args){
        System.out.print("Please enter the template file path: ");
        Scanner input = new Scanner(System.in);
        String templatePath = input.nextLine(); //Location of the template file
        String template = getTemplate(templatePath); //String containing text of the template file
        if (template.equals("")) {
            System.out.println("Empty template file");
        }
        System.out.println(template);
    }

    private static String getTemplate(String filePath){
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
}
