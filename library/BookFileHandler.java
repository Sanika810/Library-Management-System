package library;

import java.io.*;
import java.util.*;

public class BookFileHandler {
    private static final String BOOK_FILE = "books.txt";

    // Write all books to file
    public static void writeAllBooks(ArrayList<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_FILE))) {
            for (Book book : books) {
                writer.write(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + ","
                        + book.isBookAvailable() + "," + book.getNumCopies());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing book data: " + e.getMessage());
        }
    }
    
    // Read all books into an ArrayList
    public static ArrayList<Book> readBooks() throws IOException {
        ArrayList<Book> books = new ArrayList<>(); // Change to ArrayList<Book>
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOK_FILE))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String bookId = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    int numCopies = Integer.parseInt(parts[4]);
                    
                    // Create a new Book object and add it to the ArrayList
                    books.add(new Book(title, author, bookId,numCopies));
                }
            }
        }
        return books; // Return ArrayList<Book>
    }
}
