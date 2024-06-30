package com.samuel.security.demo2.app;

import java.security.SecureRandom;
import java.util.Base64;

public class SecreteKeyGenerator {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom() ; //256 bits
        byte[] key = new byte[32];

        secureRandom.nextBytes(key);

        String encodedKey = Base64.getEncoder().encodeToString(key);
        System.out.println("key: "+ encodedKey);
    }
}
