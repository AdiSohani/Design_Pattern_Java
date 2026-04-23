// =========================================================
// MEDIATOR PATTERN — Air Traffic Control (ATC)
// Planes never communicate directly with each other.
// All coordination goes through the ATC tower.
// =========================================================
import java.util.*;

public class Mediator {

    // Mediator interface
    interface ATC {
        void registerPlane(Plane plane);
        void relay(String message, Plane sender);
    }

    // Colleague
    static class Plane {
        private final String callSign;
        private final ATC    atc;

        Plane(String callSign, ATC atc) {
            this.callSign = callSign;
            this.atc      = atc;
            atc.registerPlane(this);
        }

        // Send via mediator
        void transmit(String message) {
            System.out.println(callSign + " → ATC : \"" + message + "\"");
            atc.relay(message, this);
        }

        // Receive from mediator
        void receive(String message, String fromCallSign) {
            System.out.println(callSign + " ← ATC : [from " + fromCallSign + "] \"" + message + "\"");
        }

        String getCallSign() { return callSign; }
    }

    // Concrete mediator
    static class TowerATC implements ATC {
        private final List<Plane> planes = new ArrayList<>();

        public void registerPlane(Plane plane) { planes.add(plane); }

        public void relay(String message, Plane sender) {
            for (Plane p : planes) {
                if (p != sender) p.receive(message, sender.getCallSign());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Mediator Pattern ===\n");

        TowerATC atc = new TowerATC();

        Plane ai202  = new Plane("AI-202",  atc);
        Plane ig6301 = new Plane("6E-6301", atc);
        Plane uk987  = new Plane("UK-987",  atc);

        System.out.println();
        ai202.transmit("Requesting clearance to land on runway 27L");
        System.out.println();
        ig6301.transmit("Holding at waypoint ALPHA, fuel at 40%");
        System.out.println();
        uk987.transmit("Beginning descent from FL350");
    }
}