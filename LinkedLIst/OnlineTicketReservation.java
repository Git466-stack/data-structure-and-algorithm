class Ticket {
    int ticketId; // Unique Ticket ID
    String customerName; // Customer's Name
    String movieName; // Movie Name
    String seatNumber; // Seat Number
    String bookingTime; // Booking Time
    Ticket next; // Pointer to the next ticket

    // Constructor
    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head; // Head node of the circular linked list
    private Ticket tail; // Tail node of the circular linked list
    private int totalTickets; // Total number of booked tickets

    // Constructor
    public TicketReservationSystem() {
        this.head = null;
        this.tail = null;
        this.totalTickets = 0;
    }

    // Add a new ticket at the end
    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);

        if (head == null) { // If the list is empty
            head = tail = newTicket;
            newTicket.next = head; // Circular pointer
        } else {
            tail.next = newTicket;
            newTicket.next = head; // Circular pointer
            tail = newTicket;
        }

        totalTickets++;
        System.out.println("Ticket added successfully: " + ticketId);
    }

    // Remove a ticket by Ticket ID
    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket current = head;
        Ticket previous = tail;

        do {
            if (current.ticketId == ticketId) {
                if (current == head) { // If it's the first ticket
                    head = head.next;
                    tail.next = head; // Maintain circular nature
                } else {
                    previous.next = current.next;
                    if (current == tail) { // If it's the last ticket
                        tail = previous;
                    }
                }

                totalTickets--;
                System.out.println("Ticket removed successfully: " + ticketId);
                return;
            }

            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket ID not found: " + ticketId);
    }

    // Display all tickets
    public void displayAllTickets() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        Ticket current = head;
        System.out.println("Current tickets:");

        do {
            System.out.println("Ticket ID: " + current.ticketId + ", Customer Name: " + current.customerName +
                    ", Movie Name: " + current.movieName + ", Seat Number: " + current.seatNumber +
                    ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    // Search for a ticket by Customer Name or Movie Name
    public void searchTicket(String searchKey) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        Ticket current = head;
        boolean found = false;

        do {
            if (current.customerName.equalsIgnoreCase(searchKey) || current.movieName.equalsIgnoreCase(searchKey)) {
                System.out.println("Ticket Found: Ticket ID: " + current.ticketId + ", Customer Name: " + current.customerName +
                        ", Movie Name: " + current.movieName + ", Seat Number: " + current.seatNumber +
                        ", Booking Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No ticket found for search key: " + searchKey);
        }
    }

    // Calculate total number of tickets
    public void displayTotalTickets() {
        System.out.println("Total number of booked tickets: " + totalTickets);
    }
}

public class OnlineTicketReservation {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        // Add tickets
        system.addTicket(1, "Alice", "Inception", "A1", "10:00 AM");
        system.addTicket(2, "Bob", "Interstellar", "B2", "12:00 PM");
        system.addTicket(3, "Charlie", "Inception", "A2", "10:00 AM");

        // Display all tickets
        system.displayAllTickets();

        // Search tickets
        system.searchTicket("Inception");
        system.searchTicket("Bob");

        // Remove a ticket
        system.removeTicket(2);

        // Display all tickets after removal
        system.displayAllTickets();

        // Display total tickets
        system.displayTotalTickets();
    }
}
