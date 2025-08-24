package repository;

import domain.Account;
import domain.AccountType;

import java.sql.*;
import java.util.Optional;

public class AccountRepository {
    private final Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    // Guardar cuenta
    public void save(Account account) throws SQLException {
        String sql = "INSERT INTO cuenta (numero_cuenta, saldo, tipo, cliente_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountNumber());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getType().name());
            stmt.setInt(4, account.getClientId());
            stmt.executeUpdate();
        }
    }

    // Buscar cuenta por número
    public Optional<Account> findByAccountNumber(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM cuenta WHERE numero_cuenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(
                        rs.getInt("id"),
                        rs.getString("numero_cuenta"),
                        rs.getDouble("saldo"),
                        AccountType.valueOf(rs.getString("tipo")),
                        rs.getInt("cliente_id")
                );
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }

    // Actualizar saldo
    public void updateBalance(String accountNumber, double newBalance) throws SQLException {
        String sql = "UPDATE cuenta SET saldo = ? WHERE numero_cuenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        }
    }
}
