// =========================================================
// SINGLETON PATTERN — Country President
// Only ONE president exists at a time.
// Every request returns the same single instance.
// =========================================================
public class Singleton {

    static class President {
        // The single instance — created once, lazily
        private static President instance;
        private String name;
        private int    termStart;

        private President() {           // private: no external instantiation
            this.name      = "Droupadi Murmu";
            this.termStart = 2022;
        }

        // Thread-safe lazy initialisation
        static synchronized President getInstance() {
            if (instance == null) instance = new President();
            return instance;
        }

        String getName()      { return name; }
        int    getTermStart() { return termStart; }
    }

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern ===\n");

        President p1 = President.getInstance();
        President p2 = President.getInstance();
        President p3 = President.getInstance();

        System.out.println("Request 1: " + p1.getName() + " (term since " + p1.getTermStart() + ")");
        System.out.println("Request 2: " + p2.getName());
        System.out.println("Request 3: " + p3.getName());
        System.out.println("\nAll the same instance? " + (p1 == p2 && p2 == p3));  // true
    }
}