package library;
import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String bookId;
    boolean isAvailable;
    private int numCopies;

    // Constructor
    public Book(String title, String author, String bookId, int numCopies) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.numCopies = numCopies;
        this.isAvailable = numCopies > 0; // Set availability based on the number of copies
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    // Method to check if the book is available
    public boolean isBookAvailable() {
        return isAvailable;
    }

    public void displayBookDetails() {
        System.out.println(title + " | ID: " + bookId + " | Author: " + author + " | Copies: " + numCopies);
    }
    
}
