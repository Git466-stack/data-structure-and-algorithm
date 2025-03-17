import java.util.Arrays;
import java.util.Scanner;

public class HeapSortExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of salary demands
        System.out.print("Enter the number of salary demands: ");
        int n = scanner.nextInt();

        // Input the salary demands
        int[] salaries = new int[n];
        System.out.println("Enter the salary demands:");
        for (int i = 0; i < n; i++) {
            salaries[i] = scanner.nextInt();
        }

        System.out.println("Original Salary Demands: " + Arrays.toString(salaries));

        // Sort the array using Heap Sort
        heapSort(salaries);

        System.out.println("Sorted Salary Demands: " + Arrays.toString(salaries));

        scanner.close();
    }

    // Heap Sort function
    public static void heapSort(int[] array) {
        int n = array.length;

        // Step 1: Build a Max Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Step 2: Extract elements from the heap
        for (int i = n - 1; i > 0; i--) {
            // Move the current root (largest) to the end
            swap(array, 0, i);

            // Reheapify the reduced heap
            heapify(array, i, 0);
        }
    }

    // Heapify function to maintain the heap property
    public static void heapify(int[] array, int size, int root) {
        int largest = root;       // Initialize the largest as root
        int left = 2 * root + 1;  // Left child index
        int right = 2 * root + 2; // Right child index

        // Check if the left child is larger than the root
        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        // Check if the right child is larger than the current largest
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        // If the largest is not the root, swap and continue heapifying
        if (largest != root) {
            swap(array, root, largest);
            heapify(array, size, largest);
        }
    }

    // Swap function
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
