package library;

import java.util.Scanner;

public class Librarian extends User 
{
    private static final Scanner scanner = new Scanner(System.in);

    // Constructor
    public Librarian(String name, String userId) 
    {
        super(name, userId);
    }

    // Override displayMenu method for Librarian
    @Override
    public void displayMenu() 
    {
        System.out.println("\n--- Librarian Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book by Title");
        System.out.println("3. View All Books");
        System.out.println("4. View Student records ");  
        System.out.println("5. Exit");
    }

    // Add a new book to the library
    public void addBook(LibrarySystem librarySystem) 
    {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();

        System.out.print("Enter number of copies: ");
        int numCopies = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        Book existingBook = librarySystem.searchBookById(bookId);//checking if the book is already present in the library

        if (existingBook == null) 
        {
            // Create and add new book if it doesn't exist
            Book book = new Book(title, author, bookId, numCopies);
            librarySystem.addBook(book);
            System.out.println("Book added successfully: " + title);
        } 
        else 
        {
            // Update copies if book already exists
            existingBook.setNumCopies(existingBook.getNumCopies() + numCopies);
            System.out.println("Book already exists. Updated number of copies: " + existingBook.getNumCopies());
        }
        librarySystem.SaveBookRecords(); // Save the updated book records to file
    }


    // Search for a book by title
    public void searchBookByTitle(LibrarySystem librarySystem) 
    {
        System.out.print("Enter the title to search: ");
        String title = scanner.nextLine();
        try {
            Book book = librarySystem.searchBookByTitle(title);
            if(book == null)
            {
                throw new bookNotFound("Book not found with title: " + title);
            }
            book.displayBookDetails(); // Display book details if found
        } catch (bookNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    // View all books in the library
    public void viewAllBooks(LibrarySystem librarySystem) {
        librarySystem.displayBooks(); // Display all books in the library
    }

    //display student records
    public void viewStudentRecords(LibrarySystem librarySystem) 
    {
        System.out.println("Student Records:");
        librarySystem.displayStudents(); // Display all student records
    }

    // Perform actions from the menu
    @Override
    public void performAction(LibrarySystem librarySystem) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> addBook(librarySystem);
                case 2 -> searchBookByTitle(librarySystem);
                case 3 -> viewAllBooks(librarySystem);
                case 4 -> viewStudentRecords(librarySystem);
                case 5 -> {
                    exit = true;
                    System.out.println("Exiting Librarian Menu.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    @Override
    public void displayUserDetails() 
    {
        System.out.println("Librarian Name: " + name);
        System.out.println("User ID: " + userId);
    }
}
