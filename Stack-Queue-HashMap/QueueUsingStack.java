import java.util.Stack;

class QueueUsingStacks {
    private Stack<Integer> stack1; // For enqueue operation
    private Stack<Integer> stack2; // For dequeue operation

    // Constructor
    public QueueUsingStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // Enqueue operation
    public void enqueue(int data) {
        stack1.push(data); // Push directly to stack1
    }

    // Dequeue operation
    public int dequeue() throws Exception {
        if (stack2.isEmpty()) { 
            if (stack1.isEmpty()) {
                throw new Exception("Queue is empty"); // Handle underflow
            }
            // Transfer elements from stack1 to stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop(); // Pop the top of stack2
    }

    // Peek operation to see the front element
    public int peek() throws Exception {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                throw new Exception("Queue is empty"); // Handle underflow
            }
            // Transfer elements from stack1 to stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek(); // Peek the top of stack2
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

// Test the QueueUsingStacks class
public class QueueUsingStack{
    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        try {
            queue.enqueue(10);
            queue.enqueue(20);
            queue.enqueue(30);

            System.out.println("Dequeue: " + queue.dequeue()); // Output: 10
            System.out.println("Peek: " + queue.peek());       // Output: 20
            queue.enqueue(40);
            System.out.println("Dequeue: " + queue.dequeue()); // Output: 20
            System.out.println("Dequeue: " + queue.dequeue()); // Output: 30
            System.out.println("Dequeue: " + queue.dequeue()); // Output: 40
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
