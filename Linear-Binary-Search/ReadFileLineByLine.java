import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileLineByLine {
    public static void main(String[] args) {
        // Specify the file path
        String filePath = "example.txt";

        // Initialize resources in a try-with-resources block
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            // Read and print each line from the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            // Handle exceptions such as file not found or read errors
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
