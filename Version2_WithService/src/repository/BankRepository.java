package repository;

import domain.Account;
import domain.AccountType;
import domain.Client;

import java.util.*;
import java.util.stream.Collectors;

//Persistencia de los datos de clientes y cuentas en memoria
public class BankRepository {
    //Uso de HashMap para simular una BD
    private Map<String, Client> clients = new HashMap<>(); // dni como clave
    private Map<String, Account> accounts = new HashMap<>(); // numeroCuenta como clave

    // Registrar un cliente nuevo
    public void addClient(Client client) {
        // Validar que el DNI sea único
        if (clients.containsKey(client.getDni())) {
            throw new IllegalArgumentException("DNI already exists");
        }
        clients.put(client.getDni(), client);
        System.out.println("Cliente creada: " + client.getFirstName());
    }

    public void addAccount(String dni, Account account) {
        Client client = clients.get(dni);
        // Validar que el cliente exista
        if (client == null) throw new IllegalArgumentException("Client does not exist");
        // Validar que el número de cuenta sea único
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }
        // Asociar una nueva cuenta a un cliente existente
        client.addAccount(account);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("N° de Cuenta: " + account.getAccountNumber());
    }

    //Uso de Optional
    // Buscar un cliente por DNI
    public Optional<Client> getClient(String dni) {
        return Optional.ofNullable(clients.get(dni));
    }

    // Buscar una cuenta por número de cuenta
    public Optional<Account> getAccount(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    // Uso de Streams
    // Filtrar cuentas por tipo
    public List<Account> getAccountsByType(String dni, AccountType type) {
        return getClient(dni)
                .map(client -> client.getAccounts().stream()
                        .filter(acc -> acc.getType() == type)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
