// =========================================================
// PROTOTYPE PATTERN — Legal NDA Template
// Clone an existing document instead of building from scratch.
// Only customise the fields that differ.
// =========================================================
public class Prototype {

    static class NDADocument implements Cloneable {
        private String templateText;
        String clientName;
        String date;
        String jurisdiction;

        NDADocument(String template) { this.templateText = template; }

        // Clone — shallow copy (deep copy for mutable fields if needed)
        NDADocument cloneDoc() {
            try { return (NDADocument) super.clone(); }
            catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
        }

        public String toString() {
            return "[" + templateText + "]"
                    + "  Client: " + clientName
                    + "  |  Date: " + date
                    + "  |  Jurisdiction: " + jurisdiction;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Prototype Pattern ===\n");

        // Original template
        NDADocument template = new NDADocument("Standard Non-Disclosure Agreement v3");

        // Clone and customise — no re-drafting needed
        NDADocument doc1 = template.cloneDoc();
        doc1.clientName   = "TechCorp Ltd";
        doc1.date         = "2025-01-15";
        doc1.jurisdiction = "Delhi";

        NDADocument doc2 = template.cloneDoc();
        doc2.clientName   = "StartupXYZ";
        doc2.date         = "2025-02-10";
        doc2.jurisdiction = "Mumbai";

        System.out.println("Doc 1: " + doc1);
        System.out.println("Doc 2: " + doc2);
        System.out.println("\nSame template object? " + (doc1 == doc2));  // false — different clones
    }
}