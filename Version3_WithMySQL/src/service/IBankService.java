package service;

import domain.Account;
import domain.Client;

import java.sql.SQLException;
import java.util.List;

public interface IBankService {
    void registerClient(Client client) throws SQLException;
    void openAccount(Account account) throws SQLException;
    void deposit(String accountNumber, double amount) throws SQLException;
    void withdraw(String accountNumber, double amount) throws SQLException;
    double getBalance(String accountNumber) throws SQLException;
    List<Account> getAccountsByClient(String dni) throws SQLException;
}
