package service;

import domain.*;
import repository.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankService implements IBankService {
    private final IClientRepository clientRepo;
    private final IAccountRepository accountRepo;

    public BankService(IClientRepository clientRepo, IAccountRepository accountRepo) {
        this.clientRepo = clientRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public void registerClient(Client client) throws SQLException {
        clientRepo.save(client);
    }

    @Override
    public void openAccount(Account account) throws SQLException {
        accountRepo.save(account);
    }

    @Override
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

    @Override
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

    @Override
    public double getBalance(String accountNumber) throws SQLException {
        return accountRepo.findByAccountNumber(accountNumber)
                .map(Account::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
    }

    @Override
    public List<Account> getAccountsByClient(String dni) throws SQLException {
        // Buscar cliente por DNI
        Optional<Client> clientOpt = clientRepo.findByDni(dni);

        if (clientOpt.isPresent()) {
            int clientId = clientOpt.get().getId();

            return accountRepo.findAll().stream()
                    .filter(acc -> acc.getClientId() == clientId)
                    .collect(Collectors.toList());  // Java 11
        } else {
            throw new IllegalArgumentException("Cliente con DNI " + dni + " no encontrado");
        }
    }

}
