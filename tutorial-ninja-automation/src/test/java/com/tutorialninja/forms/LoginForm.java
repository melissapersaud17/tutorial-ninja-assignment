package com.tutorialninja.forms;

import org.openqa.selenium.By;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginForm implements FormPage {

    private final Map<String, By> fields = new LinkedHashMap<>();
    private final By submitButton = By.cssSelector("input[value='Login']");
    private final By pageWarning = By.cssSelector("div.alert.alert-danger");

    public LoginForm() {
        fields.put("email", By.id("input-email"));
        fields.put("password", By.id("input-password"));
    }

    @Override
    public Map<String, By> getFields()  { return fields; }

    @Override
    public By getSubmitButton()         { return submitButton; }
    public By getPageWarning()          { return pageWarning; }
}
