import java.util.Arrays;
import java.util.Scanner;

public class SelectionSortExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of exam scores
        System.out.print("Enter the number of exam scores: ");
        int n = scanner.nextInt();

        // Input the exam scores
        int[] examScores = new int[n];
        System.out.println("Enter the exam scores:");
        for (int i = 0; i < n; i++) {
            examScores[i] = scanner.nextInt();
        }

        System.out.println("Original Exam Scores: " + Arrays.toString(examScores));

        // Sort the array using Selection Sort
        selectionSort(examScores);

        System.out.println("Sorted Exam Scores: " + Arrays.toString(examScores));

        scanner.close();
    }

    // Selection Sort function
    public static void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            // Assume the minimum is the first element of the unsorted part
            int minIndex = i;

            // Find the index of the minimum element in the unsorted part
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first unsorted element
            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }
}
