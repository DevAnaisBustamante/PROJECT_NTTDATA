import domain.*;
import repository.*;
import service.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bankingBusiness_db";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            IClientRepository clientRepo = new ClientRepository(conn);
            IAccountRepository accountRepo = new AccountRepository(conn);

            IBankService bankService = new BankService(clientRepo, accountRepo);

            // 1. Registrar cliente
            Client client = new Client("Anais", "Bustamante", "12345679", "anais@mail.com");
            bankService.registerClient(client);

            // 2. Abrir cuenta
            Account account = new Account("ACC3001", 0.0, AccountType.SAVINGS, 1);
            bankService.openAccount(account);

            // 3. Depositar
            bankService.deposit("ACC3001", 500);
            System.out.println("Saldo después de depósito: " + bankService.getBalance("ACC3001"));

            // 4. Retirar
            bankService.withdraw("ACC3001", 200);
            System.out.println("Saldo después de retiro: " + bankService.getBalance("ACC3001"));

            //5. Consultar Saldo
            bankService.getBalance("ACC3001");

            // 6. Listar cuentas por cliente con Stream
            System.out.println("Cuentas del cliente con DNI 12345679:");
            bankService.getAccountsByClient("12345679")
                    .forEach(acc -> System.out.println(
                            acc.getAccountNumber() + " | Saldo: " + acc.getBalance()
                    ));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
