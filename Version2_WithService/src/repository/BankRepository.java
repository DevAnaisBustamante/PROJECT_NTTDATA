package repository;

import domain.Account;
import domain.AccountType;
import domain.Client;

import java.util.*;
import java.util.stream.Collectors;

public class BankRepository implements IBankRepository {
    private Map<String, Client> clients = new HashMap<>();
    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public void addClient(Client client) {
        if (clients.containsKey(client.getDni())) {
            throw new IllegalArgumentException("DNI already exists");
        }
        clients.put(client.getDni(), client);
        System.out.println("Cliente creado: " + client.getFirstName());
    }

    @Override
    public void addAccount(String dni, Account account) {
        Client client = clients.get(dni);
        if (client == null) throw new IllegalArgumentException("Client does not exist");
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }
        client.addAccount(account);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("N° de Cuenta creada: " + account.getAccountNumber());
    }

    @Override
    public Optional<Client> getClient(String dni) {
        return Optional.ofNullable(clients.get(dni));
    }

    @Override
    public Optional<Account> getAccount(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    @Override
    public List<Account> getAccountsByType(String dni, AccountType type) {
        return getClient(dni)
                .map(client -> client.getAccounts().stream()
                        .filter(acc -> acc.getType() == type)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
