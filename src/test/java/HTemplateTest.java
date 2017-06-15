import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by theasianpianist on 6/15/17.
 */
public class HTemplateTest {
    @Test
    public void testHTemplate(){
        String expectedHTML = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\t<head>\n" +
                "\t    <title>Test title</title>\n" +
                "  \t</head>\n" +
                "  \t<body>\n" +
                "\t\t<p style=\"color:red\">test red paragraph</p>\n" +
                "  \t</body>\n" +
                "</html>\n";
        String path = "/home/theasianpianist/Data/Documents/Code/htemplate/src/test/java/root/";
        HTemplate.processFiles(path + "template.html", path + "content");
        String outputHTML = getFileContents(path + "content/" + "index.html");
        File resultFile = new File(path + "content/" + "index.html");
        resultFile.delete();
        Assertions.assertEquals(expectedHTML, outputHTML);

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
}
