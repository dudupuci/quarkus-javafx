package org.acme.javafx.interfaces;

import java.util.Random;

public interface AccountNumberGen {

    static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            var n = random.nextInt(9);
            builder.append(n);
        }

        int verifierDigit = random.nextInt(9);
        builder.append("-").append(verifierDigit);
        return builder.toString();
    }


}
