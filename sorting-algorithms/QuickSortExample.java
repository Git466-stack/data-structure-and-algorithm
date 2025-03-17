import java.util.Arrays;
import java.util.Scanner;

public class QuickSortExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of product prices
        System.out.print("Enter the number of product prices: ");
        int n = scanner.nextInt();

        // Input the product prices
        double[] productPrices = new double[n];
        System.out.println("Enter the product prices:");
        for (int i = 0; i < n; i++) {
            productPrices[i] = scanner.nextDouble();
        }

        System.out.println("Original Product Prices: " + Arrays.toString(productPrices));

        // Sort the array using Quick Sort
        quickSort(productPrices, 0, productPrices.length - 1);

        System.out.println("Sorted Product Prices: " + Arrays.toString(productPrices));

        scanner.close();
    }

    // Quick Sort function
    public static void quickSort(double[] array, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(array, low, high);

            // Recursively apply Quick Sort to the left and right partitions
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Partition function
    public static int partition(double[] array, int low, int high) {
        // Choose the last element as the pivot
        double pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (array[j] <= pivot) {
                i++;
                // Swap array[i] and array[j]
                swap(array, i, j);
            }
        }

        // Swap array[i + 1] and the pivot
        swap(array, i + 1, high);

        return i + 1; // Return the partition index
    }

    // Swap function
    public static void swap(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
