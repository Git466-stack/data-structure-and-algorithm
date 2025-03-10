class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;

    // Constructor
    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head;
    private Book tail;
    private int totalBooks;

    // Add a book at the beginning
    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        totalBooks++;
    }

    // Add a book at the end
    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        totalBooks++;
    }

    // Add a book at a specific position
    public void addAtPosition(int position, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (position <= 0 || position > totalBooks + 1) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        if (position == totalBooks + 1) {
            addAtEnd(title, author, genre, bookId, isAvailable);
            return;
        }
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book temp = head;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
        totalBooks++;
    }

    // Remove a book by Book ID
    public void removeByBookId(int bookId) {
        if (head == null) {
            System.out.println("Library is empty!");
            return;
        }
        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Book with ID " + bookId + " not found!");
            return;
        }
        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        totalBooks--;
        System.out.println("Book with ID " + bookId + " removed successfully.");
    }

    // Search for a book by Title or Author
    public void searchBook(String title, String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if ((title != null && temp.title.equalsIgnoreCase(title)) ||
                (author != null && temp.author.equalsIgnoreCase(author))) {
                System.out.println("[Title: " + temp.title + ", Author: " + temp.author +
                                   ", Genre: " + temp.genre + ", ID: " + temp.bookId +
                                   ", Available: " + temp.isAvailable + "]");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }

    // Update a book's Availability Status
    public void updateAvailabilityStatus(int bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = status;
                System.out.println("Availability status updated for Book ID " + bookId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID " + bookId + " not found!");
    }

    // Display all books in forward order
    public void displayBooksForward() {
        if (head == null) {
            System.out.println("Library is empty!");
            return;
        }
        System.out.println("Books in Forward Order:");
        Book temp = head;
        while (temp != null) {
            System.out.println("[Title: " + temp.title + ", Author: " + temp.author +
                               ", Genre: " + temp.genre + ", ID: " + temp.bookId +
                               ", Available: " + temp.isAvailable + "]");
            temp = temp.next;
        }
    }

    // Display all books in reverse order
    public void displayBooksReverse() {
        if (tail == null) {
            System.out.println("Library is empty!");
            return;
        }
        System.out.println("Books in Reverse Order:");
        Book temp = tail;
        while (temp != null) {
            System.out.println("[Title: " + temp.title + ", Author: " + temp.author +
                               ", Genre: " + temp.genre + ", ID: " + temp.bookId +
                               ", Available: " + temp.isAvailable + "]");
            temp = temp.prev;
        }
    }

    // Count the total number of books
    public void countTotalBooks() {
        System.out.println("Total number of books in the library: " + totalBooks);
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addAtBeginning("Book A", "Author A", "Fiction", 101, true);
        library.addAtEnd("Book B", "Author B", "Non-Fiction", 102, true);
        library.addAtPosition(2, "Book C", "Author C", "Science", 103, false);

        // Display books in forward and reverse order
        library.displayBooksForward();
        library.displayBooksReverse();

        // Search for a book
        library.searchBook("Book A", null);
        library.searchBook(null, "Author B");

        // Update availability status
        library.updateAvailabilityStatus(103, true);

        // Remove a book
        library.removeByBookId(102);

        // Display books after removal
        library.displayBooksForward();

        // Count total books
        library.countTotalBooks();
    }
}
