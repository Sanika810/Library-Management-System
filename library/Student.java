package library;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User 
{
    protected ArrayList<Book> borrowedBooks; //stores books borrowed by the student

    private static final Scanner scanner = new Scanner(System.in);
    
    public Student(String name, String userId) // Constructor that uses the constructor of the parent class User
    {
        super(name, userId);
        this.borrowedBooks = new ArrayList<>(); // Initialize the borrowedBooks list
    }

    // Override displayMenu method for Student to show relevant options
    @Override
    public void displayMenu() 
    {
        System.out.println("\n--- Student Menu ---");
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. Exit");
    }
    // Student borrows a book (decreases the available copies)
    public void borrowBook(LibrarySystem librarySystem) 
    {
        System.out.print("Enter the title of the book you want to search for: ");
        String title = scanner.nextLine();
        try 
        {        
            Book book = librarySystem.searchBookByTitle(title);  //searching for the book by title
            if(book==null)
            {
                throw new bookNotFound("Book not found with title: " + title);
            }
            System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor() + "(ID : " + book.getBookId() + ")\n");

            if (book.isBookAvailable()) // Check if the book is available
            {
                book.setNumCopies(book.getNumCopies() - 1); // Decrease the number of copies available
                Book borrowedCopy = new Book(book.getTitle(),book.getAuthor(),book.getBookId(),1);// Create a deep copy to track the borrow
                borrowedBooks.add(borrowedCopy);//add the book to the borrowed list)
                if(book.getNumCopies() == 0) // If no copies are left, set the book as unavailable
                {
                    book.isAvailable = false;
                }
                System.out.println("You have successfully borrowed " + book.getTitle() + "(ID : " + book.getBookId() + ")\n");
                librarySystem.SaveBookRecords();
            } 
            else 
            {
                System.out.println("The book is not available.\n");
            }
        } 
        catch (bookNotFound e) 
        {
            System.out.println(e.getMessage()); // Print the exception message if the book is not found
        }
    }

    // Student returns a book (increases the available copies)
    public void returnBook(LibrarySystem librarySystem) {
        try {
            User user = librarySystem.searchUserById(this.userId);     
            if (user == null) {
                throw new invalidUser("You are not registered in the library system: " + this.userId);
            }
            Student stud= (Student) user; // Cast to Student to access borrowedBooks
            System.out.print("Enter the ID of the book you want to return: ");
            String id = scanner.nextLine().trim(); 

            try {
                Book book = librarySystem.searchBookById(id);
                if (book == null) {
                    throw new bookNotFound("Book not found in library system with ID: " + id);
                }

                Book bookToReturn = null;
                for (Book b : stud.borrowedBooks) {
                    if (b.getBookId().trim().equalsIgnoreCase(id)) {
                        bookToReturn = b;
                        break;
                    }
                }

                if (bookToReturn == null) {
                    throw new bookNotFound("You did not borrow this book with ID: " + id);
                }
                stud.borrowedBooks.remove(bookToReturn);
                book.setNumCopies(book.getNumCopies() + 1);
                System.out.println("You have successfully returned the book: " + book.getTitle());
                librarySystem.SaveBookRecords(); // Save the updated book records
                librarySystem.SaveStudentRecords(); // Save the updated student records
            } 
            catch (bookNotFound e) {
                System.out.println("Book error: " + e.getMessage());
            }

        } catch (invalidUser e) {
            System.out.println("User error: " + e.getMessage());
        }
    }

    @Override
    public void displayUserDetails() 
    {
        System.out.println("Student: " + name + " | ID: " + userId);

        if (borrowedBooks.isEmpty()) {
            System.out.println("Borrowed Books: None");
        } else {
            System.out.print("Borrowed Books: ");
            for (Book book : borrowedBooks) {
                System.out.print(book.getTitle() + " (ID: " + book.getBookId() + ") | ");
            }
            System.out.println(); // Move to next line after printing books
        }
    }


    // Implementing the performAction method that uses the menu
    @Override
    public void performAction(LibrarySystem librarySystem) 
    {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1 -> { 
                    User existingStudent = librarySystem.searchUserById(this.userId);
                    if(existingStudent == null) {
                        librarySystem.addUser(this);
                        this.borrowBook(librarySystem);
                    }
                    else
                    {
                        Student stud = (Student) existingStudent;
                        stud.borrowBook(librarySystem);
                    }
                    librarySystem.SaveStudentRecords();
                }
                case 2 -> returnBook(librarySystem);
                case 3 -> {
                    exit = true;
                    System.out.println("Exiting Student Menu.");
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
