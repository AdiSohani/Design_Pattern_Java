// =========================================================
// COMMAND PATTERN — Restaurant Order Slip
// Orders are encapsulated as objects — queue, cancel, undo.
// =========================================================
import java.util.*;

public class Command {

    // Command interface
    interface Order {
        void execute();
        void undo();
    }

    // Receiver — does the actual work
    static class Chef {
        void prepareDish(String dish) {
            System.out.println("  Chef: Preparing '" + dish + "'");
        }
        void cancelDish(String dish) {
            System.out.println("  Chef: Cancelling '" + dish + "'");
        }
    }

    // Concrete command
    static class DishOrder implements Order {
        private final Chef   chef;
        private final String dish;

        DishOrder(Chef chef, String dish) { this.chef = chef; this.dish = dish; }

        public void execute() { chef.prepareDish(dish); }
        public void undo()    { chef.cancelDish(dish); }
    }

    // Invoker — holds and submits orders
    static class Waiter {
        private final List<DishOrder> pendingOrders = new ArrayList<>();
        private final List<DishOrder> submittedOrders = new ArrayList<>();

        void takeOrder(DishOrder order) {
            pendingOrders.add(order);
            System.out.println("  Waiter: Noted '" + order.dish + "'");
        }

        void cancelLastPending() {
            if (pendingOrders.isEmpty()) { System.out.println("  Waiter: Nothing to cancel."); return; }
            DishOrder last = pendingOrders.remove(pendingOrders.size() - 1);
            System.out.println("  Waiter: Removing '" + last.dish + "' from order before submission.");
        }

        void submitAllOrders() {
            System.out.println("  Waiter: Sending order slip to kitchen...");
            for (DishOrder o : pendingOrders) { o.execute(); submittedOrders.add(o); }
            pendingOrders.clear();
        }

        void undoLastSubmitted() {
            if (submittedOrders.isEmpty()) return;
            DishOrder last = submittedOrders.remove(submittedOrders.size() - 1);
            last.undo();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Command Pattern ===\n");

        Chef   chef   = new Chef();
        Waiter waiter = new Waiter();

        System.out.println("Taking orders:");
        waiter.takeOrder(new DishOrder(chef, "Paneer Tikka"));
        waiter.takeOrder(new DishOrder(chef, "Naan x2"));
        waiter.takeOrder(new DishOrder(chef, "Mango Lassi"));
        waiter.takeOrder(new DishOrder(chef, "Gulab Jamun"));

        System.out.println("\nCustomer changes mind — cancel last item:");
        waiter.cancelLastPending();

        System.out.println("\nSubmitting to kitchen:");
        waiter.submitAllOrders();

        System.out.println("\nCustomer cancels Naan after submission:");
        waiter.undoLastSubmitted();
    }
}