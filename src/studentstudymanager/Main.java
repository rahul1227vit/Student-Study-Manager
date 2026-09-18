```java
package studentstudymanager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create student
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine();

        Student student = new Student(name, studentId);

        // Create StudyManager
        StudyManager manager = new StudyManager(student);

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       STUDENT STUDY MANAGER");
            System.out.println("====================================");
            System.out.println("Student: " + student.getName());
            System.out.println("ID     : " + student.getStudentId());
            System.out.println("------------------------------------");
            System.out.println("1. Subject Management");
            System.out.println("2. Assignment Management");
            System.out.println("3. Study Session Management");
            System.out.println("4. Statistics");
            System.out.println("5. View Student Details");
            System.out.println("0. Save & Exit");
            System.out.println("------------------------------------");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        subjectMenu(sc, manager);
                        break;

                    case 2:
                        assignmentMenu(sc, manager);
                        break;

                    case 3:
                        studySessionMenu(sc, manager);
                        break;

                    case 4:
                        statisticsMenu(manager);
                        break;

                    case 5:
                        System.out.println("\n--- Student Details ---");
                        System.out.println("Name: " + student.getName());
                        System.out.println("ID  : " + student.getStudentId());
                        break;

                    case 0:
                        System.out.println("\nSaving data...");
                        manager.saveAllData();
                        System.out.println("Data saved successfully.");
                        System.out.println("Thank you for using Student Study Manager!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please enter 0-5.");

                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }


    // ==========================================
    // SUBJECT MANAGEMENT
    // ==========================================

    private static void subjectMenu(Scanner sc, StudyManager manager) {

        int choice;

        do {
            System.out.println("\n========== SUBJECT MANAGEMENT ==========");
            System.out.println("1. Add Subject");
            System.out.println("2. View Subjects");
            System.out.println("3. Search Subject");
            System.out.println("4. Delete Subject");
            System.out.println("0. Back");
            System.out.println("----------------------------------------");

            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        manager.addSubjectFromInput(sc);
                        break;

                    case 2:
                        manager.viewSubjects();
                        break;

                    case 3:
                        manager.searchSubjectFromInput(sc);
                        break;

                    case 4:
                        manager.deleteSubjectFromInput(sc);
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Invalid choice!");

                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
            }

        } while (choice != 0);
    }


    // ==========================================
    // ASSIGNMENT MANAGEMENT
    // ==========================================

    private static void assignmentMenu(Scanner sc, StudyManager manager) {

        int choice;

        do {
            System.out.println("\n======== ASSIGNMENT MANAGEMENT ========");
            System.out.println("1. Add Assignment");
            System.out.println("2. View All Assignments");
            System.out.println("3. Search Assignment");
            System.out.println("4. Mark Assignment Complete");
            System.out.println("5. View Pending Assignments");
            System.out.println("6. View Completed Assignments");
            System.out.println("7. Delete Assignment");
            System.out.println("0. Back");
            System.out.println("---------------------------------------");

            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        manager.addAssignmentFromInput(sc);
                        break;

                    case 2:
                        manager.viewAssignments();
                        break;

                    case 3:
                        manager.searchAssignmentFromInput(sc);
                        break;

                    case 4:
                        manager.markAssignmentCompleteFromInput(sc);
                        break;

                    case 5:
                        manager.viewPendingAssignments();
                        break;

                    case 6:
                        manager.viewCompletedAssignments();
                        break;

                    case 7:
                        manager.deleteAssignmentFromInput(sc);
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Invalid choice!");

                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
            }

        } while (choice != 0);
    }


    // ==========================================
    // STUDY SESSION MANAGEMENT
    // ==========================================

    private static void studySessionMenu(
            Scanner sc,
            StudyManager manager) {

        int choice;

        do {
            System.out.println("\n======= STUDY SESSION MANAGEMENT =======");
            System.out.println("1. Add Study Session");
            System.out.println("2. View Study History");
            System.out.println("3. View Total Study Hours");
            System.out.println("4. View Subject-wise Study Hours");
            System.out.println("0. Back");
            System.out.println("----------------------------------------");

            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        manager.addStudySessionFromInput(sc);
                        break;

                    case 2:
                        manager.viewStudySessions();
                        break;

                    case 3:
                        manager.showTotalStudyHours();
                        break;

                    case 4:
                        manager.showSubjectWiseStudyHours();
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Invalid choice!");

                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
            }

        } while (choice != 0);
    }


    // ==========================================
    // STATISTICS
    // ==========================================

    private static void statisticsMenu(StudyManager manager) {

        System.out.println("\n============== STATISTICS ==============");

        manager.showTotalStudyHours();

        System.out.println("-----------------------------------------");

        manager.showSubjectWiseStudyHours();

        System.out.println("-----------------------------------------");

        manager.showAssignmentStatistics();

        System.out.println("=========================================");
    }
}
```

