import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    void addGrade(int grade) {
        grades.add(grade);
    }

    double getAverage() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int g : grades) {
            sum += g;
        }
        return (double) sum / grades.size();
    }

    int getHighest() {
        if (grades.isEmpty()) {
            return -1; 
        }
        int max = Integer.MIN_VALUE;
        for (int g : grades) {
            if (g > max) max = g;
        }
        return max;
    }

    int getLowest() {
        if (grades.isEmpty()) {
            return -1; 
        }
        int min = Integer.MAX_VALUE;
        for (int g : grades) {
            if (g < min) min = g;
        }
        return min;
    }

    void printSummary() {
        System.out.println("Name: " + name);
        System.out.println("Grades: " + grades);
        
        if (grades.isEmpty()) {
            System.out.println("No grades recorded yet.");
        } else {
            System.out.printf("Average: %.2f%n", getAverage());
            System.out.println("Highest: " + getHighest());
            System.out.println("Lowest: " + getLowest());
        }
        System.out.println("----------------------------");
    }
}

public class StudentGradeTracker {
    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("==== Student Grade Tracker ====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grades to Student");
            System.out.println("3. View Summary Report");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            
            choice = getValidInteger();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrades();
                    break;
                case 3:
                    viewReport();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 0-3.");
            }

        } while (choice != 0);
        
        sc.close(); 
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Please try again.\n");
            return;
        }
        
        for (Student s : studentList) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.println("Student with this name already exists!\n");
                return;
            }
        }
        
        studentList.add(new Student(name));
        System.out.println("Student '" + name + "' added successfully!\n");
    }

    static void addGrades() {
        if (studentList.isEmpty()) {
            System.out.println("No students available. Add a student first.\n");
            return;
        }

        System.out.println("Choose student:");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println((i + 1) + ". " + studentList.get(i).name);
        }

        System.out.print("Enter student number (1-" + studentList.size() + "): ");
        int index = getValidInteger() - 1;

        if (index < 0 || index >= studentList.size()) {
            System.out.println("Invalid student number. Please try again.\n");
            return;
        }

        Student s = studentList.get(index);
        System.out.print("How many grades to add? ");
        int n = getValidInteger();
        
        if (n <= 0) {
            System.out.println("Number of grades must be positive.\n");
            return;
        }
        
        for (int i = 0; i < n; i++) {
            int grade;
            do {
                System.out.print("Enter grade " + (i + 1) + " (0-100): ");
                grade = getValidInteger();
                
                if (grade < 0 || grade > 100) {
                    System.out.println("Please enter a grade between 0 and 100.");
                }
            } while (grade < 0 || grade > 100);
            
            s.addGrade(grade);
        }
        System.out.println("Grades added successfully!\n");
    }

    static void viewReport() {
        if (studentList.isEmpty()) {
            System.out.println("No student data to show.\n");
            return;
        }

        System.out.println("\n===== Summary Report =====");
        for (Student s : studentList) {
            s.printSummary();
        }
    }
    
    
    static int getValidInteger() {
        while (true) {
            try {
                int value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
                sc.nextLine(); 
            }
        }
    }
}