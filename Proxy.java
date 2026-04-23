// =========================================================
// PROXY PATTERN — Receptionist & CEO
// Receptionist validates, logs, and rate-limits access
// to the CEO. CEO code never changes.
// =========================================================
import java.util.Set;
import java.util.HashSet;

public class Proxy {

    // Subject interface
    interface Executive {
        void meetWith(String visitor);
    }

    // Real subject — the actual CEO
    static class CEO implements Executive {
        private String name = "Ratan Sharma";
        public void meetWith(String visitor) {
            System.out.println("  CEO " + name + ": meeting with '" + visitor + "'.");
        }
    }

    // Proxy — controls access to CEO
    static class Receptionist implements Executive {
        private final CEO ceo = new CEO();
        private final Set<String> approvedVisitors =
                new HashSet<>(Set.of("Board Member", "Investor", "CTO"));
        private int meetingCount = 0;
        private static final int MAX_MEETINGS_PER_DAY = 3;

        public void meetWith(String visitor) {
            System.out.print("Receptionist checking '" + visitor + "'... ");

            if (!approvedVisitors.contains(visitor)) {
                System.out.println("DENIED — not on the approved list.");
                return;
            }
            if (meetingCount >= MAX_MEETINGS_PER_DAY) {
                System.out.println("DENIED — CEO has no slots left today.");
                return;
            }

            meetingCount++;
            System.out.println("APPROVED (meeting " + meetingCount + "/" + MAX_MEETINGS_PER_DAY + ").");
            ceo.meetWith(visitor);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern ===\n");

        Executive proxy = new Receptionist();
        proxy.meetWith("Investor");
        proxy.meetWith("Random Salesperson");
        proxy.meetWith("Board Member");
        proxy.meetWith("CTO");
        proxy.meetWith("Investor");          // exceeds daily limit
    }
}