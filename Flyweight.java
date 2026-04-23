// =========================================================
// FLYWEIGHT PATTERN — Forest of Trees
// Thousands of trees share a handful of TreeType objects.
// Only (x, y) position is stored per individual tree.
// =========================================================
import java.util.*;

public class Flyweight {

    // Flyweight — shared (intrinsic) state: name, colour, texture
    static class TreeType {
        final String name;
        final String colour;
        final String texture;

        TreeType(String name, String colour, String texture) {
            this.name = name; this.colour = colour; this.texture = texture;
            System.out.println("  [Factory] Creating new TreeType: " + name);
        }

        void draw(int x, int y) {
            System.out.println("  Drawing " + name + " (" + colour + "/" + texture + ") at (" + x + "," + y + ")");
        }
    }

    // Flyweight factory — returns existing type or creates one
    static class TreeFactory {
        private static final Map<String, TreeType> cache = new HashMap<>();

        static TreeType get(String name, String colour, String texture) {
            String key = name + colour + texture;
            return cache.computeIfAbsent(key, k -> new TreeType(name, colour, texture));
        }

        static int typeCount() { return cache.size(); }
    }

    // Context — extrinsic (unique) state: position
    static class Tree {
        private final int x, y;
        private final TreeType type;

        Tree(int x, int y, TreeType type) { this.x = x; this.y = y; this.type = type; }
        void draw() { type.draw(x, y); }
    }

    public static void main(String[] args) {
        System.out.println("=== Flyweight Pattern ===\n");
        System.out.println("Initialising forest (shared types created only once):\n");

        List<Tree> forest = new ArrayList<>();
        forest.add(new Tree(1, 2, TreeFactory.get("Pine",  "dark-green", "needle")));
        forest.add(new Tree(5, 8, TreeFactory.get("Pine",  "dark-green", "needle")));   // reuses Pine
        forest.add(new Tree(3, 6, TreeFactory.get("Oak",   "brown",      "bark")));
        forest.add(new Tree(9, 1, TreeFactory.get("Pine",  "dark-green", "needle")));   // reuses Pine
        forest.add(new Tree(4, 7, TreeFactory.get("Birch", "white",      "smooth")));
        forest.add(new Tree(2, 5, TreeFactory.get("Oak",   "brown",      "bark")));     // reuses Oak

        System.out.println("\nDrawing forest:\n");
        forest.forEach(Tree::draw);

        System.out.println("\nTrees in forest : " + forest.size());
        System.out.println("Unique TreeType objects in memory: " + TreeFactory.typeCount());
    }
}