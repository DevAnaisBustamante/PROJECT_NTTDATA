import java.util.UUID;

public class AccountNumberGenerator {
    public static String generate() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
