package repository;

import domain.Account;
import domain.AccountType;
import domain.Client;

import java.util.*;

public interface IBankRepository {
    void addClient(Client client);
    void addAccount(String dni, Account account);
    Optional<Client> getClient(String dni);
    Optional<Account> getAccount(String accountNumber);
    List<Account> getAccountsByType(String dni, AccountType type);
}
