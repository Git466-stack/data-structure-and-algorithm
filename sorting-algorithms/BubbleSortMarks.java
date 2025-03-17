import java.util.Scanner;

public class BubbleSortMarks {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();

        int[] marks = new int[n];
        System.out.println("Enter the marks of the students:");
        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (marks[j] > marks[j + 1]) {
                   
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                    swapped = true;
                }
            }
           
            if (!swapped) break;
        }

        System.out.println("Sorted marks in ascending order:");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
    }
}
