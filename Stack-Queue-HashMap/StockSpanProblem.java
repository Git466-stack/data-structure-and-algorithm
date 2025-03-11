import java.util.Stack;

class StockSpan {
    // Method to calculate stock spans
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] spans = new int[n]; // Array to store spans
        Stack<Integer> stack = new Stack<>(); // Stack to store indices

        for (int i = 0; i < n; i++) {
            // Pop elements from the stack while stack is not empty and the current price is greater than or equal to the price at the top index
            while (!stack.isEmpty() && prices[i] >= prices[stack.peek()]) {
                stack.pop();
            }

            // If the stack is empty, the span is the entire range from the start
            spans[i] = stack.isEmpty() ? i + 1 : i - stack.peek();

            // Push the current index onto the stack
            stack.push(i);
        }

        return spans;
    }

    // Main method to test the StockSpan class
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = calculateSpan(prices);

        System.out.println("Stock prices: ");
        for (int price : prices) {
            System.out.print(price + " ");
        }
        System.out.println();

        System.out.println("Stock spans: ");
        for (int span : spans) {
            System.out.print(span + " ");
        }
    }
}
