package com.tutorialninja.steps;

import com.tutorialninja.config.Hooks;
import com.tutorialninja.config.SharedContext;
import com.tutorialninja.forms.GenericFormWrapper;
import com.tutorialninja.forms.LoginForm;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.pages.MyAccountPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final WebDriver driver = Hooks.getDriver();
    private GenericFormWrapper<LoginForm> loginWrapper;

    private MyAccountPage myAccountPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        HomePage homePage = new HomePage(driver);
        loginWrapper = new GenericFormWrapper<>(driver, new LoginForm());
        myAccountPage = new MyAccountPage(driver);
        homePage.navigateToLoginPage();
    }

    @When("I log in with the registered credentials")
    public void iLogInWithTheRegisteredCredentials() {
        loginWrapper.fillAllFields(Map.of(
                "email", SharedContext.getRegisteredEmail(),
                "password", SharedContext.getRegisteredPassword()
        ));
    }

    @When("I enter invalid credentials")
    public void iEnterInvalidCredentials() {
        loginWrapper.fillAllFields(Map.of(
                "email", "fakeemail1@doesnotexist.com",
                "password", "WrongPassword123"
        ));
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginWrapper.submit();
    }

    @Then("I should see the login error message")
    public void iShouldSeeTheLoginErrorMessage() {
        String expected = "Warning: No match for E-Mail Address and/or Password.";
        String actual = loginWrapper.getText(loginWrapper.getForm().getPageWarning());
        assertTrue(actual.contains(expected),
                "Expected: " + expected + " but got: " + actual);
    }

    @Then("I should see the My Account page")
    public void iShouldSeeTheMyAccountPage() {
        assertTrue(myAccountPage.isOnMyAccountPage(),
                "Expected to see 'My Account' heading but got: "
                        + myAccountPage.getHeadingText());
    }
}
