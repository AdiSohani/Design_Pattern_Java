// =========================================================
// VISITOR PATTERN — Hospital Inspection
// A doctor (visitor) visits different elements without
// those elements needing to change their classes.
// =========================================================
import java.util.*;

public class Visitor {

    // Visitor interface
    interface HospitalVisitor {
        void visitPatient(Patient patient);
        void visitEquipment(Equipment equipment);
        void visitWard(Ward ward);
    }

    // Element interface
    interface HospitalElement {
        void accept(HospitalVisitor visitor);
    }

    // Concrete elements
    static class Patient implements HospitalElement {
        final String name;
        final int    age;
        final String diagnosis;

        Patient(String name, int age, String diagnosis) {
            this.name = name; this.age = age; this.diagnosis = diagnosis;
        }
        public void accept(HospitalVisitor v) { v.visitPatient(this); }
    }

    static class Equipment implements HospitalElement {
        final String name;
        final int    yearsOld;
        final boolean needsService;

        Equipment(String name, int yearsOld, boolean needsService) {
            this.name = name; this.yearsOld = yearsOld; this.needsService = needsService;
        }
        public void accept(HospitalVisitor v) { v.visitEquipment(this); }
    }

    static class Ward implements HospitalElement {
        final String name;
        final int    bedCount;

        Ward(String name, int bedCount) { this.name = name; this.bedCount = bedCount; }
        public void accept(HospitalVisitor v) { v.visitWard(this); }
    }

    // Concrete visitor — Doctor inspection
    static class Doctor implements HospitalVisitor {
        public void visitPatient(Patient p) {
            System.out.println("  Doctor examines " + p.name + " (age " + p.age + "): " + p.diagnosis);
        }
        public void visitEquipment(Equipment e) {
            String status = e.needsService ? "⚠ NEEDS SERVICE" : "OK";
            System.out.println("  Doctor checks " + e.name + " (" + e.yearsOld + " yrs old): " + status);
        }
        public void visitWard(Ward w) {
            System.out.println("  Doctor reviews " + w.name + " ward: " + w.bedCount + " beds");
        }
    }

    // Another visitor — Insurance Auditor
    static class InsuranceAuditor implements HospitalVisitor {
        public void visitPatient(Patient p) {
            System.out.println("  Auditor: billing for patient " + p.name + " — diagnosis: " + p.diagnosis);
        }
        public void visitEquipment(Equipment e) {
            int value = e.needsService ? 0 : e.yearsOld * 10_000;
            System.out.println("  Auditor: " + e.name + " asset value ₹" + value);
        }
        public void visitWard(Ward w) {
            System.out.println("  Auditor: " + w.name + " ward — " + w.bedCount + " beds @ ₹5,000/day each");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Visitor Pattern ===\n");

        List<HospitalElement> elements = Arrays.asList(
                new Patient("Ramesh",  45, "Hypertension"),
                new Patient("Sunita",  30, "Fracture - left arm"),
                new Equipment("MRI Scanner",    3, false),
                new Equipment("X-Ray Machine",  8, true),
                new Ward("Cardiology",  20),
                new Ward("Orthopaedics", 15)
        );

        System.out.println("--- Doctor's Round ---");
        HospitalVisitor doctor = new Doctor();
        elements.forEach(e -> e.accept(doctor));

        System.out.println("\n--- Insurance Audit ---");
        HospitalVisitor auditor = new InsuranceAuditor();
        elements.forEach(e -> e.accept(auditor));
    }
}