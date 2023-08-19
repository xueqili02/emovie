package com.groupfour.eMovie.utils;

import com.google.gson.Gson;

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static <T> T fromJson(String objectJson, Class<T> clazz) {
        return gson.fromJson(objectJson, clazz);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
