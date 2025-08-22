public class Main {
    public static void main(String[] args) {
        BankRepository bank = new BankRepository();

        // 1. Registrar Cliente
        System.out.println("1. Registrar Cliente");
        Client client = new Client("Anais", "Bustamante", "71205648", "anais@mail.com");
        bank.addClient(client);
        bank.getClient(client.getDni());

        // 2. Abrir Cuenta
        System.out.println("2.1. Abrir Cuenta tipo SAVINGS");
        Account accountSaving = new Account(AccountNumberGenerator.generate(), AccountType.SAVINGS);
        bank.addAccount("71205648", accountSaving);

        System.out.println("2.2. Abrir Cuenta tipo CURRENT");
        Account accountCurrent = new Account(AccountNumberGenerator.generate(), AccountType.CURRENT);
        bank.addAccount("71205648", accountCurrent);

        // 3. Depositar
        System.out.println("3. Depositar");
        accountSaving.deposit(1000);
        System.out.println("Saldo tras depósito: " + accountSaving.getBalance());
        accountCurrent.deposit(200);
        System.out.println("Saldo tras depósito: " + accountCurrent.getBalance());

        // 4. Retirar
        System.out.println("4.1. Retirar en cuenta SAVINGS");
        accountSaving.withdraw(500);
        System.out.println("Saldo tras retiro: " + accountSaving.getBalance());

        System.out.println("4.2. Retirar en cuenta CURRENT");
        accountCurrent.withdraw(400);
        System.out.println("Saldo tras retiro: " + accountCurrent.getBalance());

        // 5. Consultar Saldo
        System.out.println("5. Consultar Saldo");
        bank.getAccount(accountSaving.getAccountNumber())
                .ifPresent(acc -> System.out.println("Saldo actual en la cuenta tipo SAVING: " + acc.getBalance()));
        bank.getAccount(accountCurrent.getAccountNumber())
                .ifPresent(acc2 -> System.out.println("Saldo actual en la cuenta tipo CURRENT: " + acc2.getBalance()));

        // 6. Uso de Streams para filtrar cuentas
        System.out.println("6. Uso de Streams para filtrar cuentas");
        System.out.println("\nCuentas de tipo CURRENT:");
        bank.getAccountsByType(client.getDni(), AccountType.CURRENT)
                .forEach(System.out::println);
    }
}
