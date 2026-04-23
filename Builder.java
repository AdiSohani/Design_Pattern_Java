// =========================================================
// BUILDER PATTERN — Custom Burger
// Separate construction of a complex object from its
// representation. Each step is explicit and chainable.
// =========================================================
public class Builder {

    static class Burger {
        private String bun, patty, topping, sauce;

        public String toString() {
            return bun + " bun  |  " + patty + " patty  |  " + topping + "  |  " + sauce;
        }
    }

    // Builder — assembles each part step by step
    static class BurgerBuilder {
        private Burger burger = new Burger();

        BurgerBuilder addBun(String bun)       { burger.bun     = bun;     return this; }
        BurgerBuilder addPatty(String patty)   { burger.patty   = patty;   return this; }
        BurgerBuilder addTopping(String top)   { burger.topping = top;     return this; }
        BurgerBuilder addSauce(String sauce)   { burger.sauce   = sauce;   return this; }
        Burger build() { return burger; }
    }

    public static void main(String[] args) {
        System.out.println("=== Builder Pattern ===\n");

        Burger chickenBurger = new BurgerBuilder()
                .addBun("Sesame")
                .addPatty("Chicken")
                .addTopping("Lettuce & Tomato")
                .addSauce("Chipotle Mayo")
                .build();

        Burger vegBurger = new BurgerBuilder()
                .addBun("Whole Wheat")
                .addPatty("Aloo Tikki")
                .addTopping("Onion & Pickles")
                .addSauce("Mint Chutney")
                .build();

        System.out.println("Burger 1: " + chickenBurger);
        System.out.println("Burger 2: " + vegBurger);
    }
}