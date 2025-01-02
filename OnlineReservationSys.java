import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSys {

    static class ReservationSystem {

        private static final Map<String, String> users = new HashMap<>(); // Stores user login credentials
        private static final Map<Integer, Reservation> reservations = new HashMap<>(); // Stores reservations
        private static int reservationIdCounter = 1;

        static {
            // Adding a default user for testing
            users.put("admin", "password123");
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Online Reservation System");

            // Login
            if (!login(scanner)) {
                System.out.println("Invalid login credentials. Exiting.");
                return;
            }

            while (true) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel a Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        makeReservation(scanner);
                        break;
                    case 2:
                        cancelReservation(scanner);
                        break;
                    case 3:
                        System.out.println("Thank you for using the Online Reservation System. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private static boolean login(Scanner scanner) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            return users.containsKey(username) && users.get(username).equals(password);
        }

        private static void makeReservation(Scanner scanner) {
            System.out.println("\n--- Make a Reservation ---");
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter train number: ");
            String trainNumber = scanner.nextLine();
            System.out.print("Enter train name: ");
            String trainName = scanner.nextLine();
            System.out.print("Enter class type: ");
            String classType = scanner.nextLine();
            System.out.print("Enter date of journey (YYYY-MM-DD): ");
            String dateOfJourney = scanner.nextLine();
            System.out.print("From: ");
            String from = scanner.nextLine();
            System.out.print("To: ");
            String to = scanner.nextLine();

            Reservation reservation = new Reservation(reservationIdCounter++, name, trainNumber, trainName, classType, dateOfJourney, from, to);
            reservations.put(reservation.getReservationId(), reservation);

            System.out.println("Reservation successful! Your reservation ID is: " + reservation.getReservationId());
        }

        private static void cancelReservation(Scanner scanner) {
            System.out.println("\n--- Cancel a Reservation ---");
            System.out.print("Enter your reservation ID: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (reservations.containsKey(reservationId)) {
                Reservation reservation = reservations.get(reservationId);
                System.out.println("Reservation Details: " + reservation);
                System.out.print("Are you sure you want to cancel this reservation? (yes/no): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    reservations.remove(reservationId);
                    System.out.println("Reservation canceled successfully.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
            } else {
                System.out.println("No reservation found with ID: " + reservationId);
            }
        }
    }

    static class Reservation {
        private final int reservationId;
        private final String name;
        private final String trainNumber;
        private final String trainName;
        private final String classType;
        private final String dateOfJourney;
        private final String from;
        private final String to;

        public Reservation(int reservationId, String name, String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
            this.reservationId = reservationId;
            this.name = name;
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.from = from;
            this.to = to;
        }

        public int getReservationId() {
            return reservationId;
        }

        @Override
        public String toString() {
            return "Reservation ID: " + reservationId + ", Name: " + name + ", Train: " + trainNumber + " - " + trainName + ", Class: " + classType + ", Date: " + dateOfJourney + ", From: " + from + ", To: " + to;
        }
    }
}
