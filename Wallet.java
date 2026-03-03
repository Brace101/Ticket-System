import java.util.ArrayList;

public class Wallet {

    private double balance;
    private ArrayList<Transaction> transactions;

    public Wallet() {
        balance = 0.0;
        transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("DEPOSIT", amount));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("WITHDRAW", amount));
            return true;
        }
        return false;
    }

    public double getBalance() { return balance; }
    public ArrayList<Transaction> getTransactions() { return transactions; }
}
