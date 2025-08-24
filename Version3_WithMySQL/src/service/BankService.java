package service;

import domain.*;
import repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class BankService {
    private final ClientRepository clientRepo;
    private final AccountRepository accountRepo;

    public BankService(Connection connection) {
        this.clientRepo = new ClientRepository(connection);
        this.accountRepo = new AccountRepository(connection);
    }

    // Registrar cliente
    public void registerClient(Client client) throws SQLException {
        clientRepo.save(client);
    }

    // Abrir cuenta bancaria
    public void openAccount(Account account) throws SQLException {
        accountRepo.save(account);
    }

    // Depositar
    public void deposit(String accountNumber, double amount) throws SQLException {
        Optional<Account> accOpt = accountRepo.findByAccountNumber(accountNumber);
        if (accOpt.isPresent()) {
            Account account = accOpt.get();
            double newBalance = account.getBalance() + amount;
            accountRepo.updateBalance(accountNumber, newBalance);
        } else {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }
    }

    // Retirar con reglas de negocio
    public void withdraw(String accountNumber, double amount) throws SQLException {
        Optional<Account> accOpt = accountRepo.findByAccountNumber(accountNumber);
        if (accOpt.isPresent()) {
            Account account = accOpt.get();
            double newBalance = account.getBalance() - amount;

            if (account.getType() == AccountType.SAVINGS && newBalance < 0) {
                throw new IllegalArgumentException("Las cuentas de ahorro no pueden quedar negativas");
            }

            if (account.getType() == AccountType.CURRENT && newBalance < -500) {
                throw new IllegalArgumentException("Límite de sobregiro alcanzado (-500)");
            }

            accountRepo.updateBalance(accountNumber, newBalance);
        } else {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }
    }

    // Consultar saldo
    public double getBalance(String accountNumber) throws SQLException {
        Optional<Account> accOpt = accountRepo.findByAccountNumber(accountNumber);
        return accOpt.map(Account::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
    }
}
