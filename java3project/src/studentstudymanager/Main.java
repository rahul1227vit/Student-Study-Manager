package studentstudymanager;

import studentstudymanager.exceptions.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private static StudyManager manager = new StudyManager();
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        System.out.println("Loading saved data...");
        loadData();
        System.out.println("Data loaded successfully.");
        
        if (manager.getStudent() == null) {
            setupStudent();
        }

        boolean running = true;
        while (running) {
            System.out.println("\n========================================");
            System.out.println("        STUDENT STUDY MANAGER");
            System.out.println("========================================");
            System.out.println("Student: " + manager.getStudent().getName());
            System.out.println("1. Subject Management");
            System.out.println("2. Assignment Management");
            System.out.println("3. Study Session Management");
            System.out.println("4. View Study Statistics");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        subjectMenu();
                        break;
                    case 2:
                        assignmentMenu();
                        break;
                    case 3:
                        studySessionMenu();
                        break;
                    case 4:
                        manager.showStatistics();
                        break;
                    case 5:
                        saveData();
                        System.out.println("Data saved successfully.");
                        break;
                    case 6:
                        saveData();
                        System.out.println("Saving data...");
                        System.out.println("Data saved successfully.");
                        System.out.println("Exiting Application. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("ERROR: Invalid menu choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void setupStudent() {
        System.out.println("\n--- Setup Student Profile ---");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Roll Number: ");
        String roll = scanner.nextLine().trim();
        manager.setStudent(new Student(name, roll));
    }

    // --- Subject Menu ---
    private static void subjectMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n========== SUBJECT MANAGEMENT ==========");
            System.out.println("1. Add Subject");
            System.out.println("2. View Subjects");
            System.out.println("3. Search Subject");
            System.out.println("4. Delete Subject");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Subject Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Subject Code: ");
                        String code = scanner.nextLine().trim();
                        System.out.print("Enter Credits: ");
                        int credits = Integer.parseInt(scanner.nextLine().trim());
                        if (name.isEmpty() || code.isEmpty() || credits <= 0) {
                            System.out.println("ERROR: Invalid input for subject details.");
                        } else {
                            manager.addSubject(new Subject(name, code, credits));
                            System.out.println("Subject added successfully.");
                        }
                        break;
                    case 2:
                        for (Subject s : manager.getSubjects()) {
                            System.out.println(s);
                        }
                        break;
                    case 3:
                        System.out.print("Enter Subject Code to search: ");
                        String searchCode = scanner.nextLine().trim();
                        Subject found = manager.searchSubject(searchCode);
                        if (found != null) {
                            System.out.println("Found: " + found);
                        } else {
                            throw new SubjectNotFoundException("Subject with code " + searchCode + " not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Subject Code to delete: ");
                        String delCode = scanner.nextLine().trim();
                        if (manager.deleteSubject(delCode)) {
                            System.out.println("Subject deleted successfully.");
                        } else {
                            throw new SubjectNotFoundException("Subject with code " + delCode + " not found.");
                        }
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("ERROR: Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    // --- Assignment Menu ---
    private static void assignmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n========== ASSIGNMENT MANAGEMENT ==========");
            System.out.println("1. Add Assignment");
            System.out.println("2. View All Assignments");
            System.out.println("3. Search Assignment");
            System.out.println("4. Mark Assignment as Completed");
            System.out.println("5. View Pending Assignments");
            System.out.println("6. View Completed Assignments");
            System.out.println("7. Delete Assignment");
            System.out.println("8. Back");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Assignment ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter Subject Code: ");
                        String subCode = scanner.nextLine().trim();
                        Subject sub = manager.searchSubject(subCode);
                        if (sub == null) {
                            throw new SubjectNotFoundException("Subject " + subCode + " not found. Cannot add assignment.");
                        }
                        System.out.print("Enter Deadline (dd-MM-yyyy): ");
                        String dateStr = scanner.nextLine().trim();
                        LocalDate deadline = LocalDate.parse(dateStr, DATE_FORMATTER);
                        
                        manager.addAssignment(new Assignment(id, title, sub, deadline, false));
                        System.out.println("Assignment added successfully.");
                        break;
                    case 2:
                        for (Assignment a : manager.getAssignments()) {
                            System.out.println(a);
                            System.out.println("-");
                        }
                        break;
                    case 3:
                        System.out.print("Enter Assignment ID: ");
                        String searchId = scanner.nextLine().trim();
                        Assignment found = manager.searchAssignment(searchId);
                        if (found != null) {
                            System.out.println(found);
                        } else {
                            throw new AssignmentNotFoundException("Assignment ID " + searchId + " not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Assignment ID to mark completed: ");
                        String compId = scanner.nextLine().trim();
                        if (manager.markAssignmentComplete(compId)) {
                            System.out.println("Assignment marked as completed.");
                        } else {
                            throw new AssignmentNotFoundException("Assignment ID " + compId + " not found.");
                        }
                        break;
                    case 5:
                        for (Assignment a : manager.getPendingAssignments()) {
                            System.out.println(a);
                            System.out.println("-");
                        }
                        break;
                    case 6:
                        for (Assignment a : manager.getCompletedAssignments()) {
                            System.out.println(a);
                            System.out.println("-");
                        }
                        break;
                    case 7:
                        System.out.print("Enter Assignment ID to delete: ");
                        String delId = scanner.nextLine().trim();
                        if (manager.deleteAssignment(delId)) {
                            System.out.println("Assignment deleted successfully.");
                        } else {
                            throw new AssignmentNotFoundException("Assignment ID " + delId + " not found.");
                        }
                        break;
                    case 8:
                        back = true;
                        break;
                    default:
                        System.out.println("ERROR: Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number.");
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Invalid date format. Use dd-MM-yyyy.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    // --- Study Session Menu ---
    private static void studySessionMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n========== STUDY SESSION MANAGEMENT ==========");
            System.out.println("1. Add Study Session");
            System.out.println("2. View Study History");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Session ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter Subject Code: ");
                        String subCode = scanner.nextLine().trim();
                        Subject sub = manager.searchSubject(subCode);
                        if (sub == null) {
                            throw new SubjectNotFoundException("Subject " + subCode + " not found.");
                        }
                        System.out.print("Enter Date (dd-MM-yyyy): ");
                        String dateStr = scanner.nextLine().trim();
                        LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
                        System.out.print("Enter Hours Studied: ");
                        double hours = Double.parseDouble(scanner.nextLine().trim());
                        
                        if (hours <= 0) {
                            throw new InvalidStudyHoursException("Study hours must be greater than zero.");
                        }
                        
                        manager.addStudySession(new StudySession(id, sub, date, hours));
                        System.out.println("Study session added successfully.");
                        break;
                    case 2:
                        for (StudySession s : manager.getStudySessions()) {
                            System.out.println(s);
                        }
                        break;
                    case 3:
                        back = true;
                        break;
                    default:
                        System.out.println("ERROR: Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number.");
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Invalid date format. Use dd-MM-yyyy.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    // --- File IO ---
    private static void loadData() {
        manager.setStudent(FileManager.loadStudent());
        manager.setSubjects(FileManager.loadSubjects());
        manager.setAssignments(FileManager.loadAssignments(manager));
        manager.setStudySessions(FileManager.loadStudySessions(manager));
    }

    private static void saveData() {
        FileManager.saveStudent(manager.getStudent());
        FileManager.saveSubjects((java.util.ArrayList<Subject>) manager.getSubjects());
        FileManager.saveAssignments((java.util.ArrayList<Assignment>) manager.getAssignments());
        FileManager.saveStudySessions((java.util.ArrayList<StudySession>) manager.getStudySessions());
    }
}
