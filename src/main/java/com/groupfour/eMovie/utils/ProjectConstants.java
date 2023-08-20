package com.groupfour.eMovie.utils;

public class ProjectConstants {
    public static final int CODE_DIGITS = 6;

    // redis key
    public static final String REDIS_LOGIN_KEY_PREFIX = "emovie:login:code:";
    public static final String REDIS_MOVIE_KEY_PREFIX = "emovie:movie:id:";

    // redis TTL
    public static final Long REDIS_LOGIN_TTL = 5L;
    public static final Long REDIS_MOVIE_TTL = 30L;
    public static final Long REDIS_NULL_TTL = 2L;

    // status
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
}
