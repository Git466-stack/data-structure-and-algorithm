import java.util.*;

class SlidingWindowMaximum {
    // Method to find the maximum in each sliding window
    public static int[] findMaxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0]; // Handle edge cases
        }

        int n = nums.length;
        int[] result = new int[n - k + 1]; // Result array to store maximums
        Deque<Integer> deque = new LinkedList<>(); // Deque to store indices of useful elements

        for (int i = 0; i < n; i++) {
            // Remove indices that are out of the current window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove indices of elements smaller than the current element
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add the current index to the deque
            deque.offerLast(i);

            // The maximum for the current window is at the front of the deque
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    // Main method to test the SlidingWindowMaximum class
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] maxSlidingWindow = findMaxSlidingWindow(nums, k);

        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("Sliding window maximums: " + Arrays.toString(maxSlidingWindow));
    }
}
