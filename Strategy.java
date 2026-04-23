// =========================================================
// STRATEGY PATTERN — Google Maps Route
// Same Navigator context; swap routing algorithm at runtime.
// =========================================================
public class Strategy {

    // Strategy interface
    interface RouteStrategy {
        void buildRoute(String from, String to);
    }

    // Concrete strategies
    static class DrivingStrategy implements RouteStrategy {
        public void buildRoute(String from, String to) {
            System.out.println("  [Driving]  " + from + " → " + to);
            System.out.println("             Via NH-48 | Est. time: 25 min | Avoid tolls: off");
        }
    }

    static class WalkingStrategy implements RouteStrategy {
        public void buildRoute(String from, String to) {
            System.out.println("  [Walking]  " + from + " → " + to);
            System.out.println("             Via footpaths | Distance: 2.1 km | Steps: ~2,800");
        }
    }

    static class CyclingStrategy implements RouteStrategy {
        public void buildRoute(String from, String to) {
            System.out.println("  [Cycling]  " + from + " → " + to);
            System.out.println("             Via cycle lane | Distance: 2.4 km | Avoid highways: on");
        }
    }

    static class PublicTransitStrategy implements RouteStrategy {
        public void buildRoute(String from, String to) {
            System.out.println("  [Transit]  " + from + " → " + to);
            System.out.println("             Bus 42 → Metro Blue Line | Est. time: 35 min | Cost: ₹30");
        }
    }

    // Context
    static class Navigator {
        private RouteStrategy strategy;

        void setStrategy(RouteStrategy strategy) {
            this.strategy = strategy;
        }

        void navigate(String from, String to) {
            if (strategy == null) { System.out.println("No strategy set!"); return; }
            strategy.buildRoute(from, to);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern ===\n");

        Navigator nav = new Navigator();

        nav.setStrategy(new DrivingStrategy());
        nav.navigate("Home", "Office");

        nav.setStrategy(new WalkingStrategy());
        nav.navigate("Hotel", "India Gate");

        nav.setStrategy(new CyclingStrategy());
        nav.navigate("Station", "Market");

        nav.setStrategy(new PublicTransitStrategy());
        nav.navigate("Noida", "Connaught Place");
    }
}