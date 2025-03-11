import java.util.*;

class PairWithGivenSum {

    // Method to check if a pair exists with the given sum
    public static boolean hasPairWithSum(int[] nums, int target) {
        // Hash map to store visited numbers
        Set<Integer> seen = new HashSet<>();

        // Traverse the array
        for (int num : nums) {
            // Check if the complement (target - current number) exists in the set
            if (seen.contains(target - num)) {
                System.out.println("Pair found: (" + num + ", " + (target - num) + ")");
                return true;
            }
            // Add the current number to the set
            seen.add(num);
        }

        // If no pair is found, return false
        System.out.println("No pair found with the given sum.");
        return false;
    }

    // Main method to test the PairWithGivenSum class
    public static void main(String[] args) {
        int[] nums = {10, 15, 3, 7};
        int target = 17;

        hasPairWithSum(nums, target);
    }
}
