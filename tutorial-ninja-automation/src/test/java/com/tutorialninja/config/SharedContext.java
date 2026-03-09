package com.tutorialninja.config;

import org.openqa.selenium.WebDriver;

public class SharedContext {

    private WebDriver driver;
    private String registeredEmail;
    private String registeredPassword;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setCredentials(String email, String password) {
        this.registeredEmail = email;
        this.registeredPassword = password;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }
}
