package service;

import domain.Account;
import domain.AccountType;
import domain.Client;
import repository.IBankRepository;

import java.util.*;

public class BankService implements IBankService {
    private final IBankRepository repository;

    public BankService(IBankRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registerClient(String dni, String firstName, String lastName, String email) {
        Client client = new Client(firstName, lastName, dni, email);
        repository.addClient(client);
    }

    @Override
    public String openAccount(String dni, AccountType type) {
        Account account = new Account(type);
        repository.addAccount(dni, account);
        return account.getAccountNumber();
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Optional<Account> accountOpt = repository.getAccount(accountNumber);
        if (accountOpt.isPresent()) {
            accountOpt.get().deposit(amount);
        } else {
            throw new NoSuchElementException("Account not found.");
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Optional<Account> accountOpt = repository.getAccount(accountNumber);
        if (accountOpt.isPresent()) {
            accountOpt.get().withdraw(amount);
        } else {
            throw new NoSuchElementException("Account not found.");
        }
    }

    @Override
    public double checkBalance(String accountNumber) {
        return repository.getAccount(accountNumber)
                .map(Account::getBalance)
                .orElseThrow(() -> new NoSuchElementException("Account not found."));
    }

    @Override
    public Optional<Client> findClient(String dni) {
        return repository.getClient(dni);
    }

    @Override
    public List<Account> getAccountsByType(String dni, AccountType type) {
        return repository.getAccountsByType(dni, type);
    }
}
