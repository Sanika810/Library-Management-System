package library;

import java.io.*;
import java.util.*;

public class StudentFileHandler {
    private static final String STUDENT_FILE = "students.txt";

    // Read students and their borrowed books
    public static ArrayList<User> readStudents(LibrarySystem librarySystem) {
        ArrayList<User> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 2) {
                    String userId = parts[0].trim();
                    String name = parts[1].trim();
                    Student student = new Student(name, userId);

                    // Process borrowed books
                    if (parts.length == 3 && !parts[2].trim().isEmpty()) {
                        String[] bookIds = parts[2].split("\\|");
                        for (String bookId : bookIds) {
                            Book borrowed = librarySystem.searchBookById(bookId.trim());
                            if (borrowed != null) {
                                Book borrowedCopy = new Book(borrowed.getTitle(), borrowed.getAuthor(), borrowed.getBookId(), 1);
                                student.borrowedBooks.add(borrowedCopy);
                            }
                        }
                    }

                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student data: " + e.getMessage());
        }

        return students;
    }

    // Write students and their borrowed books
    public static void writeStudents(ArrayList<User> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (User student : students) {
                Student st = (Student) student; // Cast to Student to access borrowedBooks
                StringBuilder line = new StringBuilder();
                line.append(st.getUserId()).append(",").append(st.getName());
    
                if (!st.borrowedBooks.isEmpty()) {
                    line.append(",");
                    for (int i = 0; i < st.borrowedBooks.size(); i++) {
                        line.append(st.borrowedBooks.get(i).getBookId());
                        if (i < st.borrowedBooks.size() - 1) {
                            line.append("|");
                        }
                    }
                }
    
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing student data: " + e.getMessage());
        }
    }
    
}
