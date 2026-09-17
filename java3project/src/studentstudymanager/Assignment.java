package studentstudymanager;

import java.time.LocalDate;

public class Assignment {
    private String assignmentId;
    private String title;
    private Subject subject;
    private LocalDate deadline;
    private boolean completed;

    public Assignment(String assignmentId, String title, Subject subject, LocalDate deadline, boolean completed) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.subject = subject;
        this.deadline = deadline;
        this.completed = completed;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "Completed" : "Pending";
        String subjectName = (subject != null) ? subject.getSubjectName() : "Unknown Subject";
        return "Assignment ID: " + assignmentId + "\n" +
               "Title: " + title + "\n" +
               "Subject: " + subjectName + "\n" +
               "Deadline: " + deadline.toString() + "\n" +
               "Status: " + status;
    }
}
