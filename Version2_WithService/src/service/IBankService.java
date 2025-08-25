package service;

import domain.Account;
import domain.AccountType;
import domain.Client;

import java.util.*;

public interface IBankService {
    void registerClient(String dni, String firstName, String lastName, String email);
    String openAccount(String dni, AccountType type);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
    double checkBalance(String accountNumber);
    Optional<Client> findClient(String dni);
    List<Account> getAccountsByType(String dni, AccountType type);
}
