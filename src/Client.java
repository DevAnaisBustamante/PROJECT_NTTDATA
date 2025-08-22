import java.util.ArrayList;
import java.util.List;

public class Client {
    private String firstname;
    private String lastName;
    private String dni;
    private String email;
    private List<Account> accounts;

    public Client(String firstname, String lastName, String dni, String email) {
        if (firstname == null || lastName == null || dni == null || email == null) {
            throw new IllegalArgumentException("All fields are mandatory");
        }
        if (!email.contains("@")) throw new IllegalArgumentException("Invalid email");

        this.firstname = firstname;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getDni() { return dni; }
    public String getName() { return firstname; }
}