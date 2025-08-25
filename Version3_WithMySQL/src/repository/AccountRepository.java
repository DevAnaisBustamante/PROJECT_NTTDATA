package repository;

import domain.Account;
import domain.AccountType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository implements IAccountRepository {
    private final Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Account account) throws SQLException {
        String sql = "INSERT INTO cuentabancaria (numerocuenta, saldo, tipo, cliente_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountNumber());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getType().name());
            stmt.setInt(4, account.getClientId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM cuentabancaria WHERE numerocuenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(
                        rs.getInt("id"),
                        rs.getString("numerocuenta"),
                        rs.getDouble("saldo"),
                        AccountType.valueOf(rs.getString("tipo")),
                        rs.getInt("cliente_id")
                );
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateBalance(String accountNumber, double newBalance) throws SQLException {
        String sql = "UPDATE cuentabancaria SET saldo = ? WHERE numerocuenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM cuentabancaria";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("id"),
                        rs.getString("numerocuenta"),
                        rs.getDouble("saldo"),
                        AccountType.valueOf(rs.getString("tipo")),
                        rs.getInt("cliente_id")
                );
                accounts.add(account);
            }
        }
        return accounts;
    }


}
