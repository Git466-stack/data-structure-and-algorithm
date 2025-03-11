import java.util.Stack;

class SortStackRecursively {

    // Method to sort the stack
    public static void sortStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            // Pop the top element
            int top = stack.pop();

            // Recursively sort the remaining stack
            sortStack(stack);

            // Insert the popped element back in the sorted order
            insertInSortedOrder(stack, top);
        }
    }

    // Helper method to insert an element into a sorted stack
    private static void insertInSortedOrder(Stack<Integer> stack, int element) {
        // Base condition: If the stack is empty or the top element is smaller than the current element
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }

        // Pop the top element and hold it
        int top = stack.pop();

        // Recursively insert the current element
        insertInSortedOrder(stack, element);

        // Push the held element back into the stack
        stack.push(top);
    }

    // Method to print the stack elements
    public static void printStack(Stack<Integer> stack) {
        for (int elem : stack) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    // Main method to test the sortStack method
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);

        System.out.println("Original Stack:");
        printStack(stack);

        sortStack(stack);

        System.out.println("Sorted Stack:");
        printStack(stack);
    }
}
