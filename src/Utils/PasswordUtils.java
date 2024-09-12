package Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordUtils {

    public static String hashPassword(String password) {
        byte[] salt = new byte[16]; // Note: This salt should be the same every time for this to work, which is not secure.
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing a password: " + e.getMessage(), e);
        }
    }

    // This method checks the provided password against the stored hash using the same static salt.
    public static boolean checkPassword(String providedPassword, String storedHash) {
        // The salt must be the same used when the stored hash was created.
        byte[] salt = new byte[16]; // Again, using a static salt here for demonstration; not secure.
        KeySpec spec = new PBEKeySpec(providedPassword.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            String computedHash = Base64.getEncoder().encodeToString(hash);
            return computedHash.equals(storedHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while checking a password: " + e.getMessage(), e);
        }
    }
}

