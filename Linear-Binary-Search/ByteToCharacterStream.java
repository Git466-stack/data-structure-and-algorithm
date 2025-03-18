import java.io.*;

public class ByteToCharacterStream {
    public static void main(String[] args) {
        // Specify the file path and charset
        String filePath = "example.txt";
        String charset = "UTF-8";

        // Try-with-resources block to handle file reading
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            // Read and print each line from the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            // Handle exceptions such as file not found or read errors
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
