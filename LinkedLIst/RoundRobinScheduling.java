class Process {
    int processId;
    int burstTime;
    int priority;
    int waitingTime = 0;
    int turnaroundTime = 0;
    Process next;

    // Constructor
    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;
    private int totalProcesses = 0;

    // Add a new process at the end of the circular list
    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            tail.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
        totalProcesses++;
    }

    // Remove a process by Process ID
    public void removeProcess(int processId) {
        if (head == null) {
            System.out.println("No processes in the queue!");
            return;
        }
        Process current = head;
        Process previous = null;

        do {
            if (current.processId == processId) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    tail = previous;
                    tail.next = head;
                } else {
                    previous.next = current.next;
                }
                totalProcesses--;
                System.out.println("Process " + processId + " removed after execution.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Process " + processId + " not found!");
    }

    // Simulate round-robin scheduling
    public void simulateScheduling(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule!");
            return;
        }

        int currentTime = 0;
        Process current = head;
        System.out.println("Starting Round Robin Scheduling...");

        while (totalProcesses > 0) {
            if (current.burstTime > 0) {
                System.out.println("Executing Process ID: " + current.processId);
                if (current.burstTime > timeQuantum) {
                    current.burstTime -= timeQuantum;
                    currentTime += timeQuantum;
                } else {
                    currentTime += current.burstTime;
                    current.turnaroundTime = currentTime;
                    current.burstTime = 0;
                    current.waitingTime = current.turnaroundTime - current.burstTime;
                    removeProcess(current.processId);
                }
            }
            current = current.next;
        }
    }

    // Display the list of processes
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue!");
            return;
        }
        Process current = head;
        System.out.println("Processes in the circular queue:");
        do {
            System.out.println("[Process ID: " + current.processId + ", Burst Time: " +
                               current.burstTime + ", Priority: " + current.priority + "]");
            current = current.next;
        } while (current != head);
    }

    // Calculate and display average waiting time and turnaround time
    public void calculateAverageTimes() {
        if (head == null) {
            System.out.println("No processes to calculate times!");
            return;
        }

        Process current = head;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int processCount = 0;

        do {
            totalWaitingTime += current.waitingTime;
            totalTurnaroundTime += current.turnaroundTime;
            processCount++;
            current = current.next;
        } while (current != head);

        double avgWaitingTime = (double) totalWaitingTime / processCount;
        double avgTurnaroundTime = (double) totalTurnaroundTime / processCount;

        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        // Adding processes
        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);

        // Display initial processes
        scheduler.displayProcesses();

        // Simulate scheduling with a time quantum of 3
        scheduler.simulateScheduling(3);

        // Display remaining processes (if any)
        scheduler.displayProcesses();

        // Calculate average times
        scheduler.calculateAverageTimes();
    }
}
