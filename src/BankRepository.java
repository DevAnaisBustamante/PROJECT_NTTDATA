import java.util.*;
import java.util.stream.Collectors;

//Persistencia de los datos de clientes y cuentas en memoria
public class BankRepository {
    private Map<String, Client> clients = new HashMap<>(); // dni como clave
    private Map<String, Account> accounts = new HashMap<>(); // numeroCuenta como clave

    public void addClient(Client client) {
        if (clients.containsKey(client.getDni())) {
            throw new IllegalArgumentException("DNI already exists");
        }
        clients.put(client.getDni(), client);
        System.out.println("Cliente creada: " + client.getName());
    }

    public void addAccount(String dni, Account account) {
        Client client = clients.get(dni);
        if (client == null) throw new IllegalArgumentException("Client does not exist");

        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }

        client.addAccount(account);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("N° de Cuenta: " + account.getAccountNumber());
    }

    // ✅ Uso de Optional para búsquedas
    public Optional<Client> getClient(String dni) {
        return Optional.ofNullable(clients.get(dni));
    }

    public Optional<Account> getAccount(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    // ✅ Uso de Streams para filtrar cuentas por tipo
    public List<Account> getAccountsByType(String dni, AccountType type) {
        return getClient(dni)
                .map(client -> client.getAccounts().stream()
                        .filter(acc -> acc.getType() == type)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
