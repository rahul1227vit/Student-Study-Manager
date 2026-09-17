# Problem Statement

College and university students often struggle to keep track of their subjects, assignments, and the hours they dedicate to studying. Without a centralized system, it becomes difficult to monitor academic progress, meet deadlines, and evaluate which subjects require more attention. Therefore, students need a simple, structured way to manage their academic activities and time locally.

## Scope

The **Student Study Manager** is a locally-hosted Java console application that enables students to organize their academic information. It leverages Java Object-Oriented Programming (OOP) principles and text-file storage to provide persistence. It is designed specifically for individual academic management without relying on external databases or cloud services.

## Target Users

The primary target users are **college and university students** who want an offline, easy-to-use terminal interface to log their study sessions, track assignments, and view their academic statistics.

## High-Level Features

- **Subject Management**: Add, view, search, and delete academic subjects.
- **Assignment Management**: Track assignments, associate them with subjects, and mark them as completed.
- **Study Session Management**: Log study hours by subject and date.
- **Statistics**: Automatically calculate total subjects, assignment completion rates, total study hours, and subject-wise study hours.
- **File Persistence**: Save and load all student data to text files so information is retained across sessions.
- **Validation and Exception Handling**: Prevent invalid input (e.g., negative hours, missing subjects, incorrect dates) through robust try-catch blocks and custom exceptions.
