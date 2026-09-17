package studentstudymanager;

import java.time.LocalDate;

public class StudySession {
    private String sessionId;
    private Subject subject;
    private LocalDate date;
    private double hours;

    public StudySession(String sessionId, Subject subject, LocalDate date, double hours) {
        this.sessionId = sessionId;
        this.subject = subject;
        this.date = date;
        this.hours = hours;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        String subjectName = (subject != null) ? subject.getSubjectName() : "Unknown Subject";
        return "Session ID: " + sessionId + " | Subject: " + subjectName + " | Date: " + date.toString() + " | Hours: " + hours;
    }
}
