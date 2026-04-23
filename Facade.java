// =========================================================
// FACADE PATTERN — Travel Agency (Holiday Package)
// One call to the agency books flights, hotel, and car.
// Client never touches individual subsystems.
// =========================================================
public class Facade {

    // Complex subsystems
    static class FlightBooking {
        void searchFlights(String destination) {
            System.out.println("  [Flights] Searching flights to " + destination + "...");
        }
        void book(String destination) {
            System.out.println("  [Flights] Flight to " + destination + " CONFIRMED.");
        }
    }

    static class HotelBooking {
        void checkAvailability(String destination) {
            System.out.println("  [Hotel]   Checking hotels in " + destination + "...");
        }
        void book(String destination) {
            System.out.println("  [Hotel]   Hotel in " + destination + " CONFIRMED.");
        }
    }

    static class CarRental {
        void book(String destination) {
            System.out.println("  [Car]     Rental car in " + destination + " CONFIRMED.");
        }
    }

    static class TravelInsurance {
        void purchase(String destination) {
            System.out.println("  [Insurance] Travel insurance for " + destination + " ACTIVE.");
        }
    }

    // Facade — simple interface over all subsystems
    static class TravelAgency {
        private FlightBooking   flights   = new FlightBooking();
        private HotelBooking    hotel     = new HotelBooking();
        private CarRental       car       = new CarRental();
        private TravelInsurance insurance = new TravelInsurance();

        void bookHolidayPackage(String destination) {
            System.out.println("Booking complete holiday package to " + destination + ":\n");
            flights.searchFlights(destination);
            flights.book(destination);
            hotel.checkAvailability(destination);
            hotel.book(destination);
            car.book(destination);
            insurance.purchase(destination);
            System.out.println("\nAll done! Enjoy your trip to " + destination + ".");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Facade Pattern ===\n");

        TravelAgency agency = new TravelAgency();
        agency.bookHolidayPackage("Goa");
    }
}