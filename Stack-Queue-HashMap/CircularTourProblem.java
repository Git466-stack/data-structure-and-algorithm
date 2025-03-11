class CircularTour {

    // Method to find the starting point of the circular tour
    public static int findStartingPoint(int[] petrol, int[] distance) {
        int n = petrol.length;

        // Initialize variables
        int start = 0; // Starting point of the tour
        int surplus = 0; // Surplus petrol in the tank
        int deficit = 0; // Deficit to be covered

        // Traverse all petrol pumps
        for (int i = 0; i < n; i++) {
            surplus += petrol[i] - distance[i];

            // If surplus becomes negative, current pump cannot be the starting point
            if (surplus < 0) {
                start = i + 1; // Move the starting point to the next pump
                deficit += surplus; // Add the deficit
                surplus = 0; // Reset surplus
            }
        }

        // If the total surplus + deficit is non-negative, a solution exists
        return (surplus + deficit >= 0) ? start : -1;
    }

    // Main method to test the CircularTour class
    public static void main(String[] args) {
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};

        int startingPoint = findStartingPoint(petrol, distance);

        if (startingPoint != -1) {
            System.out.println("The starting point is: " + startingPoint);
        } else {
            System.out.println("No solution exists");
        }
    }
}
