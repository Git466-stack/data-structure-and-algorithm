import java.util.Scanner;

public class ConcatenateStrings {
    public static String concatenateStrings(String[] strings) {
        // Create a new StringBuffer object
        StringBuffer stringBuffer = new StringBuffer();

        // Iterate through each string in the array and append it to the StringBuffer
        for (String str : strings) {
            stringBuffer.append(str);
        }

        // Return the concatenated string
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the number of strings
        System.out.print("Enter the number of strings: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Create an array to store the strings
        String[] strings = new String[n];

        // Prompt the user to enter each string
        for (int i = 0; i < n; i++) {
            System.out.print("Enter string " + (i + 1) + ": ");
            strings[i] = scanner.nextLine();
        }

        // Concatenate strings using the method
        String result = concatenateStrings(strings);

        // Display the result
        System.out.println("Concatenated String: " + result);

        // Close the scanner
        scanner.close();
    }
}
