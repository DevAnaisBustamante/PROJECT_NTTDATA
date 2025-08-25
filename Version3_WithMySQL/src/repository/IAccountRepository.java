package repository;

import domain.Account;
import java.sql.SQLException;
import java.util.Optional;

public interface IAccountRepository {
    void save(Account account) throws SQLException;
    Optional<Account> findByAccountNumber(String accountNumber) throws SQLException;
    void updateBalance(String accountNumber, double newBalance) throws SQLException;
    java.util.List<Account> findAll() throws SQLException;   // 👈 agregado
}
