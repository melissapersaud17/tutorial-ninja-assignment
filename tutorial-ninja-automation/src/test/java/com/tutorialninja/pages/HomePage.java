package com.tutorialninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String BASE_URL = "https://tutorialsninja.com/demo/index.php";
    private static final String REGISTRATION_URL = BASE_URL + "?route=account/register";
    private static final String LOGIN_URL = BASE_URL + "?route=account/login";

    private final By myAccountDropdown = By.cssSelector("a[title='My Account']");
    private final By registerLink = By.linkText("Register");
    private final By loginLink = By.linkText("Login");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get(BASE_URL);
    }

    public void navigateToRegistration() {
        driver.get(REGISTRATION_URL);
    }

    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
    }

    public void clickMyAccount() {
        click(myAccountDropdown);
    }

    public void clickRegister() {
        clickMyAccount();
        click(registerLink);
    }

    public void clickLogin() {
        clickMyAccount();
        click(loginLink);
    }
}
