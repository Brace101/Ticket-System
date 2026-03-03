import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void creatorLoop(User user, EventManager manager, EventSearch search, Scanner input) {
        boolean running = true;
        while (running) {
            System.out.println("""
            
            
                                            ---- CREATOR MENU ----
                                            1. Create Event
                                            2. Delete Event
                                            3. Search Events
                                            4. Fund Wallet
                                            5. Check Balance
                                            6. View Transactions
                                            7. Logout
                                            
                                            
                                """);
            System.out.print("Select option: ");
            String choice = input.nextLine();
            switch (choice) {
                case "1" -> TicketActions.createEvent(manager, user, input);
                case "2" -> TicketActions.deleteEvent(manager, user, input);
                case "3" -> TicketActions.searchByCategory(search, input);
                case "4" -> TicketActions.fundWallet(user, input);
                case "5" -> TicketActions.checkBalance(user);
                case "6" -> TicketActions.viewTransactions(user);
                case "7" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void customerLoop(User user, EventManager manager, EventSearch search,
                                    Scanner input, ArrayList<User> allUsers) {
        boolean running = true;
        while (running) {
            System.out.println("""
            
                                            ---- CUSTOMER MENU ----
                                            1. Search Events
                                            2. Fund Wallet
                                            3. Check Balance
                                            4. View Transactions
                                            5. Buy Ticket
                                            6. Logout
                                            
                                            
                               """);
            System.out.print("Select option: ");
            String choice = input.nextLine();
            switch (choice) {
                case "1" -> TicketActions.searchByCategory(search, input);
                case "2" -> TicketActions.fundWallet(user, input);
                case "3" -> TicketActions.checkBalance(user);
                case "4" -> TicketActions.viewTransactions(user);
                case "5" -> TicketActions.buyTicket(user, manager, input, allUsers);
                case "6" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
