package domain;

// Representa una cuenta bancaria
public class Account {
    private int id;                // ID generado en BD
    private String accountNumber;  // Número de cuenta único
    private double balance;        // Saldo
    private AccountType type;      // Tipo (SAVINGS o CURRENT)
    private int clientId;          // Relación con cliente

    public Account(String accountNumber, double balance, AccountType type, int clientId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.clientId = clientId;
    }

    public Account(int id, String accountNumber, double balance, AccountType type, int clientId) {
        this(accountNumber, balance, type, clientId);
        this.id = id;
    }

    // Getters
    public int getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public AccountType getType() { return type; }
    public int getClientId() { return clientId; }
}
