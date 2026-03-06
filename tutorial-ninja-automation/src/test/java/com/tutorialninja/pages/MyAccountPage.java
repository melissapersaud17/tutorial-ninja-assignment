package com.tutorialninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    private final By pageHeading = By.cssSelector("div#content h2");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadingText() {
        return getText(pageHeading);
    }

    public boolean isOnMyAccountPage() {
        return getHeadingText().contains("My Account");
    }
}
