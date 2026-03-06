package com.tutorialninja.forms;

import org.openqa.selenium.By;

import java.util.LinkedHashMap;
import java.util.Map;

public class RegistrationForm implements FormPage {

    private final Map<String, By> fields = new LinkedHashMap<>();
    private final Map<String, By> errors = new LinkedHashMap<>();
    private final By submitButton = By.cssSelector("input[value='Continue']");
    private final By privacyPolicyCheckbox = By.name("agree");
    private final By pageWarning = By.cssSelector("div.alert.alert-danger");

    public RegistrationForm() {
        // Fields
        fields.put("firstName", By.id("input-firstname"));
        fields.put("lastName", By.id("input-lastname"));
        fields.put("email", By.id("input-email"));
        fields.put("telephone", By.id("input-telephone"));
        fields.put("password", By.id("input-password"));
        fields.put("passwordConfirm", By.id("input-confirm"));

        // Error messages (the red text next to each field)
        errors.put("firstName", By.cssSelector("#input-firstname + div"));
        errors.put("lastName", By.cssSelector("#input-lastname + div"));
        errors.put("email", By.cssSelector("#input-email + div"));
        errors.put("telephone", By.cssSelector("#input-telephone + div"));
        errors.put("password", By.cssSelector("#input-password + div"));
        errors.put("passwordConfirm", By.cssSelector("#input-confirm + div"));
    }

    @Override
    public Map<String, By> getFields()      { return fields; }

    @Override
    public By getSubmitButton()             { return submitButton; }

    public Map<String, By> getErrors()      { return errors; }
    public By getPrivacyPolicyCheckbox()    { return privacyPolicyCheckbox; }
    public By getPageWarning()              { return pageWarning; }
}
