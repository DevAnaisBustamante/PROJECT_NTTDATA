package domain;

import java.util.UUID;

public class AccountNumberGenerator {
    // Se usa UUID para garantizar que no se repitan
    public static String generate() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
