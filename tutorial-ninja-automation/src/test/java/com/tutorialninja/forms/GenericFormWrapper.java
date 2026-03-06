package com.tutorialninja.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class GenericFormWrapper<F extends FormPage> {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final F form;

    public GenericFormWrapper(WebDriver driver, F form) {
        this.driver = driver;
        this.form = form;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public F getForm() {
        return form;
    }

    public void fillField(String fieldName, String value) {
        By locator = form.getFields().get(fieldName);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void fillAllFields(Map<String, String> data) {
        data.forEach(this::fillField);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(form.getSubmitButton())).click();
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

}
