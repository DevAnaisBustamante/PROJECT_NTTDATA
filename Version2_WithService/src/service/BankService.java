package service;

import domain.Account;
import domain.AccountType;
import domain.Client;
import repository.BankRepository;

import java.util.*;

//Lógica del Negocio
public class BankService {
    //Usa el repositorio para acceder a los datos
    private final BankRepository repository;

    public BankService(BankRepository repository) {
        this.repository = repository;
    }

    // Registrar cliente
    public void registerClient(String dni, String firstName, String lastName, String email) {
        Client client = new Client(firstName, lastName, dni, email);
        repository.addClient(client);
    }

    // Abrir una nueva cuenta para un cliente existente
    public String openAccount(String dni, AccountType type) {
        Account account = new Account(type);
        repository.addAccount(dni, account);
        return account.getAccountNumber();
    }

    // Realizar un depósito
    public void deposit(String accountNumber, double amount) {
        Optional<Account> accountOpt = repository.getAccount(accountNumber);
        if (accountOpt.isPresent()) {
            accountOpt.get().deposit(amount);
        } else {
            throw new NoSuchElementException("Account not found.");
        }
    }

    // Realizar un retiro
    public void withdraw(String accountNumber, double amount) {
        Optional<Account> accountOpt = repository.getAccount(accountNumber);
        if (accountOpt.isPresent()) {
            accountOpt.get().withdraw(amount);
        } else {
            throw new NoSuchElementException("Account not found.");
        }
    }

    // Consultar el saldo
    public double checkBalance(String accountNumber) {
        return repository.getAccount(accountNumber)
                .map(Account::getBalance)
                .orElseThrow(() -> new NoSuchElementException("Account not found."));
    }

    // Buscar cliente por DNI
    public Optional<Client> findClient(String dni) {
        return repository.getClient(dni);
    }

    // Filtrar cuentas de un cliente por tipo
    public List<Account> getAccountsByType(String dni, AccountType type) {
        return repository.getAccountsByType(dni, type);
    }
}
