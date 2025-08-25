package domain;

import java.util.ArrayList;
import java.util.List;
// Clase de dominio que representa un Cliente del banco
public class Client {
    private String firstName; // Nombre del cliente
    private String lastName; // Apellido del cliente
    private String dni; // DNI del cliente
    private String email; // Correo electrónico validado
    private List<Account> accounts; // Lista de cuentas asociadas al cliente

    public Client(String firstName, String lastName, String dni, String email) {
        //Validaciones básicas de campos obligatorios
        if (firstName == null || lastName == null || dni == null || email == null) {
            throw new IllegalArgumentException("All fields are mandatory");
        }
        if (!email.contains("@")) throw new IllegalArgumentException("Invalid email");

        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    //Asociar una nueva cuenta al cliente
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public String getDni() { return dni; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public List<Account> getAccounts() { return accounts; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + dni + ")";
    }
}