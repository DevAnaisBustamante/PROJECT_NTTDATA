package repository;

import domain.Client;
import java.sql.SQLException;
import java.util.Optional;

public interface IClientRepository {
    void save(Client client) throws SQLException;
    Optional<Client> findByDni(String dni) throws SQLException;
}