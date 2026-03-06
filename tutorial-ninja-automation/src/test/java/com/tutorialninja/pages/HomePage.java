package com.tutorialninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String URL = "https://tutorialsninja.com/demo/index.php";

    private final By myAccountDropdown = By.cssSelector("a[title='My Account']");
    private final By registerLink = By.linkText("Register");
    private final By loginLink = By.linkText("Login");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get(URL);
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
