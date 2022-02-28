package com.example.provasuficienciaprogramacaoweb2;

public class AbstractConstantsApp {

    AbstractConstantsApp() {
    }

    public static final String API_URI = "/api/v1";
    public static final String ORIGIN = "*";

    private static final String[] AUTHENTICATED_ANT_MATCHERS = {
            API_URI + "/**",
    };

    private static final String[] PUBLIC_ANT_MATCHERS = {};

    public static String[] getPublicAntMatchers() {
        return PUBLIC_ANT_MATCHERS.clone();
    }

    public static String[] getAuthenticatedAntMatchers() {
        return AUTHENTICATED_ANT_MATCHERS.clone();
    }

}