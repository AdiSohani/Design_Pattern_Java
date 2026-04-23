// =========================================================
// COMPOSITE PATTERN — Company Org Chart
// Employees (leaves) and Departments (composites) share
// the same interface. getSalary() works uniformly on both.
// =========================================================
import java.util.ArrayList;
import java.util.List;

public class Composite {

    // Component interface
    interface OrgComponent {
        String getName();
        int    getSalary();
        void   print(String indent);
    }

    // Leaf
    static class Employee implements OrgComponent {
        private String name;
        private String role;
        private int    salary;

        Employee(String name, String role, int salary) {
            this.name = name; this.role = role; this.salary = salary;
        }

        public String getName()    { return name; }
        public int    getSalary()  { return salary; }
        public void   print(String indent) {
            System.out.println(indent + "- " + name + " (" + role + ") ₹" + salary);
        }
    }

    // Composite
    static class Department implements OrgComponent {
        private String name;
        private List<OrgComponent> members = new ArrayList<>();

        Department(String name) { this.name = name; }

        void add(OrgComponent c) { members.add(c); }

        public String getName()   { return name; }
        public int    getSalary() { return members.stream().mapToInt(OrgComponent::getSalary).sum(); }
        public void   print(String indent) {
            System.out.println(indent + "[" + name + "]  total salary: ₹" + getSalary());
            members.forEach(m -> m.print(indent + "  "));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Composite Pattern ===\n");

        Employee alice = new Employee("Alice", "Engineer",  80_000);
        Employee bob   = new Employee("Bob",   "Engineer",  70_000);
        Employee carol = new Employee("Carol", "Designer",  65_000);
        Employee diana = new Employee("Diana", "Manager",  120_000);
        Employee eve   = new Employee("Eve",   "CEO",      300_000);

        Department engineering = new Department("Engineering");
        engineering.add(alice); engineering.add(bob);

        Department design = new Department("Design");
        design.add(carol);

        Department company = new Department("Acme Corp");
        company.add(eve);
        company.add(diana);
        company.add(engineering);
        company.add(design);

        company.print("");
    }
}