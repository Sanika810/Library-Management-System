# 📚 Library Management System – Assignment 6

This Java-based project implements a Library Management System that allows librarians to manage book records and students to borrow/return books. It uses object-oriented principles and file handling to store data persistently.

## Project Structure

```
Library management system

├── Main.java                  # Entry point of the program
├── books.txt                 # Stores list of available books
├── students.txt              # Stores student data
│
└── library/                  # Contains supporting classes
    ├── Book.java             # Book object structure
    ├── BookFileHandler.java # Book file read/write handler
    ├── bookNotFound.java     # Custom exception for book not found
    ├── Librarian.java        # Handles librarian actions
    ├── Student.java          # Handles student actions
    ├── LibrarySystem.java    # Central system logic and state
    ├── invalidUser.java      # Custom exception for invalid users
```

##  How to Compile and Run

### Prerequisites

* Java 8 or above

### Steps

1. **Compile the code:**

   ```bash
   javac Main.java library/*.java
   ```

2. **Run the program:**

   ```bash
   java Main
   ```

> Ensure `books.txt` and `students.txt` are in the same directory as `Main.java`.

##  Functionalities

###  Librarian Menu

After logging in with valid credentials (`ID: 123`, `Name: librarian`), the librarian can:

* Add new books
* Delete existing books
* View all books
* Search books by title or author

###  Student Menu

Upon entering a valid 4-digit ID, a student can:

* Search for books
* Borrow available books
* Return borrowed books
* View their list of borrowed books

> Student input is validated. Invalid IDs (non-4-digit) will raise an error.

##  File Descriptions

| File/Folder                    | Purpose                                  |
| ------------------------------ | ---------------------------------------- |
| `Main.java`                    | Handles user role selection and login    |
| `library/Book.java`            | Book class definition                    |
| `library/Librarian.java`       | Librarian actions implementation         |
| `library/Student.java`         | Student actions implementation           |
| `library/BookFileHandler.java` | File operations on book data             |
| `library/bookNotFound.java`    | Custom exception for missing books       |
| `library/invalidUser.java`     | Custom exception for invalid student IDs |
| `books.txt`                    | Text file containing book data           |
| `students.txt`                 | Text file containing student data        |

##  Requirements

* Java 8 or higher
* Command-line environment (or any Java IDE)

---

This README file is intended for upload/submission with the full project. Feel free to expand it with output samples or screenshots if needed.
