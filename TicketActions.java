import java.util.ArrayList;
import java.util.Scanner;

public class TicketActions {

    // CREATE EVENT 
    public static void createEvent(EventManager manager, User user, Scanner input) {
        System.out.print("Event name: ");
        String name = input.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(input.nextLine());

        System.out.print("Tickets: ");
        int tickets = Integer.parseInt(input.nextLine());

        System.out.print("Is virtual? (true/false): ");
        boolean isVirtual = Boolean.parseBoolean(input.nextLine());

        System.out.print("Location: ");
        String location = input.nextLine();

        System.out.println("Categories: ");
        for (EventCategory category : EventCategory.values()) System.out.println("\n- " + category);
        System.out.print("Choose category: ");
        EventCategory category = EventCategory.valueOf(input.nextLine().toUpperCase());

        manager.createEvent(user, name, price, tickets, isVirtual, location, category);
    }

    // ---------------- DELETE EVENT ----------------
    public static void deleteEvent(EventManager manager, User user, Scanner input) {
        System.out.print("Event ID to delete: ");
        String eventId = input.nextLine();
        manager.deleteEvent(user, eventId);
    }

    // ---------------- SEARCH EVENTS ----------------
    public static void searchByCategory(EventSearch search, Scanner input) {
        System.out.println("Categories:");
        for (EventCategory category : EventCategory.values()) System.out.println("- " + category);
        System.out.print("Choose category: ");
        EventCategory category = EventCategory.valueOf(input.nextLine().toUpperCase());

        ArrayList<Event> results = search.searchByCategory(category);
        search.displayTable(results);
    }

    // ---------------- FUND WALLET ----------------
    public static void fundWallet(User user, Scanner input) {
        System.out.print("Amount to deposit: ");
        double amount = Double.parseDouble(input.nextLine());
        user.getWallet().deposit(amount);
        System.out.println("Wallet funded successfully.");
    }

    // ---------------- CHECK BALANCE ----------------
    public static void checkBalance(User user) {
        System.out.println("Balance: ₦" + user.getWallet().getBalance());
    }

    // ---------------- VIEW TRANSACTIONS ----------------
    public static void viewTransactions(User user) {
        ArrayList<Transaction> transactions = user.getWallet().getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions.");
            return;
        }
        System.out.printf("%-12s %-12s %-20s%n", "TYPE", "AMOUNT", "DATE & TIME");
        for (Transaction transaction : transactions)
            System.out.printf("%-12s ₦%-11.2f %-20s%n",
                    transaction.getType(), transaction.getAmount(), transaction.getFormattedDateTime());
    }

    // ---------------- BUY TICKET ----------------
    public static void buyTicket(User user, EventManager manager, Scanner input, ArrayList<User> allUsers) {
        System.out.print("Enter Event ID: ");
        String eventId = input.nextLine();

        Event foundEvent = null;

        // ----------- SIMPLE FOR-LOOP SEARCH -----------
        for (Event event : manager.getEvents()) {
            if (event.getEventId().equalsIgnoreCase(eventId)) {
                foundEvent = event;
                break; // Stop searching once found
            }
        }

        if (foundEvent == null) {
            System.out.println("Event not found.");
            return;
        }

        // Ticket Quantity
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(input.nextLine());

        double total = foundEvent.getPrice() * quantity;

        if (user.getWallet().getBalance() < total) {
            System.out.println("Insufficient funds.");
            return;
        }

        if (!foundEvent.purchase(quantity)) {
            System.out.println("Not enough tickets available.");
            return;
        }

        // customer's wallet here
        user.getWallet().withdraw(total);

        // creators wallet here
        User creator = foundEvent.getCreator();
        creator.getWallet().deposit(total);

        // notifications
        creator.addNotification("Tickets sold for your event '" + foundEvent.getName()
                + "'. Amount ₦" + total);
        user.addNotification("You purchased " + quantity + " tickets for " + foundEvent.getName());

        System.out.println("Ticket purchased successfully! Total: ₦" + total);
    }
}
