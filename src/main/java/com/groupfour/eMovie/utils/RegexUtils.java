package com.groupfour.eMovie.utils;

import java.util.regex.Pattern;

public class RegexUtils {
    public static boolean EmailValid(String email) {
        if (email == null || email.equals("")) {
            return false;
        }
        String pattern = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        return Pattern.matches(pattern, email);
    }
}
