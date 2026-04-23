// =========================================================
// ADAPTER PATTERN — Travel Plug Adapter
// US laptop (flat 2-pin) can't plug into Indian socket (round 3-pin).
// Adapter sits in between; neither side changes.
// =========================================================
public class Adapter {

    // Target interface — what the Indian socket expects
    interface IndianSocket {
        void acceptRoundPin();
    }

    // Adaptee — existing class with incompatible interface
    static class USLaptop {
        void plugFlatPin() {
            System.out.println("US Laptop: inserting flat 2-pin plug...");
        }
    }

    // Adapter — wraps USLaptop, exposes IndianSocket interface
    static class TravelAdapter implements IndianSocket {
        private USLaptop laptop;

        TravelAdapter(USLaptop laptop) { this.laptop = laptop; }

        public void acceptRoundPin() {
            System.out.println("Travel Adapter: converting flat pin → round pin...");
            laptop.plugFlatPin();
            System.out.println("Travel Adapter: power flowing! Laptop charging.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern ===\n");

        USLaptop laptop       = new USLaptop();
        IndianSocket socket   = new TravelAdapter(laptop);

        // Client uses IndianSocket — doesn't know about USLaptop details
        socket.acceptRoundPin();
    }
}