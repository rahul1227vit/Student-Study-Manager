import java.io.*;
import java.util.*;

public class Main {

    // ---------- Subject Class ----------
    static class Subject {
        private String name;
        private String code;
        private int credits;

        Subject(String name, String code, int credits) {
            this.name = name;
            this.code = code;
            this.credits = credits;
        }

        public String getCode() {
            return code;
        }

        public String toString() {
            return name + " (" + code + ") - " + credits + " credits";
        }
    }

    // ---------- Assignment Class ----------
    static class Assignment {
        private String title;
        private String subject;
        private boolean completed;

        Assignment(String title, String subject) {
            this.title = title;
            this.subject = subject;
            this.completed = false;
        }

        void complete() {
            completed = true;
        }

        public String toString() {
            return title + " | " + subject +
                    " | " + (completed ? "Completed" : "Pending");
        }
    }

    // ---------- Study Session Class ----------
    static class StudySession {
        private String subject;
        private double hours;

        StudySession(String subject, double hours) {
            this.subject = subject;
            this.hours = hours;
        }

        public String toString() {
            return subject + " - " + hours + " hours";
        }
    }

    // ---------- Custom Exception ----------
    static class InvalidStudyHoursException extends Exception {
        InvalidStudyHoursException(String message) {
            super(message);
        }
    }

    // ---------- Manager Class ----------
    static class StudyManager {
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<Assignment> assignments = new ArrayList<>();
        ArrayList<StudySession> sessions = new ArrayList<>();

        void addSubject(String name, String code, int credits) {
            for (Subject s : subjects) {
                if (s.getCode().equalsIgnoreCase(code)) {
                    System.out.println("Subject code already exists!");
                    return;
                }
            }

            subjects.add(new Subject(name, code, credits));
            System.out.println("Subject added successfully!");
        }

        void viewSubjects() {
            System.out.println("\n--- Subjects ---");

            if (subjects.isEmpty()) {
                System.out.println("No subjects added.");
                return;
            }

            for (Subject s : subjects)
                System.out.println(s);
        }

        void addAssignment(String title, String subject) {
            assignments.add(new Assignment(title, subject));
            System.out.println("Assignment added!");
        }

        void viewAssignments() {
            System.out.println("\n--- Assignments ---");

            if (assignments.isEmpty()) {
                System.out.println("No assignments found.");
                return;
            }

            for (int i = 0; i < assignments.size(); i++)
                System.out.println((i + 1) + ". " + assignments.get(i));
        }

        void completeAssignment(int index) {
            if (index < 0 || index >= assignments.size()) {
                System.out.println("Invalid assignment number.");
                return;
            }

            assignments.get(index).complete();
            System.out.println("Assignment marked as completed!");
        }

        void addStudySession(String subject, double hours)
                throws InvalidStudyHoursException {

            if (hours <= 0)
                throw new InvalidStudyHoursException(
                        "Study hours must be greater than 0.");

            sessions.add(new StudySession(subject, hours));
            System.out.println("Study session added!");
        }

        void viewSessions() {
            System.out.println("\n--- Study History ---");

            if (sessions.isEmpty()) {
                System.out.println("No study sessions yet.");
                return;
            }

            double total = 0;

            for (StudySession s : sessions) {
                System.out.println(s);
            }

            for (StudySession s : sessions)
                total += s.hours;

            System.out.println("Total Study Hours: " + total);
        }

        void statistics() {
            int completed = 0;

            for (Assignment a : assignments) {
                if (a.completed)
                    completed++;
            }

            double totalHours = 0;

            for (StudySession s : sessions)
                totalHours += s.hours;

            System.out.println("\n--- Study Statistics ---");
            System.out.println("Total Subjects    : " + subjects.size());
            System.out.println("Total Assignments : " + assignments.size());
            System.out.println("Completed         : " + completed);
            System.out.println("Pending           : " +
                    (assignments.size() - completed));
            System.out.println("Study Hours       : " + totalHours);
        }

        // ---------- File I/O ----------
        void saveData() {
            try {
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter("study_data.txt"));

                writer.write("SUBJECTS\n");

                for (Subject s : subjects)
                    writer.write(s + "\n");

                writer.write("\nASSIGNMENTS\n");

                for (Assignment a : assignments)
                    writer.write(a + "\n");

                writer.write("\nSTUDY SESSIONS\n");

                for (StudySession s : sessions)
                    writer.write(s + "\n");

                writer.close();

                System.out.println("Data saved successfully!");

            } catch (IOException e) {
                System.out.println("Error saving data.");
            }
        }
    }

    // ---------- Main Program ----------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudyManager manager = new StudyManager();

        System.out.println("================================");
        System.out.println("      STUDENT STUDY MANAGER");
        System.out.println("================================");

        while (true) {

            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Add Subject");
            System.out.println("2. View Subjects");
            System.out.println("3. Add Assignment");
            System.out.println("4. View Assignments");
            System.out.println("5. Complete Assignment");
            System.out.println("6. Add Study Session");
            System.out.println("7. View Study History");
            System.out.println("8. View Statistics");
            System.out.println("9. Save & Exit");

            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Subject name: ");
                        String name = sc.nextLine();

                        System.out.print("Subject code: ");
                        String code = sc.nextLine();

                        System.out.print("Credits: ");
                        int credits = Integer.parseInt(sc.nextLine());

                        manager.addSubject(name, code, credits);
                        break;

                    case 2:
                        manager.viewSubjects();
                        break;

                    case 3:
                        System.out.print("Assignment title: ");
                        String title = sc.nextLine();

                        System.out.print("Subject: ");
                        String subject = sc.nextLine();

                        manager.addAssignment(title, subject);
                        break;

                    case 4:
                        manager.viewAssignments();
                        break;

                    case 5:
                        manager.viewAssignments();

                        if (!manager.assignments.isEmpty()) {
                            System.out.print("Enter assignment number: ");
                            int n = Integer.parseInt(sc.nextLine());

                            manager.completeAssignment(n - 1);
                        }
                        break;

                    case 6:
                        System.out.print("Subject: ");
                        String sub = sc.nextLine();

                        System.out.print("Study hours: ");
                        double hours = Double.parseDouble(sc.nextLine());

                        manager.addStudySession(sub, hours);
                        break;

                    case 7:
                        manager.viewSessions();
                        break;

                    case 8:
                        manager.statistics();
                        break;

                    case 9:
                        manager.saveData();
                        System.out.println("Thanks for using Study Manager!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Please choose between 1 and 9.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");

            } catch (InvalidStudyHoursException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
