import java.util.ArrayList;
import java.util.Scanner;

public class TicketSystem {

    private ArrayList<User> users;
    private EventManager eventManager;
    private EventSearch eventSearch;
    private Scanner input;

    public TicketSystem() {
        users = new ArrayList<>();
        eventManager = new EventManager();
        eventSearch = new EventSearch(eventManager.getEvents());
        input = new Scanner(System.in);
    }

    public void register() {
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter email: ");
        String email = input.nextLine();
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered!");
                return;
            }
        }
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter role (USER / CREATOR): ");
        String role = input.nextLine().toUpperCase();

        User newUser = new User(name, email, password, role);
        users.add(newUser);
        System.out.println("Registration successful!");
    }

    public User login() {
        System.out.print("Enter email: ");
        String email = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome " + u.getName());
                u.displayNotifications(); // show notifications
                return u;
            }
        }
        System.out.println("Login failed: Invalid credentials");
        return null;
    }

    public void runSystem() {
        boolean systemRunning = true;

        while (systemRunning) {
            System.out.println("""
                    ==== TICKET SYSTEM ====
                    1. Register
                    2. Login
                    0. Exit
                    """);
            System.out.print("Select option: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1" -> register();
                case "2" -> {
                    User currentUser = login();
                    if (currentUser != null) {
                        if (currentUser.getRole().equals("CREATOR")) {
                            Menu.creatorLoop(currentUser, eventManager, eventSearch, input);
                        } else {
                            Menu.customerLoop(currentUser, eventManager, eventSearch, input, users);
                        }
                    }
                }
                case "0" -> systemRunning = false;
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("System shutting down...");
    }
}
