import java.util.Scanner;
import java.util.List;

public class TicketSystemDriver {

    public static void main(String... args) {

        Scanner input = new Scanner(System.in);
        TicketSystem system = new TicketSystem();

        // Add Events
        system.addEvent(new Event("EVT01", "Java Mastery", 100.0, 10, true));
        system.addEvent(new Event("EVT02", "Python Basics", 50.0, 20, false));
        system.addEvent(new Event("EVT03", "Spring Boot Pro", 200.0, 5, true));

        boolean running = true;

        while (running) {

            displayMenu();
            System.out.print("Select an option: ");
            String choice = input.nextLine();

            switch (choice) {

                case "1" -> handleRegistration(system, input);

                case "2" -> handleLogin(system, input);

                case "3" -> {
                    System.out.print("Enter event name to search: ");
                    String search = input.nextLine();

                    List<Event> results = system.searchEvents(search);

                    if (results.isEmpty()) {
                        System.out.println("No events found.");
                    } else {
                        for (Event event : results) {
                            System.out.printf("[%s] %s - ₦%.2f (Tickets Left: %d)%n",
                                    event.getEventId(),
                                    event.getName(),
                                    event.getPrice(),
                                    event.getAvailableTickets());
                        }
                    }
                }

                case "4" -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = Doubleevent.parseDouble(input.nextLine());
                    system.fundWallet(amount);
                }

                case "5" -> {
                    System.out.print("Enter Event ID: ");
                    String eventId = input.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(input.nextLine());

                    system.buyTicket(eventId, quantity);
                }

                case "6" -> system.logout();

                case "0" -> running = false;

                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("""
            --- TICKET SYSTEM MENU ---
            1. Register
            2. Login
            3. Search Events
            4. Fund Wallet
            5. Buy Ticket
            6. Logout
            0. Exit
            --------------------------
            """);
    }

    private static void handleRegistration(TicketSystem system, Scanner input) {

        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        system.register(name, email, password);
    }

    private static void handleLogin(TicketSystem system, Scanner input) {

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        system.login(email, password);
    }
}
