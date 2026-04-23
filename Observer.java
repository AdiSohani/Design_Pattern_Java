// =========================================================
// OBSERVER PATTERN — Newspaper Subscription
// Publisher notifies all subscribers automatically
// when a new edition is released.
// =========================================================
import java.util.*;

public class Observer {

    // Observer interface
    interface Subscriber {
        void update(String edition, String publisherName);
    }

    // Subject interface
    interface Publisher {
        void subscribe(Subscriber s);
        void unsubscribe(Subscriber s);
        void notifySubscribers(String edition);
    }

    // Concrete subject
    static class Newspaper implements Publisher {
        private final String name;
        private final List<Subscriber> subscribers = new ArrayList<>();
        private int editionCount = 0;

        Newspaper(String name) { this.name = name; }

        public void subscribe(Subscriber s) {
            subscribers.add(s);
            System.out.println("  [+] New subscriber registered.");
        }
        public void unsubscribe(Subscriber s) {
            subscribers.remove(s);
            System.out.println("  [-] Subscriber removed.");
        }
        public void notifySubscribers(String edition) {
            subscribers.forEach(s -> s.update(edition, name));
        }

        void publishEdition(String headline) {
            editionCount++;
            String edition = name + " | Edition #" + editionCount + " | " + headline;
            System.out.println("\n[Publisher] Releasing: " + edition);
            notifySubscribers(edition);
        }
    }

    // Concrete observer
    static class Reader implements Subscriber {
        private final String name;
        private int receivedCount = 0;

        Reader(String name) { this.name = name; }

        public void update(String edition, String from) {
            receivedCount++;
            System.out.println("  " + name + " received edition #" + receivedCount + ": \"" + edition + "\"");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Observer Pattern ===\n");

        Newspaper paper = new Newspaper("The Morning Times");

        Reader raj   = new Reader("Raj");
        Reader priya = new Reader("Priya");
        Reader aman  = new Reader("Aman");

        paper.subscribe(raj);
        paper.subscribe(priya);
        paper.subscribe(aman);

        paper.publishEdition("Budget 2025 announced");

        System.out.println("\nPriya unsubscribes...");
        paper.unsubscribe(priya);

        paper.publishEdition("India wins T20 World Cup");
    }
}