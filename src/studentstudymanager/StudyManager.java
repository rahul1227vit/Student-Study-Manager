package studentstudymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyManager {
    private Student student;
    private ArrayList<Subject> subjects;
    private ArrayList<Assignment> assignments;
    private ArrayList<StudySession> studySessions;

    public StudyManager() {
        this.subjects = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.studySessions = new ArrayList<>();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    // --- Subject Management ---

    public void addSubject(Subject subject) throws Exception {
        if (searchSubject(subject.getSubjectCode()) != null) {
            throw new Exception("Subject with code " + subject.getSubjectCode() + " already exists.");
        }
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Subject searchSubject(String subjectCode) {
        for (Subject s : subjects) {
            if (s.getSubjectCode().equalsIgnoreCase(subjectCode)) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteSubject(String subjectCode) {
        Subject subject = searchSubject(subjectCode);
        if (subject != null) {
            subjects.remove(subject);
            return true;
        }
        return false;
    }

    // --- Assignment Management ---

    public void addAssignment(Assignment assignment) throws Exception {
        if (searchAssignment(assignment.getAssignmentId()) != null) {
            throw new Exception("Assignment with ID " + assignment.getAssignmentId() + " already exists.");
        }
        assignments.add(assignment);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public Assignment searchAssignment(String assignmentId) {
        for (Assignment a : assignments) {
            if (a.getAssignmentId().equalsIgnoreCase(assignmentId)) {
                return a;
            }
        }
        return null;
    }

    public boolean markAssignmentComplete(String assignmentId) {
        Assignment assignment = searchAssignment(assignmentId);
        if (assignment != null) {
            assignment.setCompleted(true);
            return true;
        }
        return false;
    }

    public boolean deleteAssignment(String assignmentId) {
        Assignment assignment = searchAssignment(assignmentId);
        if (assignment != null) {
            assignments.remove(assignment);
            return true;
        }
        return false;
    }

    public List<Assignment> getPendingAssignments() {
        List<Assignment> pending = new ArrayList<>();
        for (Assignment a : assignments) {
            if (!a.isCompleted()) {
                pending.add(a);
            }
        }
        return pending;
    }

    public List<Assignment> getCompletedAssignments() {
        List<Assignment> completed = new ArrayList<>();
        for (Assignment a : assignments) {
            if (a.isCompleted()) {
                completed.add(a);
            }
        }
        return completed;
    }

    // --- Study Session Management ---

    public void addStudySession(StudySession session) {
        studySessions.add(session);
    }

    public List<StudySession> getStudySessions() {
        return studySessions;
    }

    // --- Statistics ---

    public double calculateTotalStudyHours() {
        double total = 0;
        for (StudySession s : studySessions) {
            total += s.getHours();
        }
        return total;
    }

    public Map<String, Double> calculateSubjectWiseHours() {
        Map<String, Double> subjectHours = new HashMap<>();
        for (StudySession s : studySessions) {
            String subjectName = (s.getSubject() != null) ? s.getSubject().getSubjectName() : "Unknown";
            subjectHours.put(subjectName, subjectHours.getOrDefault(subjectName, 0.0) + s.getHours());
        }
        return subjectHours;
    }

    public void showStatistics() {
        System.out.println("========================================");
        System.out.println("           STUDY STATISTICS");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Total Subjects       : " + subjects.size());
        System.out.println("Total Assignments    : " + assignments.size());
        System.out.println("Completed            : " + getCompletedAssignments().size());
        System.out.println("Pending              : " + getPendingAssignments().size());
        System.out.println("Total Study Hours    : " + calculateTotalStudyHours());
        System.out.println();
        System.out.println("Subject-wise Study Hours:");
        System.out.println();
        Map<String, Double> subjectHours = calculateSubjectWiseHours();
        for (Map.Entry<String, Double> entry : subjectHours.entrySet()) {
            System.out.printf("%-20s : %.1f hours\n", entry.getKey(), entry.getValue());
        }
        System.out.println();
        System.out.println("========================================");
    }

    // Setters for bulk loading data from FileManager
    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setStudySessions(ArrayList<StudySession> studySessions) {
        this.studySessions = studySessions;
    }
}
