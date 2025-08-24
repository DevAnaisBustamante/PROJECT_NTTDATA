package repository;

import domain.Client;
import java.sql.*;
import java.util.Optional;

public class ClientRepository {
    private final Connection connection;

    public ClientRepository(Connection connection) {
        this.connection = connection;
    }

    // Registrar cliente
    public void save(Client client) throws SQLException {
        String sql = "INSERT INTO cliente (nombre, apellido, dni, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setString(3, client.getDni());
            stmt.setString(4, client.getEmail());
            stmt.executeUpdate();
        }
    }

    // Buscar cliente por DNI
    public Optional<Client> findByDni(String dni) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("email")
                );
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }
}
