package com.tutorialninja.steps;

import com.tutorialninja.config.SharedContext;
import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;
import com.tutorialninja.pages.AccountSuccessPage;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.validators.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationSteps {

    private final SharedContext context;
    private GenericFormWrapper<RegistrationForm> registrationWrapper;
    private HomePage homePage;
    private AccountSuccessPage accountSuccessPage;
    private Map<String, String> lastFormData;

    private final List<FieldValidator> fieldValidators = List.of(
            new LengthBoundValidator("firstName", "First Name", 1, 32, LengthBoundValidator.ViolationType.ABOVE_MAX),
            new LengthBoundValidator("lastName", "Last Name", 1, 32, LengthBoundValidator.ViolationType.ABOVE_MAX),
            new EmailValidator(),
            new LengthBoundValidator("telephone", "Telephone", 3, 32, LengthBoundValidator.ViolationType.ABOVE_MAX),
            new LengthBoundValidator("password", "Password", 4, 20, LengthBoundValidator.ViolationType.BELOW_MIN)
    );

    public RegistrationSteps(SharedContext context) {
        this.context = context;
    }

    @Given("I have registered a new account")
    public void iHaveRegisteredANewAccount() {
        WebDriver driver = context.getDriver();
        homePage = new HomePage(driver);
        registrationWrapper = new GenericFormWrapper<>(driver, new RegistrationForm());
        accountSuccessPage = new AccountSuccessPage(driver);
        homePage.navigateToRegistration();

        String email = "testuser_" + System.currentTimeMillis() + "@test.com";
        String password = "Test1234";
        context.setCredentials(email, password);

        registrationWrapper.fillAllFields(Map.of(
                "firstName", "John",
                "lastName", "Doe",
                "email", email,
                "telephone", "1234567890",
                "password", password,
                "passwordConfirm", password
        ));
        registrationWrapper.click(registrationWrapper.getForm().getPrivacyPolicyCheckbox());
        registrationWrapper.submit();
        accountSuccessPage.waitUntilLoaded();
        driver.manage().deleteAllCookies();
    }

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        WebDriver driver = context.getDriver();
        homePage = new HomePage(driver);
        registrationWrapper = new GenericFormWrapper<>(driver, new RegistrationForm());
        accountSuccessPage = new AccountSuccessPage(driver);
        homePage.navigateToRegistration();
    }

    @When("I fill out the registration form with the following details:")
    public void iFillOutTheRegistrationFormWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> data = new HashMap<>(dataTable.asMap());
        if ("<generated>".equals(data.get("email"))) {
            data.put("email", "testuser_" + System.currentTimeMillis() + "@test.com");
        }
        lastFormData = data;
        registrationWrapper.fillAllFields(data);
    }

    @When("I agree to the privacy policy")
    public void iAgreeToThePrivacyPolicy() {
        registrationWrapper.click(registrationWrapper.getForm().getPrivacyPolicyCheckbox());
    }

    @When("I click the registration submit button")
    public void iClickTheRegistrationSubmitButton() {
        registrationWrapper.submit();
    }

    @When("I register again with the same details")
    public void iRegisterAgainWithTheSameDetails() {
        WebDriver driver = context.getDriver();
        accountSuccessPage.waitUntilLoaded();
        driver.manage().deleteAllCookies();
        homePage.navigateToRegistration();
        registrationWrapper = new GenericFormWrapper<>(driver, new RegistrationForm());
        registrationWrapper.fillAllFields(lastFormData);
        registrationWrapper.click(registrationWrapper.getForm().getPrivacyPolicyCheckbox());
        registrationWrapper.submit();
    }

    @Then("I should see the duplicate email warning")
    public void iShouldSeeTheDuplicateEmailWarning() {
        String expected = "Warning: E-Mail Address is already registered!";
        String actual = registrationWrapper.getText(registrationWrapper.getForm().getPageWarning());
        assertTrue(actual.contains(expected),
                "Expected duplicate email warning: " + expected + " but got: " + actual);
    }

    @Then("I should see the account created confirmation page")
    public void iShouldSeeTheAccountCreatedConfirmationPage() {
        assertTrue(accountSuccessPage.isAccountCreated(),
                "Expected 'Your Account Has Been Created!' but got: "
                        + accountSuccessPage.getHeadingText());
        assertTrue(accountSuccessPage.isOnSuccessPage(),
                "Expected URL to contain 'route=account/success' but got: "
                        + accountSuccessPage.getCurrentUrl());
    }

    @When("I submit the empty registration form")
    public void iSubmitTheEmptyRegistrationForm() {
        registrationWrapper.submit();
    }

    @When("I submit the form with each field's invalid value")
    public void iSubmitTheFormWithEachFieldsInvalidValue() {
        fieldValidators.forEach(v -> registrationWrapper.fillField(v.getFieldName(), v.getInvalidValue(), v.needsMaxLengthRemoved()));
        registrationWrapper.submit();
    }

    @Then("each required field should display the correct error message")
    public void eachRequiredFieldShouldDisplayTheCorrectErrorMessage() {
        List<String> failures = fieldValidators.stream()
                .filter(v -> !v.getActualError(registrationWrapper).contains(v.getExpectedError()))
                .map(v -> v.getFieldName()
                        + " — Expected: " + v.getExpectedError()
                        + " | Got: " + v.getActualError(registrationWrapper))
                .toList();

        assertTrue(failures.isEmpty(),
                "The following field validations failed:\n" + String.join("\n", failures));
    }

    @Then("the page should display the privacy policy warning")
    public void thePageShouldDisplayThePrivacyPolicyWarning() {
        String expected = "Warning: You must agree to the Privacy Policy!";
        String actual = registrationWrapper.getText(
                registrationWrapper.getForm().getPageWarning()
        );

        System.out.println("Checking page warning: expected=\"" + expected
                + "\" actual=\"" + actual + "\"");

        assertTrue(actual.contains(expected),
                "Expected page warning: " + expected + " but got: " + actual);
    }

    @Then("I should see the password confirmation error message")
    public void iShouldSeeThePasswordConfirmationErrorMessage() {
        String expected = "Password confirmation does not match password!";
        String actual = registrationWrapper.getText(
                registrationWrapper.getForm().getErrors().get("passwordConfirm")
        );

        assertEquals(expected, actual,
                "Expected: " + expected + " but got: " + actual);
    }
}
