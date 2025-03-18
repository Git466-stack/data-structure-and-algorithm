import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCountInFile {
    public static void main(String[] args) {
        // Specify the file path and the target word
        String filePath = "example.txt";
        String targetWord = "Java";

        // Initialize the counter
        int wordCount = 0;

        // Try-with-resources block to handle file reading
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            // Read the file line by line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into words
                String[] words = line.split("\\s+"); // Split by whitespace
                for (String word : words) {
                    // Compare each word with the target word (case-sensitive)
                    if (word.equals(targetWord)) {
                        wordCount++;
                    }
                }
            }

        } catch (IOException e) {
            // Handle exceptions such as file not found or read errors
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Print the result
        System.out.println("The word \"" + targetWord + "\" appears " + wordCount + " times in the file.");
    }
}
