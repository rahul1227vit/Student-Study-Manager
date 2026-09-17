# 📚 Student Study Manager

> **A simple, console-based Java application designed to help students manage subjects, assignments, and study sessions efficiently.**

---

## 🚀 Overview

**Student Study Manager** is a Java-based console application developed to provide students with a simple way to organize their academic activities.

The application allows students to:

- 📖 Manage their subjects
- 📝 Track assignments and deadlines
- ⏱️ Record study sessions
- 📊 View study statistics
- 💾 Store and retrieve data using files
- ⚠️ Handle invalid inputs and application errors safely

The project is designed using core **Object-Oriented Programming (OOP)** concepts along with **Exception Handling**, **Collections**, and **File I/O**.

The main goal is to create a practical academic management system while demonstrating fundamental Java programming concepts in a real-world application.

---

## 🎯 Problem Statement

Students often have to manage multiple subjects, assignments, deadlines, and study schedules simultaneously.

Without a proper system, it can become difficult to:

- Keep track of subjects
- Remember assignment deadlines
- Monitor completed and pending assignments
- Record study hours
- Understand how much time is being spent on each subject

The **Student Study Manager** provides a centralized console-based solution for managing these academic activities.

---

## ✨ Features

### 📖 1. Subject Management

Manage all academic subjects from one place.

**Features:**
- Add a new subject
- View all subjects
- Search for a subject
- Delete a subject
- Prevent duplicate subject codes
- Store subject credits

**Subject Information:**
- Subject Name
- Subject Code
- Credits

---

### 📝 2. Assignment Management

Keep track of assignments and their completion status.

**Features:**
- Add assignments
- View all assignments
- Search assignments
- Mark assignments as completed
- View pending assignments
- View completed assignments
- Delete assignments
- Store assignment deadlines

**Assignment Information:**
- Assignment ID
- Assignment Title
- Subject
- Deadline
- Completion Status

---

### ⏱️ 3. Study Session Management

Record and monitor study sessions.

**Features:**
- Add study sessions
- View study history
- Record study hours
- Validate study hours
- View total study hours
- Calculate subject-wise study hours

**Study Session Information:**
- Session ID
- Subject
- Date
- Study Hours

---

### 📊 4. Statistics

The application provides useful academic statistics such as:

- Total study hours
- Subject-wise study hours
- Total assignments
- Completed assignments
- Pending assignments
- Assignment completion statistics

Statistics are calculated dynamically from the stored data.

---

### 💾 5. File-Based Data Storage

The application uses **File I/O** to store data locally.

Data can be maintained using files such as:

```text
data/
├── student.txt
├── subjects.txt
├── assignments.txt
└── study_sessions.txt
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
