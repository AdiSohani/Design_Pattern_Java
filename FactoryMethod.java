// =========================================================
// FACTORY METHOD PATTERN — Coffee Shop
// Each branch decides which specific drink to make.
// The ordering interface stays the same.
// =========================================================
public class FactoryMethod {

    abstract static class Drink {
        abstract String name();
    }

    static class Espresso extends Drink {
        String name() { return "Espresso"; }
    }

    static class ColdBrew extends Drink {
        String name() { return "Cold Brew"; }
    }

    // Creator — declares the factory method
    abstract static class CoffeeBar {
        abstract Drink makeDrink();          // factory method

        void serveOrder() {
            Drink d = makeDrink();
            System.out.println("Serving: " + d.name());
        }
    }

    // Concrete Creators — override the factory method
    static class EspressoBar extends CoffeeBar {
        Drink makeDrink() { return new Espresso(); }
    }

    static class ColdBrewBar extends CoffeeBar {
        Drink makeDrink() { return new ColdBrew(); }
    }

    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern ===\n");

        CoffeeBar espressoBar = new EspressoBar();
        CoffeeBar coldBrewBar = new ColdBrewBar();

        espressoBar.serveOrder();   // Serving: Espresso
        coldBrewBar.serveOrder();   // Serving: Cold Brew
    }
}