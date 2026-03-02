import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketSystem {

    private List<User> registeredUsers = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private User currentUser = null;

    // ================= EVENTS =================

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> searchEvents(String keyword) {
        List<Event> results = new ArrayList<>();
        for (Event event : events) {
            if (event.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(event);
            }
        }
        return results;
    }

    public Event findEventById(String id) {
        for (Event event : events) {
            if (event.getEventId().equalsIgnoreCase(id)) {
                return event;
            }
        }
        return null;
    }

    // ================= USER =================

    public void register(String name, String email, String password) {
        for (User user : registeredUsers) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Registration Failed: Email already exists.");
                return;
            }
        }
        registeredUsers.add(new User(name, email, password));
        System.out.println("Account registered successfully for " + name);
    }

    public boolean login(String email, String password) {
        for (User user : registeredUsers) {
            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login Successful! Welcome, " + user.getName());
                return true;
            }
        }
        System.out.println("Login Failed: Invalid email or password.");
        return false;
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // ================= WALLET =================

    public void fundWallet(double amount) {
        if (currentUser == null) {
            System.out.println("You must login first.");
            return;
        }

        currentUser.getWallet().deposit(amount);
        System.out.println("Wallet funded successfully. Current balance: ₦"
                + currentUser.getWallet().getBalance());
    }

    // ================= PURCHASE =================

    public void buyTicket(String eventId, int quantity) {

        if (currentUser == null) {
            System.out.println("Operation Failed: You must be logged in.");
            return;
        }

        Event event = findEventById(eventId);

        if (event == null) {
            System.out.println("Event not found.");
            return;
        }

        double totalCost = event.getPrice() * quantity;

        if (currentUser.getWallet().getBalance() < totalCost) {
            System.out.println("Insufficient funds in your wallet.");
            return;
        }

        if (event.purchase(quantity)) {
            currentUser.getWallet().withdraw(totalCost);

            System.out.println("Purchase Confirmed!");
            System.out.println("Ticket ID: "
                    + UUID.randomUUID().toString().substring(0, 6));
            System.out.println("Remaining Balance: ₦"
                    + currentUser.getWallet().getBalance());

        } else {
            System.out.println("Not enough tickets available.");
        }
    }
}
