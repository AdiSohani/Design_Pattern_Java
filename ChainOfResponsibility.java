// =========================================================
// CHAIN OF RESPONSIBILITY — Customer Complaint Escalation
// Each handler either resolves the complaint or passes it up.
// =========================================================
public class ChainOfResponsibility {

    abstract static class SupportHandler {
        protected SupportHandler next;

        SupportHandler setNext(SupportHandler next) {
            this.next = next;
            return next;   // allows chaining: a.setNext(b).setNext(c)
        }

        abstract void handle(String issue, int refundAmount);

        void escalate(String issue, int amount) {
            if (next != null) next.handle(issue, amount);
            else System.out.println("  [UNRESOLVED] No handler could resolve: " + issue);
        }
    }

    static class SupportAgent extends SupportHandler {
        public void handle(String issue, int amount) {
            if (amount <= 1_000) {
                System.out.println("  Agent resolved '" + issue + "' with ₹" + amount + " refund.");
            } else {
                System.out.println("  Agent: ₹" + amount + " exceeds my limit. Escalating to Team Lead...");
                escalate(issue, amount);
            }
        }
    }

    static class TeamLead extends SupportHandler {
        public void handle(String issue, int amount) {
            if (amount <= 10_000) {
                System.out.println("  Team Lead resolved '" + issue + "' with ₹" + amount + " refund.");
            } else {
                System.out.println("  Team Lead: ₹" + amount + " exceeds my limit. Escalating to Manager...");
                escalate(issue, amount);
            }
        }
    }

    static class Manager extends SupportHandler {
        public void handle(String issue, int amount) {
            if (amount <= 1_00_000) {
                System.out.println("  Manager resolved '" + issue + "' with ₹" + amount + " refund.");
            } else {
                System.out.println("  Manager: ₹" + amount + " is too large. Escalating to Director...");
                escalate(issue, amount);
            }
        }
    }

    static class Director extends SupportHandler {
        public void handle(String issue, int amount) {
            System.out.println("  Director resolved '" + issue + "' with ₹" + amount + " refund. (Final authority)");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Chain of Responsibility Pattern ===\n");

        SupportAgent agent    = new SupportAgent();
        TeamLead     lead     = new TeamLead();
        Manager      manager  = new Manager();
        Director     director = new Director();

        // Build the chain
        agent.setNext(lead).setNext(manager).setNext(director);

        System.out.println("Complaint 1 — ₹500 refund:");
        agent.handle("Wrong item delivered", 500);

        System.out.println("\nComplaint 2 — ₹8,000 refund:");
        agent.handle("Product defective", 8_000);

        System.out.println("\nComplaint 3 — ₹50,000 refund:");
        agent.handle("Damaged electronics", 50_000);

        System.out.println("\nComplaint 4 — ₹5,00,000 refund:");
        agent.handle("Major data loss", 5_00_000);
    }
}