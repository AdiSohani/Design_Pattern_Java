// =========================================================
// ABSTRACT FACTORY PATTERN — IKEA Furniture Sets
// Nordic factory makes Nordic sofa + table.
// Modern factory makes Modern sofa + table.
// All pieces always match within a set.
// =========================================================
public class AbstractFactory {

    // Abstract products
    interface Sofa  { String style(); }
    interface Table { String style(); }

    // Nordic family
    static class NordicSofa  implements Sofa  { public String style() { return "Nordic Sofa";  } }
    static class NordicTable implements Table { public String style() { return "Nordic Table"; } }

    // Modern family
    static class ModernSofa  implements Sofa  { public String style() { return "Modern Sofa";  } }
    static class ModernTable implements Table { public String style() { return "Modern Table"; } }

    // Abstract factory
    interface FurnitureFactory {
        Sofa  createSofa();
        Table createTable();
    }

    // Concrete factories
    static class NordicFactory implements FurnitureFactory {
        public Sofa  createSofa()  { return new NordicSofa();  }
        public Table createTable() { return new NordicTable(); }
    }

    static class ModernFactory implements FurnitureFactory {
        public Sofa  createSofa()  { return new ModernSofa();  }
        public Table createTable() { return new ModernTable(); }
    }

    // Client — works with any factory
    static void furnishRoom(FurnitureFactory factory) {
        Sofa  sofa  = factory.createSofa();
        Table table = factory.createTable();
        System.out.println("Room furnished with: " + sofa.style() + " + " + table.style());
    }

    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern ===\n");

        furnishRoom(new NordicFactory());   // Nordic Sofa + Nordic Table
        furnishRoom(new ModernFactory());   // Modern Sofa + Modern Table
    }
}