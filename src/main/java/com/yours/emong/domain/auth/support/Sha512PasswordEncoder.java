package com.yours.emong.domain.auth.support;

import com.yours.emong.global.error.InternalServerException;

import java.security.MessageDigest;
public class Sha512PasswordEncoder {

    private Sha512PasswordEncoder() {}

    public static String encode(String rawPassword) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(rawPassword.getBytes());

            for (byte byteDatum : md.digest()) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    public static boolean matches(String rawPassword, String encryptedPassword) {
        return encryptedPassword.equals(encode(rawPassword));
    }
}
