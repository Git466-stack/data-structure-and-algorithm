class Movie {
    String title;
    String director;
    int yearOfRelease;
    double rating;
    Movie next; // Pointer to the next node
    Movie prev; // Pointer to the previous node

    // Constructor
    public Movie(String title, String director, int yearOfRelease, double rating) {
        this.title = title;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    private Movie head; // Pointer to the first movie
    private Movie tail; // Pointer to the last movie

    // Add a movie at the beginning
    public void addAtBeginning(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    // Add a movie at the end
    public void addAtEnd(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    // Add a movie at a specific position
    public void addAtPosition(int position, String title, String director, int yearOfRelease, double rating) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            addAtBeginning(title, director, yearOfRelease, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        Movie temp = head;
        for (int i = 1; i < position - 1; i++) {
            if (temp == null) {
                System.out.println("Position out of bounds!");
                return;
            }
            temp = temp.next;
        }
        if (temp == null) {
            addAtEnd(title, director, yearOfRelease, rating);
        } else {
            newMovie.next = temp.next;
            if (temp.next != null) {
                temp.next.prev = newMovie;
            } else {
                tail = newMovie;
            }
            temp.next = newMovie;
            newMovie.prev = temp;
        }
    }

    // Remove a movie by title
    public void removeByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Movie with title \"" + title + "\" not found!");
            return;
        }
        if (temp == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        System.out.println("Movie with title \"" + title + "\" removed successfully.");
    }

    // Search for a movie by Director or Rating
    public void searchByDirectorOrRating(String director, double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director) || temp.rating == rating) {
                System.out.println("Found: Title: " + temp.title + ", Director: " + temp.director +
                        ", Year: " + temp.yearOfRelease + ", Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movies found with Director \"" + director + "\" or Rating " + rating + ".");
        }
    }

    // Display all movies in forward order
    public void displayForward() {
        if (head == null) {
            System.out.println("No movies to display!");
            return;
        }
        System.out.println("Movies in forward order:");
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director +
                    ", Year: " + temp.yearOfRelease + ", Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    // Display all movies in reverse order
    public void displayReverse() {
        if (tail == null) {
            System.out.println("No movies to display!");
            return;
        }
        System.out.println("Movies in reverse order:");
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director +
                    ", Year: " + temp.yearOfRelease + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
    }

    // Update a movie's rating by title
    public void updateRatingByTitle(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated for movie \"" + title + "\".");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie with title \"" + title + "\" not found!");
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieList movieList = new MovieList();

        // Adding movies
        movieList.addAtBeginning("Inception", "Christopher Nolan", 2010, 8.8);
        movieList.addAtEnd("The Dark Knight", "Christopher Nolan", 2008, 9.0);
        movieList.addAtPosition(2, "Interstellar", "Christopher Nolan", 2014, 8.6);

        // Displaying movies
        movieList.displayForward();

        // Searching for movies
        System.out.println("\nSearching for movies by Director 'Christopher Nolan' or Rating 8.8:");
        movieList.searchByDirectorOrRating("Christopher Nolan", 8.8);

        // Updating a movie's rating
        System.out.println("\nUpdating rating for 'Inception':");
        movieList.updateRatingByTitle("Inception", 9.1);

        // Displaying movies in reverse order
        System.out.println("\nMovies in reverse order:");
        movieList.displayReverse();

        // Removing a movie
        System.out.println("\nRemoving 'Interstellar':");
        movieList.removeByTitle("Interstellar");

        // Displaying movies after removal
        System.out.println("\nMovies after removal:");
        movieList.displayForward();
    }
}
