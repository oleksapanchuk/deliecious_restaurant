package ua.deliciousrestaurant.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.Optional;

public final class PasswordCode {
    private static final Argon2 argon2 = Argon2Factory.create( Argon2Factory.Argon2Types.ARGON2id,32,64 );
    private static final int ITERATIONS = 10;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;

    public static Optional<String> encodePassword(String password) {
        try {
            return Optional.of(argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray()));
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }

    public static boolean verify(String hash, String password) {
        return argon2.verify(hash, password.toCharArray());
    }

}
