// =========================================================
// DECORATOR PATTERN — Coffee with Toppings
// Wrap a coffee object with toppings; each layer adds
// to description and cost without touching the base class.
// =========================================================
public class Decorator {

    // Component interface
    interface Coffee {
        String getDescription();
        int    getCost();
    }

    // Concrete component
    static class PlainCoffee implements Coffee {
        public String getDescription() { return "Plain Coffee"; }
        public int    getCost()        { return 50; }
    }

    // Base decorator — wraps a Coffee
    abstract static class CoffeeDecorator implements Coffee {
        protected Coffee coffee;
        CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }
    }

    // Concrete decorators
    static class MilkDecorator extends CoffeeDecorator {
        MilkDecorator(Coffee c) { super(c); }
        public String getDescription() { return coffee.getDescription() + ", Milk"; }
        public int    getCost()        { return coffee.getCost() + 10; }
    }

    static class CaramelDecorator extends CoffeeDecorator {
        CaramelDecorator(Coffee c) { super(c); }
        public String getDescription() { return coffee.getDescription() + ", Caramel"; }
        public int    getCost()        { return coffee.getCost() + 20; }
    }

    static class WhipDecorator extends CoffeeDecorator {
        WhipDecorator(Coffee c) { super(c); }
        public String getDescription() { return coffee.getDescription() + ", Whipped Cream"; }
        public int    getCost()        { return coffee.getCost() + 15; }
    }

    static class VanillaDecorator extends CoffeeDecorator {
        VanillaDecorator(Coffee c) { super(c); }
        public String getDescription() { return coffee.getDescription() + ", Vanilla Syrup"; }
        public int    getCost()        { return coffee.getCost() + 12; }
    }

    static void printCoffee(Coffee c) {
        System.out.printf("  ₹%-4d  %s%n", c.getCost(), c.getDescription());
    }

    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern ===\n");

        Coffee c = new PlainCoffee();
        printCoffee(c);

        c = new MilkDecorator(c);
        printCoffee(c);

        c = new CaramelDecorator(c);
        printCoffee(c);

        c = new WhipDecorator(c);
        printCoffee(c);

        c = new VanillaDecorator(c);
        printCoffee(c);

        System.out.println("\nFinal order: " + c.getDescription());
        System.out.println("Total cost : ₹" + c.getCost());
    }
}