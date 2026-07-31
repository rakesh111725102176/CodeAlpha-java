import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();

    public static void addStudent(Scanner sc) {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        sc.nextLine();

        students.add(new Student(name, marks));

        System.out.println("Student Added Successfully!\n");
    }

    public static void displayReport() {

        if (students.isEmpty()) {
            System.out.println("No student records found.\n");
            return;
        }

        double total = 0;
        double highest = students.get(0).getMarks();
        double lowest = students.get(0).getMarks();

        System.out.println("\n------ Student Report ------");

        for (Student s : students) {

            System.out.println("Name : " + s.getName()
                    + " | Marks : " + s.getMarks());

            total += s.getMarks();

            if (s.getMarks() > highest)
                highest = s.getMarks();

            if (s.getMarks() < lowest)
                lowest = s.getMarks();
        }

        double average = total / students.size();

        System.out.println("----------------------------");
        System.out.printf("Average Marks : %.2f%n", average);
        System.out.println("Highest Marks : " + highest);
        System.out.println("Lowest Marks  : " + lowest);
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("===== Student Grade Tracker =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Report");
            System.out.println("3. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    displayReport();
                    break;

                case 3:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice\n");
            }
        }
    }
}
