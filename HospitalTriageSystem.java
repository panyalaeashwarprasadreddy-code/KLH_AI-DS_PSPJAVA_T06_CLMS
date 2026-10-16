import java.util.*;

public class HospitalTriageSystem {

    // Moving Patient inside the main class completely eliminates the VS Code caching bug!
    static class Patient {
        private String id;
        private String name;
        private int triageLevel;

        public Patient(String id, String name, int triageLevel) {
            this.id = id;
            this.name = name;
            this.triageLevel = triageLevel;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public int getTriageLevel() { return triageLevel; }

        @Override
        public String toString() {
            String priority = "";
            switch (triageLevel) {
                case 1: priority = "CRITICAL (Immediate)"; break;
                case 2: priority = "URGENT (Delayed)"; break;
                default: priority = "ROUTINE (Minor)"; break;
            }
            return "Patient [ID: " + id + ", Name: " + name + ", Priority: " + priority + "]";
        }
    }

    // CO3: Modularization with method decomposition
    public static void validateTriageLevel(int level) throws IllegalArgumentException {
        // CO2: Selection Control Flow
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Must be exactly 1, 2, or 3.");
        }
    }

    public static void main(String[] args) {
        // CO1: Console Input/Output with Scanner
        Scanner scanner = new Scanner(System.in);
        
        // CO6: Collections Framework (ArrayList storage)
        List<Patient> triageQueue = new ArrayList<Patient>();

        System.out.println("=== Hospital Emergency-Room Triage System ===");
        
        // CO2: Iterative repetition handling 3 patients cleanly
        for (int i = 0; i < 3; i++) {
            System.out.println("\nEnter details for Patient " + (i + 1) + ":");
            
            System.out.print("Enter Patient ID (no spaces): ");
            String id = scanner.next();
            
            System.out.print("Enter Patient Name (no spaces): ");
            String name = scanner.next();
            
            int level = 0;
            
            // Validation loop using scanner tracking
            while (true) {
                System.out.print("Enter Triage Level (1-Critical, 2-Urgent, 3-Routine): ");
                
                if (scanner.hasNextInt()) {
                    level = scanner.nextInt();
                    try {
                        validateTriageLevel(level);
                        break; 
                    } catch (IllegalArgumentException e) {
                        System.out.println("-> Error: " + e.getMessage() + " Try again.");
                    }
                } else {
                    System.out.println("-> Error: Not a valid number. Try again.");
                    scanner.next(); // Clear bad input
                }
            }
            
            triageQueue.add(new Patient(id, name, level));
        }

        System.out.println("\n--- Processing Triage Queue (Sorted by Emergency Priority) ---");
        
        // CO5: Library Algorithms - Sorting using Collections.sort
        Collections.sort(triageQueue, new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                return Integer.compare(p1.getTriageLevel(), p2.getTriageLevel());
            }
        });

        // CO6: Final sequential printing of data records
        for (Patient p : triageQueue) {
            System.out.println(p);
        }
        
        scanner.close();
    }
}