package com.weddingcrashers.util.datalayer;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static java.util.logging.Logger.getLogger;

public class SecurityUtils {

    // ---- Statics

    /** The algorithm. */
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";



    // ---- Methods

    /**
     * Generates an secure password with the PBKDF2 with HMACSHA1 algorithm.
     *
     * @param password the password to hash.
     * @return the secured password.
     */
    public static String generatePBKDF2WithHMACSHA1Password(String password) {
        final int iterations = 1000;
        final char[] chars = password.toCharArray();
        final byte[] salt = getSalt();

        final PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = null;
        try {
            if (skf != null) {
                hash = skf.generateSecret(spec).getEncoded();
            }
        } catch (InvalidKeySpecException e) {
            getLogger(SecurityUtils.class.getSimpleName()).warning(e.getMessage());
        }
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * @return the salt key.
     */
    private static byte[] getSalt() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            getLogger(SecurityUtils.class.getSimpleName()).warning(e.getMessage());
        }
        final byte[] salt = new byte[16];
        if (sr != null) {
            sr.nextBytes(salt);
        }
        return salt;
    }

    /**
     * Returns the bytes to hex.
     *
     * @param array the array to convert.
     * @return the bytes to hex.
     */
    private static String toHex(byte[] array) {
        final BigInteger bi = new BigInteger(1, array);
        final String hex = bi.toString(16);
        final int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }
        else {
            return hex;
        }
    }

}
