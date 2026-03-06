package com.tutorialninja.steps;

import com.tutorialninja.config.Hooks;
import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.RegistrationForm;
import com.tutorialninja.pages.AccountSuccessPage;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.validators.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationSteps {

    private final WebDriver driver = Hooks.getDriver();
    private GenericFormWrapper<RegistrationForm> registrationWrapper;
    private HomePage homePage;
    private AccountSuccessPage accountSuccessPage;

    // Field-level validators only
    private final List<FieldValidator> fieldValidators = List.of(
            new FirstNameValidator(),
            new LastNameValidator(),
            new EmailValidator(),
            new TelephoneValidator(),
            new PasswordValidator()
    );

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        homePage = new HomePage(driver);
        registrationWrapper = new GenericFormWrapper<>(driver, new RegistrationForm());
        accountSuccessPage = new AccountSuccessPage(driver);
        homePage.navigateTo();
        homePage.clickRegister();
    }

    @When("I fill out the registration form with valid data")
    public void iFillOutTheRegistrationFormWithValidData() {
        String uniqueEmail = "testuser_" + System.currentTimeMillis() + "@test.com";

        registrationWrapper.fillAllFields(Map.of(
                "firstName", "John",
                "lastName", "Doe",
                "email", uniqueEmail,
                "telephone", "1234567890",
                "password", "Test1234",
                "passwordConfirm", "Test1234"
        ));
    }

    @When("I agree to the privacy policy")
    public void iAgreeToThePrivacyPolicy() {
        registrationWrapper.click(registrationWrapper.getForm().getPrivacyPolicyCheckbox());
    }

    @When("I click the registration submit button")
    public void iClickTheRegistrationSubmitButton() {
        registrationWrapper.submit();
    }

    @Then("I should see the account created confirmation page")
    public void iShouldSeeTheAccountCreatedConfirmationPage() {
        assertTrue(accountSuccessPage.isAccountCreated(),
                "Expected 'Your Account Has Been Created!' but got: "
                        + accountSuccessPage.getHeadingText());
    }

    @When("I submit the empty registration form")
    public void iSubmitTheEmptyRegistrationForm() {
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

    @When("I enter mismatched passwords")
    public void iEnterMismatchedPasswords() {
        registrationWrapper.fillField("password", "Test1234");
        registrationWrapper.fillField("passwordConfirm", "DifferentPassword");
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
