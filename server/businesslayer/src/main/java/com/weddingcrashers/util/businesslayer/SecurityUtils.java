package com.weddingcrashers.util.businesslayer;

import com.weddingcrashers.model.User;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.UserService;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 *
 * @author dmpliamplias
 */
public final class SecurityUtils {

    // ---- Constructor

    private SecurityUtils() {
        // nop
    }


    // ---- Methods

    /**
     *
     *
     * @param user
     * @param typedPassword
     * @return
     */
    public static boolean validatePassword(User user, String typedPassword) {
        final UserService userService = ServiceLocator.getServiceLocator().getUserService();
        final String storedPassword = userService.getPasswordFor(user);
        final String[] parts = storedPassword.split(":");
        final int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        final PBEKeySpec spec = new PBEKeySpec(typedPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }
        catch (NoSuchAlgorithmException e) {
            ServiceLocator.getLogger().warning(e.getMessage());
        }
        byte[] testHash = new byte[0];
        try {
            if (skf != null) {
                testHash = skf.generateSecret(spec).getEncoded();
            }
        }
        catch (InvalidKeySpecException e) {
            ServiceLocator.getLogger().warning(e.getMessage());
        }

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * Converts hex to bytes.
     *
     * @param hex the hex to convert.
     * @return the bytes converted from hex.
     */
    private static byte[] fromHex(String hex) {
        final byte[] bytes = new byte [hex.length() / 2];
        for (int i = 0; i < bytes.length ; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

}
