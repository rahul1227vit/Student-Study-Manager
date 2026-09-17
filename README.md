# Student Study Manager

## Overview
**Student Study Manager** is a Java console-based application built to help college students organize their academic activities. It allows students to manage subjects, keep track of assignment deadlines and completion statuses, log study sessions, and view detailed statistics on their academic progress.

## Features
- **Subject Management**: Add, view, search, and delete subjects (prevents duplicates).
- **Assignment Management**: Add assignments mapped to specific subjects, view pending vs. completed assignments, and mark assignments as done.
- **Study Session Management**: Log the date and hours studied for specific subjects.
- **Statistics**: Generate reports calculating total study hours, subject-wise study hours, and assignment completion ratios.
- **Persistent Storage**: All data is saved locally to `.txt` files in a `data/` directory, ensuring information survives app restarts.
- **Validation**: Gracefully handles incorrect inputs using Java exception handling and custom exceptions.

## Technologies Used
- **Java** (JDK 21)
- **Object-Oriented Programming (OOP)**
- **Java Collections** (`ArrayList`, `HashMap`, `List`, `Map`)
- **Exception Handling** (`try-catch`, Custom Exceptions)
- **File I/O** (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`)
- **Java Time API** (`LocalDate`, `DateTimeFormatter`)

## Project Structure
```text
StudentStudyManager/
│
├── src/
│   └── studentstudymanager/
│       ├── Main.java                 # Entry point and console UI
│       ├── Student.java              # Model class
│       ├── Subject.java              # Model class
│       ├── Assignment.java           # Model class
│       ├── StudySession.java         # Model class
│       ├── StudyManager.java         # Core business logic / Lists management
│       ├── FileManager.java          # File I/O operations
│       │
│       └── exceptions/
│           ├── InvalidStudyHoursException.java
│           ├── SubjectNotFoundException.java
│           └── AssignmentNotFoundException.java
│
├── data/                             # Stores local .txt persistence files
├── tests/
│   └── ApplicationTest.java          # Programmatic tests
├── README.md
└── statement.md                      # Problem statement and scope
```

## OOP Concepts Demonstrated
- **Classes & Objects**: Extensively used to model real-world entities like `Student`, `Subject`, `Assignment`, and `StudySession`.
- **Encapsulation**: All class fields are strictly `private` and accessed via `public` getters and setters to protect internal state.
- **Constructors**: Used in every model class to properly initialize object state.
- **Composition**: The `StudyManager` class demonstrates composition by maintaining lists of `Subject`, `Assignment`, and `StudySession` objects.

## Exception Handling
The application uses robust `try-catch` blocks to capture:
- `NumberFormatException`: Prevents crashing when text is entered instead of a number.
- `DateTimeParseException`: Prevents crashing on invalid date inputs.

Custom Exceptions:
- `SubjectNotFoundException`: Thrown when attempting to assign a task to or delete a subject that doesn't exist.
- `AssignmentNotFoundException`: Thrown when searching for or completing a non-existent assignment.
- `InvalidStudyHoursException`: Thrown to prevent logging negative or zero study hours.

## File I/O
The `FileManager` class handles text file persistence.
- `save...()` methods use `BufferedWriter` and `FileWriter` to serialize objects into a pipe-delimited (`|`) text format (e.g., `S001|CS201|17-09-2026|2.5`).
- `load...()` methods use `BufferedReader` and `FileReader` to read these text files line-by-line upon application startup and instantiate the objects back into memory.

## How to Compile and Run

1. Open your terminal/command prompt.
2. Navigate to the project root directory (`StudentStudyManager/`).
3. **Compile the code**:
   ```bash
   javac -d bin src/studentstudymanager/*.java src/studentstudymanager/exceptions/*.java
   ```
4. **Run the application**:
   ```bash
   java -cp bin studentstudymanager.Main
   ```

## Testing
To run the automated tests validating the core logic and exception handling:
1. Compile the test file:
   ```bash
   javac -d bin -cp bin tests/ApplicationTest.java
   ```
2. Run the test file:
   ```bash
   java -cp bin ApplicationTest
   ```
