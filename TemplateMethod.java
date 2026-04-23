// =========================================================
// TEMPLATE METHOD PATTERN — Recipe Steps
// The abstract class defines the cooking skeleton.
// Each recipe subclass fills in only the varying steps.
// =========================================================
public class TemplateMethod {

    // Abstract class with template method
    abstract static class Recipe {

        // Template method — fixed skeleton, declared final
        final void make() {
            System.out.println("  1. " + gatherIngredients());
            System.out.println("  2. " + prepIngredients());
            System.out.println("  3. " + cook());
            System.out.println("  4. " + plate());
            if (addGarnish()) {
                System.out.println("  5. Adding garnish.");
            }
            System.out.println("  → Ready to serve!\n");
        }

        // Common steps (can be overridden if needed)
        String gatherIngredients() { return "Gathering ingredients from pantry."; }
        String plate()             { return "Plating neatly on the serving dish."; }

        // Hook — subclass may override to add garnish or not
        boolean addGarnish() { return false; }

        // Abstract steps — subclass must implement
        abstract String prepIngredients();
        abstract String cook();
    }

    // Concrete recipes
    static class PastaRecipe extends Recipe {
        String prepIngredients() { return "Chopping garlic, measuring pasta and tomatoes."; }
        String cook()            { return "Boiling pasta, sautéing garlic, simmering sauce for 15 min."; }
        boolean addGarnish()     { return true; }   // adds Parmesan
    }

    static class CurryRecipe extends Recipe {
        String prepIngredients() { return "Chopping onions, grinding spices, cubing paneer."; }
        String cook()            { return "Sautéing onions, adding spices, simmering gravy 20 min."; }
    }

    static class SaladRecipe extends Recipe {
        String gatherIngredients() { return "Picking fresh veggies from the fridge."; }
        String prepIngredients()   { return "Washing and chopping all vegetables."; }
        String cook()              { return "Tossing with olive oil, lemon, salt."; }
        boolean addGarnish()       { return true; }  // adds sesame seeds
    }

    public static void main(String[] args) {
        System.out.println("=== Template Method Pattern ===\n");

        System.out.println("--- Making Pasta ---");
        new PastaRecipe().make();

        System.out.println("--- Making Curry ---");
        new CurryRecipe().make();

        System.out.println("--- Making Salad ---");
        new SaladRecipe().make();
    }
}