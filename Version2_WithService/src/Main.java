import domain.AccountType;
import repository.BankRepository;
import service.BankService;

public class Main {
    public static void main(String[] args) {
        BankRepository repository = new BankRepository();
        BankService service = new BankService(repository);

        // 1. Registrar Cliente
        System.out.println("1. Registrar domain.Client");
        service.registerClient("71205648", "Anais", "Bustamante", "anais@mail.com");

        // 2. Abrir Cuenta
        System.out.println("2. Abrir Cuentas");
        String savingsAcc = service.openAccount("71205648", AccountType.SAVINGS);
        String currentAcc = service.openAccount("71205648", AccountType.CURRENT);

        // 3. Depositar
        System.out.println("3. Depositar");
        service.deposit(savingsAcc, 1000);
        service.deposit(currentAcc, 200);
        System.out.println("Saldo en cuenta de Ahorros: " + service.checkBalance(savingsAcc));
        System.out.println("Saldo en cuenta Corriente: " + service.checkBalance(currentAcc));

        // 4. Retirar
        System.out.println("4. Retirar");
        service.withdraw(savingsAcc, 500);
        service.withdraw(currentAcc, 400);

        // 5. Consultar Saldo
        System.out.println("5. Consultar Saldo");
        System.out.println("Saldo en cuenta de Ahorros: " + service.checkBalance(savingsAcc));
        System.out.println("Saldo en cuenta Corriente: " + service.checkBalance(currentAcc));

        // 6. Filtro de Cuenta por tipo
        System.out.println("6. Filter Accounts by Type");
        service.getAccountsByType("71205648", AccountType.CURRENT)
                .forEach(System.out::println);
    }
}
