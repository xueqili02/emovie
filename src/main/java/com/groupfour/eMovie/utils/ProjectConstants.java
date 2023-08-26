package com.groupfour.eMovie.utils;

public class ProjectConstants {
    public static final int CODE_DIGITS = 6;

    // redis key
    public static final String REDIS_LOGIN_KEY_PREFIX = "emovie:login:code:";
    public static final String REDIS_MOVIE_KEY_PREFIX = "emovie:movie:id:";
    public static final String REDIS_LOCK_KEY_PREFIX = "emovie:lock:movie:id:";
    public static final String REDIS_INCR_KEY_PREFIX = "emovie:incr:";

    // redis TTL
    public static final Long REDIS_LOGIN_TTL = 5L;
    public static final Long REDIS_MOVIE_TTL = 30L;
    public static final Long REDIS_NULL_TTL = 2L;
    public static final Long REDIS_LOCK_TTL = 10L; // 10 seconds

    // status
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
}
