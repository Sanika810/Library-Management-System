package library;
import java.io.IOException;
import java.util.ArrayList;

public final class LibrarySystem
{
    private Librarian Librarian;
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public LibrarySystem() throws IOException {
    books = new ArrayList<>();
    users = new ArrayList<>();
    loadData();   
    }

    public void loadData() throws IOException {
        books = BookFileHandler.readBooks(); // Load books from file
        users = StudentFileHandler.readStudents(this); // Load students from file
    }

    public void setLibrarian(Librarian lib)
    {
        this.Librarian = lib;
    }
    public void addBook(Book book)
    {
        books.add(book);
        book.isAvailable = true; // Set the book as available when added
    }
    public void addUser(User user)
    {
        users.add(user);
    }
    public void removeUser(User user)
    {
        users.remove(user);
    }
    public Book searchBookByTitle(String title)
    {
        for (Book book : books) 
        {
            if (book.getTitle().equalsIgnoreCase(title)) 
            {
                return book;
            }
        }
        return null; // Return null if the book is not found
    }
    public Book searchBookById(String id)
    {
        for (Book book : books) 
        {
            if (book.getBookId().equals(id)) 
            {
                return book;
            }
        }
        return null; // Return null if the book is not found
    }
    public User searchUserById(String userId)
    {
        for (User user : users) 
        {
            if (user.userId.equals(userId)) 
            {
                return user;
            }
        }
        return null; // Return null if the user is not found
    }
    public void displayBooks()
    {
        System.out.println("\n--- All Books in the Library ---");
        for (Book book : books) 
        {
            book.displayBookDetails();
            System.out.println("------------------------------");
        }
    }
    public void displayStudents()
    {
        System.out.println("\n--- All Students in the Library ---");
        for (User user : users) 
        {
            user.displayUserDetails();
            System.out.println("------------------------------");
        }
    }

    public Librarian getLibrarian() {
        return Librarian;
    }

    public void SaveStudentRecords(){
        StudentFileHandler.writeStudents(users); // Save students to file
    }

    public void SaveBookRecords(){
        BookFileHandler.writeAllBooks(books); // Save books to file
    }

}
