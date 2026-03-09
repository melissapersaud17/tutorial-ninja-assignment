package com.tutorialninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountSuccessPage extends BasePage {

    private final By successHeading = By.cssSelector("div#content h1");

    public AccountSuccessPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("route=account/success"));
    }

    public String getHeadingText() {
        return getText(successHeading);
    }

    public boolean isAccountCreated() {
        return getHeadingText().contains("Your Account Has Been Created!");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOnSuccessPage() {
        return getCurrentUrl().contains("route=account/success");
    }
}
