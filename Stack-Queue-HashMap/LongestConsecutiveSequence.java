import java.util.*;

class LongestConsecutiveSequence {

    // Method to find the length of the longest consecutive sequence
    public static int findLongestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // Edge case: empty array
        }

        // Use a HashSet for quick lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // Iterate over the numbers in the set
        for (int num : numSet) {
            // Start a new streak only if the current number is the beginning of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Count consecutive numbers
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // Update the longest streak
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    // Main method to test the LongestConsecutiveSequence class
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};

        int longestSequence = findLongestConsecutive(nums);
        System.out.println("The length of the longest consecutive sequence is: " + longestSequence);
    }
}
