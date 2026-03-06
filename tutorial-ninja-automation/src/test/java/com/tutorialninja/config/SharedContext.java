package com.tutorialninja.config;

public class SharedContext {

    private static String registeredEmail;
    private static String registeredPassword;

    public static void setCredentials(String email, String password) {
        registeredEmail = email;
        registeredPassword = password;
    }

    public static String getRegisteredEmail() {
        return registeredEmail;
    }

    public static String getRegisteredPassword() {
        return registeredPassword;
    }
}
