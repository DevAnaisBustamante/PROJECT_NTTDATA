package domain;

// Representa a un cliente del banco
public class Client {
    private int id;            // ID generado en BD
    private String firstName;  // Nombre
    private String lastName;   // Apellido
    private String dni;        // DNI único
    private String email;      // Email

    public Client(String firstName, String lastName, String dni, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
    }

    public Client(int id, String firstName, String lastName, String dni, String email) {
        this(firstName, lastName, dni, email);
        this.id = id;
    }

    // Getters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }
}
