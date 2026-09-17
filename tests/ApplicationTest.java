import studentstudymanager.*;
import studentstudymanager.exceptions.*;

import java.time.LocalDate;

public class ApplicationTest {
    
    public static void main(String[] args) {
        System.out.println("--- Starting Application Tests ---");
        
        StudyManager manager = new StudyManager();
        int passed = 0;
        int failed = 0;
        
        // Test 1: Add valid subject
        try {
            manager.addSubject(new Subject("Data Structures", "CS201", 4));
            System.out.println("[PASS] Added valid subject.");
            passed++;
        } catch (Exception e) {
            System.out.println("[FAIL] Adding valid subject failed: " + e.getMessage());
            failed++;
        }
        
        // Test 2: Add duplicate subject
        try {
            manager.addSubject(new Subject("Data Structures II", "CS201", 3));
            System.out.println("[FAIL] Allowed duplicate subject.");
            failed++;
        } catch (Exception e) {
            System.out.println("[PASS] Prevented duplicate subject.");
            passed++;
        }
        
        // Test 3: Search for non-existent subject
        Subject notFound = manager.searchSubject("INVALID");
        if (notFound == null) {
            System.out.println("[PASS] Handled non-existent subject search correctly.");
            passed++;
        } else {
            System.out.println("[FAIL] Search returned a subject for an invalid code.");
            failed++;
        }
        
        // Test 4: Add Assignment with valid subject
        try {
            Subject s = manager.searchSubject("CS201");
            manager.addAssignment(new Assignment("A1", "Test Assignment", s, LocalDate.now(), false));
            System.out.println("[PASS] Added valid assignment.");
            passed++;
        } catch (Exception e) {
            System.out.println("[FAIL] Adding valid assignment failed: " + e.getMessage());
            failed++;
        }
        
        // Test 5: Mark Assignment complete
        if (manager.markAssignmentComplete("A1")) {
            System.out.println("[PASS] Marked assignment complete.");
            passed++;
        } else {
            System.out.println("[FAIL] Failed to mark assignment complete.");
            failed++;
        }
        
        // Test 6: Add study session with valid hours
        try {
            Subject s = manager.searchSubject("CS201");
            manager.addStudySession(new StudySession("S1", s, LocalDate.now(), 2.5));
            System.out.println("[PASS] Added study session with valid hours.");
            passed++;
        } catch (Exception e) {
            System.out.println("[FAIL] Failed to add study session.");
            failed++;
        }

        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("--------------------");
    }
}
