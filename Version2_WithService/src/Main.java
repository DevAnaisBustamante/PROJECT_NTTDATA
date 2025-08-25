import domain.AccountType;
import repository.BankRepository;
import repository.IBankRepository;
import service.BankService;
import service.IBankService;

public class Main {
    public static void main(String[] args) {
        IBankRepository repository = new BankRepository();
        IBankService service = new BankService(repository);

        System.out.println("1. Registrar Client");
        service.registerClient("71205648", "Anais", "Bustamante", "anais@mail.com");

        System.out.println("2. Abrir Cuentas");
        String savingsAcc = service.openAccount("71205648", AccountType.SAVINGS);
        String currentAcc = service.openAccount("71205648", AccountType.CURRENT);

        System.out.println("3. Depositar");
        service.deposit(savingsAcc, 1000);
        service.deposit(currentAcc, 200);
        System.out.println("Saldo Ahorros: " + service.checkBalance(savingsAcc));
        System.out.println("Saldo Corriente: " + service.checkBalance(currentAcc));

        System.out.println("4. Retirar");
        service.withdraw(savingsAcc, 500);
        service.withdraw(currentAcc, 400);

        System.out.println("5. Consultar Saldo");
        System.out.println("Saldo Ahorros: " + service.checkBalance(savingsAcc));
        System.out.println("Saldo Corriente: " + service.checkBalance(currentAcc));

        System.out.println("6. Filtrar cuenta por tipo");
        service.getAccountsByType("71205648", AccountType.CURRENT)
                .forEach(System.out::println);
    }
}
