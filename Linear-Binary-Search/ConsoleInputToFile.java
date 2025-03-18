import java.io.*;

public class ConsoleInputToFile {
    public static void main(String[] args) {
        // Specify the file path to write the user input
        String filePath = "output.txt";

        // Try-with-resources block to handle user input and file writing
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader consoleReader = new BufferedReader(inputStreamReader);
             FileWriter fileWriter = new FileWriter(filePath)) {

            System.out.println("Enter text to write to the file. Type 'exit' to finish.");

            String userInput;
            while (true) {
                // Read user input from the console
                System.out.print("> ");
                userInput = consoleReader.readLine();

                // Exit the loop if the user types "exit"
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                // Write the user input to the file as a new line
                fileWriter.write(userInput + System.lineSeparator());
            }

            System.out.println("Input has been written to the file: " + filePath);

        } catch (IOException e) {
            // Handle exceptions during input or file writing
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
