import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type; // DEPOSIT or WITHDRAW
    private double amount;
    private LocalDateTime dateTime;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MMM-dd hh:mm a");

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }

    // Return formatted date string
    public String getFormattedDateTime() {
        return dateTime.format(formatter);
    }

    // Optional: return raw LocalDateTime if needed
    public LocalDateTime getDateTime() { return dateTime; }
}
