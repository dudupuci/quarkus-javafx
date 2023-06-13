package org.acme.javafx.interfaces;

import java.util.Random;

public interface SecretKeyGen {
    static String generateSecretKey() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        var characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
