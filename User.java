import java.util.ArrayList;

public class User {

    private String name;
    private String email;
    private String password;
    private String role;
    private Wallet wallet;
    private ArrayList<String> notifications;

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role.toUpperCase();
        this.wallet = new Wallet();
        this.notifications = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public Wallet getWallet() { return wallet; }
    public ArrayList<String> getNotifications() { return notifications; }

    public void addNotification(String message) {
        notifications.add(message);
    }

    public void displayNotifications() {
        if (notifications.isEmpty()) return;
        System.out.println("==== Notifications ====");
        for (String note : notifications) {
            System.out.println("- " + note);
        }
        notifications.clear();
    }
}
