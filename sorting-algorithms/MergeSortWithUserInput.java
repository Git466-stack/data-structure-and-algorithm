import java.util.Arrays;
import java.util.Scanner;

public class MergeSortWithUserInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of book prices
        System.out.print("Enter the number of book prices: ");
        int n = scanner.nextInt();

        // Input the book prices
        double[] bookPrices = new double[n];
        System.out.println("Enter the book prices:");
        for (int i = 0; i < n; i++) {
            bookPrices[i] = scanner.nextDouble();
        }

        System.out.println("Original Book Prices: " + Arrays.toString(bookPrices));

        // Sort the array using Merge Sort
        mergeSort(bookPrices, 0, bookPrices.length - 1);

        System.out.println("Sorted Book Prices: " + Arrays.toString(bookPrices));

        scanner.close();
    }

    // Function to divide the array into halves and sort them
    public static void mergeSort(double[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Recursively sort the left half
            mergeSort(array, left, mid);

            // Recursively sort the right half
            mergeSort(array, mid + 1, right);

            // Merge the sorted halves
            merge(array, left, mid, right);
        }
    }

    // Function to merge two sorted halves
    public static void merge(double[] array, int left, int mid, int right) {
        // Sizes of two temporary arrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        double[] leftArray = new double[n1];
        double[] rightArray = new double[n2];

        // Copy data to temporary arrays
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        // Initial indexes of two subarrays and the merged array
        int i = 0, j = 0, k = left;

        // Merge the temp arrays back into the main array
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        // Copy remaining elements of leftArray (if any)
        while (i < n1) {
            array[k++] = leftArray[i++];
        }

        // Copy remaining elements of rightArray (if any)
        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }
}
