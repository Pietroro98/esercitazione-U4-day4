package NewMain;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main2 {
    public static void main(String[] args) {

        File file = new File("src/info.txt");
        try {
            FileUtils.writeStringToFile(file, "CIAO" + System.lineSeparator(), StandardCharsets.UTF_8, true);
            String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            System.out.println(fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            //        FileUtils.readFileToString(e);
    }
}
