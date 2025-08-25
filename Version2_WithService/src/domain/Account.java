package domain;

// // Clase de dominio que representa una Cuenta Bancaria
public class Account {
    private String accountNumber;;// Número de Cuenta Bancaria
    private double balance;// Saldo de la cuenta, inicia en 0.0
    private AccountType accountType;// Tipo de cuenta: SAVINGS o CURRENT

    public Account(AccountType accountType) {
        this.accountNumber = AccountNumberGenerator.generate();// Número de Cuenta Bancaria
        this.accountType = accountType;// Tipo de cuenta: SAVINGS o CURRENT
        this.balance = 0.0;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public AccountType getType() { return accountType; }

    // Depositar
    public void deposit(double amount) {
        // Valida que el monto sea positivo
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        this.balance += amount;
    }

    // Retirar con reglas
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");

        double newBalance = this.balance - amount;
        // Reglas de negocio aplicadas:
        // - Una cuenta de ahorro no puede quedar en saldo negativo.
        // - Una cuenta corriente puede tener sobregiro hasta -500.
        if (this.accountType == AccountType.SAVINGS && newBalance < 0) {
            throw new IllegalArgumentException("Savings cannot go negative");
        }

        if (this.accountType == AccountType.CURRENT && newBalance < -500) {
            throw new IllegalArgumentException("Overdraft limit exceeded (-500)");
        }

        this.balance = newBalance;
    }

    @Override
    public String toString() {
        return String.format("Cuenta[%s - %s] Saldo: %.2f", accountNumber, accountType, balance);
    }
}
