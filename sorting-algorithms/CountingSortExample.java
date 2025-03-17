import java.util.Arrays;
import java.util.Scanner;

public class CountingSortExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of students
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();

        // Input the ages of students
        int[] ages = new int[n];
        System.out.println("Enter the ages of students (between 10 and 18):");
        for (int i = 0; i < n; i++) {
            ages[i] = scanner.nextInt();
            if (ages[i] < 10 || ages[i] > 18) {
                System.out.println("Invalid age! Please enter ages between 10 and 18.");
                i--; // Decrement to re-enter the value
            }
        }

        System.out.println("Original Ages: " + Arrays.toString(ages));

        // Sort the array using Counting Sort
        int[] sortedAges = countingSort(ages, 10, 18);

        System.out.println("Sorted Ages: " + Arrays.toString(sortedAges));

        scanner.close();
    }

    // Counting Sort function
    public static int[] countingSort(int[] array, int min, int max) {
        int range = max - min + 1; // Range of ages (10 to 18 inclusive)
        int[] count = new int[range];
        int[] output = new int[array.length];

        // Step 1: Count the occurrences of each age
        for (int age : array) {
            count[age - min]++;
        }

        // Step 2: Compute cumulative frequencies
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Place elements in their correct positions
        for (int i = array.length - 1; i >= 0; i--) {
            int age = array[i];
            output[count[age - min] - 1] = age;
            count[age - min]--;
        }

        return output;
    }
}
