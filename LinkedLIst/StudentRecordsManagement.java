class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next; // Pointer to the next node

    // Constructor
    public Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    private Student head; // Head of the linked list

    // Add a student at the beginning
    public void addAtBeginning(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    // Add a student at the end
    public void addAtEnd(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    // Add a student at a specific position
    public void addAtPosition(int position, int rollNumber, String name, int age, String grade) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            addAtBeginning(rollNumber, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNumber, name, age, grade);
        Student temp = head;
        for (int i = 1; i < position - 1; i++) {
            if (temp == null) {
                System.out.println("Position out of bounds!");
                return;
            }
            temp = temp.next;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    // Delete a student by Roll Number
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.rollNumber == rollNumber) {
            head = head.next;
            System.out.println("Student with Roll Number " + rollNumber + " deleted.");
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != rollNumber) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found!");
            return;
        }
        temp.next = temp.next.next;
        System.out.println("Student with Roll Number " + rollNumber + " deleted.");
    }

    // Search for a student by Roll Number
    public Student searchByRollNumber(int rollNumber) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Update a student's grade by Roll Number
    public void updateGradeByRollNumber(int rollNumber, String newGrade) {
        Student student = searchByRollNumber(rollNumber);
        if (student == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found!");
            return;
        }
        student.grade = newGrade;
        System.out.println("Grade updated successfully for Roll Number " + rollNumber + ".");
    }

    // Display all student records
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records to display!");
            return;
        }
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll Number: " + temp.rollNumber + ", Name: " + temp.name +
                    ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordsManagement {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();

        // Adding students
        studentList.addAtEnd(1, "Alice", 20, "A");
        studentList.addAtBeginning(2, "Bob", 22, "B");
        studentList.addAtPosition(2, 3, "Charlie", 21, "C");

        // Displaying all students
        System.out.println("All Student Records:");
        studentList.displayAll();

        // Searching for a student
        System.out.println("\nSearching for Roll Number 2:");
        Student foundStudent = studentList.searchByRollNumber(2);
        if (foundStudent != null) {
            System.out.println("Found: Roll Number: " + foundStudent.rollNumber + ", Name: " + foundStudent.name +
                    ", Age: " + foundStudent.age + ", Grade: " + foundStudent.grade);
        } else {
            System.out.println("Student not found!");
        }

        // Updating a student's grade
        System.out.println("\nUpdating grade for Roll Number 1:");
        studentList.updateGradeByRollNumber(1, "A+");

        // Displaying all students after the update
        System.out.println("\nAll Student Records After Update:");
        studentList.displayAll();

        // Deleting a student
        System.out.println("\nDeleting student with Roll Number 3:");
        studentList.deleteByRollNumber(3);

        // Displaying all students after deletion
        System.out.println("\nAll Student Records After Deletion:");
        studentList.displayAll();
    }
}
