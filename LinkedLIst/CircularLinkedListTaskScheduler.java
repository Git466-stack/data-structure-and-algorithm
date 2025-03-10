import java.util.Date;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate; // For simplicity, using a string for the due date
    Task next; // Pointer to the next task

    // Constructor
    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head = null; // Pointer to the first task
    private Task tail = null; // Pointer to the last task
    private Task current = null; // Pointer to the current task for viewing and cycling

    // Add a task at the beginning
    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head; // Maintain circular structure
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head; // Maintain circular structure
        }
    }

    // Add a task at the end
    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (tail == null) {
            head = tail = newTask;
            tail.next = head; // Maintain circular structure
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head; // Maintain circular structure
        }
    }

    // Add a task at a specific position
    public void addAtPosition(int position, int taskId, String taskName, int priority, String dueDate) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (position == 1) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task temp = head;
        for (int i = 1; i < position - 1; i++) {
            if (temp == null || temp.next == head) {
                System.out.println("Position out of bounds!");
                return;
            }
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
        if (temp == tail) {
            tail = newTask;
        }
    }

    // Remove a task by Task ID
    public void removeByTaskId(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty!");
            return;
        }
        Task temp = head;
        Task prev = null;
        do {
            if (temp.taskId == taskId) {
                if (temp == head && temp == tail) { // Only one task in the list
                    head = tail = null;
                } else if (temp == head) { // Removing the head task
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) { // Removing the tail task
                    prev.next = head;
                    tail = prev;
                } else { // Removing a task in the middle
                    prev.next = temp.next;
                }
                System.out.println("Task with ID " + taskId + " removed successfully.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task with ID " + taskId + " not found!");
    }

    // View the current task and move to the next task
    public void viewCurrentTask() {
        if (head == null) {
            System.out.println("Task list is empty!");
            return;
        }
        if (current == null) {
            current = head; // Start from the head if current is not set
        }
        System.out.println("Current Task: [ID: " + current.taskId + ", Name: " + current.taskName +
                ", Priority: " + current.priority + ", Due Date: " + current.dueDate + "]");
        current = current.next; // Move to the next task
    }

    // Display all tasks starting from the head
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks to display!");
            return;
        }
        System.out.println("All Tasks:");
        Task temp = head;
        do {
            System.out.println("[ID: " + temp.taskId + ", Name: " + temp.taskName +
                    ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate + "]");
            temp = temp.next;
        } while (temp != head);
    }

    // Search for a task by Priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("Task list is empty!");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Found Task: [ID: " + temp.taskId + ", Name: " + temp.taskName +
                        ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate + "]");
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with Priority " + priority + ".");
        }
    }
}

public class CircularLinkedListTaskScheduler {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Add tasks
        scheduler.addAtBeginning(1, "Design Database", 2, "2025-03-15");
        scheduler.addAtEnd(2, "Write API Documentation", 1, "2025-03-20");
        scheduler.addAtPosition(2, 3, "Develop Frontend", 3, "2025-03-18");

        // Display all tasks
        scheduler.displayAllTasks();

        // View current task and cycle through tasks
        System.out.println("\nViewing Current Task:");
        scheduler.viewCurrentTask();
        scheduler.viewCurrentTask();

        // Search by priority
        System.out.println("\nSearching for tasks with Priority 3:");
        scheduler.searchByPriority(3);

        // Remove a task
        System.out.println("\nRemoving Task with ID 2:");
        scheduler.removeByTaskId(2);

        // Display all tasks after removal
        System.out.println("\nAll Tasks After Removal:");
        scheduler.displayAllTasks();
    }
}
