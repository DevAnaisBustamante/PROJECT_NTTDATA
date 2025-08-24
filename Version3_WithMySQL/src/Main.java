import domain.*;
import service.BankService;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bankingBusiness_db";
        String user = "root";
        String password = "tu_password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            BankService bankService = new BankService(conn);

            // 1. Registrar cliente
            Client client = new Client("Anais", "Bustamante", "12345678", "anais@mail.com");
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
