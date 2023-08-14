package com.groupfour.eMovie.utils;

import java.util.Random;

public class RandomCodeUtils {
    public static String random(int digit) {
        if (digit <= 0 || digit > 8) {
            throw new IllegalArgumentException("Digits are not in the range of 1 - 8");
        }
        int rand = 1;
        for (int i = 0; i < digit; i++) {
            rand *= 10;
        }
        return String.format("%0" + digit + "d", new Random().nextInt(rand));
    }
}
