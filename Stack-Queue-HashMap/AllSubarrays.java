import java.util.*;

class ZeroSumSubarrays {

    // Method to find all subarrays with zero sum
    public static List<int[]> findZeroSumSubarrays(int[] nums) {
        // List to store the starting and ending indices of all zero-sum subarrays
        List<int[]> result = new ArrayList<>();
        // Map to store the cumulative sum and the list of indices where it occurs
        Map<Integer, List<Integer>> map = new HashMap<>();
        // Initialize cumulative sum
        int cumulativeSum = 0;

        // Add an initial entry for cumulative sum 0 at index -1
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);

        // Traverse through the array
        for (int i = 0; i < nums.length; i++) {
            // Update cumulative sum
            cumulativeSum += nums[i];

            // If cumulative sum exists in the map, zero-sum subarrays exist
            if (map.containsKey(cumulativeSum)) {
                // Get the list of indices where this sum was seen before
                List<Integer> indices = map.get(cumulativeSum);

                // For each index, create a subarray from index + 1 to current index
                for (int start : indices) {
                    result.add(new int[]{start + 1, i});
                }
            }

            // Add the current index to the list of indices for this cumulative sum
            map.putIfAbsent(cumulativeSum, new ArrayList<>());
            map.get(cumulativeSum).add(i);
        }

        return result;
    }

    // Helper method to print the subarrays
    public static void printSubarrays(List<int[]> subarrays, int[] nums) {
        for (int[] range : subarrays) {
            System.out.print("Subarray [");
            for (int i = range[0]; i <= range[1]; i++) {
                System.out.print(nums[i] + (i < range[1] ? ", " : ""));
            }
            System.out.println("]");
        }
    }

    // Main method to test the ZeroSumSubarrays class
    public static void main(String[] args) {
        int[] nums = {3, 4, -7, 1, 3, 3, 1, -4};

        List<int[]> zeroSumSubarrays = findZeroSumSubarrays(nums);

        System.out.println("Zero-sum subarrays:");
        printSubarrays(zeroSumSubarrays, nums);
    }
}
