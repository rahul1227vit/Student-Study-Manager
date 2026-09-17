package studentstudymanager;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileManager {
    private static final String DATA_DIR = "data/";
    private static final String STUDENT_FILE = DATA_DIR + "student.txt";
    private static final String SUBJECTS_FILE = DATA_DIR + "subjects.txt";
    private static final String ASSIGNMENTS_FILE = DATA_DIR + "assignments.txt";
    private static final String SESSIONS_FILE = DATA_DIR + "study_sessions.txt";
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // --- Student ---
    public static Student loadStudent() {
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return null;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    return new Student(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
        return null;
    }

    public static void saveStudent(Student student) {
        if (student == null) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            writer.write(student.getName() + "|" + student.getRollNumber());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // --- Subjects ---
    public static ArrayList<Subject> loadSubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();
        File file = new File(SUBJECTS_FILE);
        if (!file.exists()) return subjects;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    subjects.add(new Subject(parts[1], parts[0], Integer.parseInt(parts[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading subjects: " + e.getMessage());
        }
        return subjects;
    }

    public static void saveSubjects(ArrayList<Subject> subjects) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SUBJECTS_FILE))) {
            for (Subject s : subjects) {
                writer.write(s.getSubjectCode() + "|" + s.getSubjectName() + "|" + s.getCredits());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving subjects: " + e.getMessage());
        }
    }

    // --- Assignments ---
    public static ArrayList<Assignment> loadAssignments(StudyManager manager) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        File file = new File(ASSIGNMENTS_FILE);
        if (!file.exists()) return assignments;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    Subject subject = manager.searchSubject(parts[2]);
                    LocalDate deadline = LocalDate.parse(parts[3], DATE_FORMATTER);
                    boolean completed = Boolean.parseBoolean(parts[4]);
                    assignments.add(new Assignment(parts[0], parts[1], subject, deadline, completed));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading assignments: " + e.getMessage());
        }
        return assignments;
    }

    public static void saveAssignments(ArrayList<Assignment> assignments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSIGNMENTS_FILE))) {
            for (Assignment a : assignments) {
                String subjectCode = (a.getSubject() != null) ? a.getSubject().getSubjectCode() : "UNKNOWN";
                String date = a.getDeadline().format(DATE_FORMATTER);
                writer.write(a.getAssignmentId() + "|" + a.getTitle() + "|" + subjectCode + "|" + date + "|" + a.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving assignments: " + e.getMessage());
        }
    }

    // --- Study Sessions ---
    public static ArrayList<StudySession> loadStudySessions(StudyManager manager) {
        ArrayList<StudySession> sessions = new ArrayList<>();
        File file = new File(SESSIONS_FILE);
        if (!file.exists()) return sessions;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Subject subject = manager.searchSubject(parts[1]);
                    LocalDate date = LocalDate.parse(parts[2], DATE_FORMATTER);
                    double hours = Double.parseDouble(parts[3]);
                    sessions.add(new StudySession(parts[0], subject, date, hours));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading study sessions: " + e.getMessage());
        }
        return sessions;
    }

    public static void saveStudySessions(ArrayList<StudySession> sessions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SESSIONS_FILE))) {
            for (StudySession s : sessions) {
                String subjectCode = (s.getSubject() != null) ? s.getSubject().getSubjectCode() : "UNKNOWN";
                String date = s.getDate().format(DATE_FORMATTER);
                writer.write(s.getSessionId() + "|" + subjectCode + "|" + date + "|" + s.getHours());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving study sessions: " + e.getMessage());
        }
    }
}
