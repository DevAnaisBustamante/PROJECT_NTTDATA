// Clase Account
public class Account {
    private String accountNumber;
    private double balance;
    private AccountType type;

    public Account(String accountNumber, AccountType type) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = 0.0;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public AccountType getType() { return type; }

    // Depositar
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        this.balance += amount;
    }

    // Retirar con reglas
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");

        double newBalance = this.balance - amount;

        if (this.type == AccountType.SAVINGS && newBalance < 0) {
            throw new IllegalArgumentException("Savings cannot go negative");
        }

        if (this.type == AccountType.CURRENT && newBalance < -500) {
            throw new IllegalArgumentException("Overdraft limit exceeded (-500)");
        }

        this.balance = newBalance;
    }
}
