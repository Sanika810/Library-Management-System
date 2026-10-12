import java.io.IOException;
import java.util.Scanner;
import library.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        LibrarySystem librarySystem = new LibrarySystem();
        // creating a test librarian
        final String TEST_LIBRARIAN_ID = "123";
        final String TEST_LIBRARIAN_NAME = "testLibrarian";
        Librarian testLibrarian = new Librarian(TEST_LIBRARIAN_NAME, TEST_LIBRARIAN_ID);
        librarySystem.setLibrarian(testLibrarian);
        System.out.println("=====================================");
        System.out.println(" Welcome to the Library System ");
        System.out.println("=====================================");

        while (true) {      
            System.out.println("Who is using the system?");
            System.out.println("1. Librarian");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Librarian ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Librarian Name: ");
                        String name = scanner.nextLine();

                        if (id.equals(TEST_LIBRARIAN_ID) && name.equalsIgnoreCase(TEST_LIBRARIAN_NAME)) {
                            testLibrarian.performAction(librarySystem);
                        }
                        else {
                            System.out.println("Invalid Librarian credentials!\n");
                        }
                    }

                    case 2 -> {
                        try {
                            System.out.print("Enter four digit Student ID: ");
                            String S_id = scanner.nextLine();
                            if (!S_id.matches("\\d{4}")) {
                                throw new invalidUser("Invalid Student ID format. It should be 4 digits.");
                            }
                            System.out.print("Enter Student Name: ");
                            String S_name = scanner.nextLine();
                            Student student = new Student(S_name, S_id);
                            student.performAction(librarySystem);
                        } catch (invalidUser e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    

                    case 3 -> {
                        System.out.println("Thank you for using the Library System ! , Exiting...");
                        scanner.close();
                        return;
                    }

                    default -> System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                }
            } 
            else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
