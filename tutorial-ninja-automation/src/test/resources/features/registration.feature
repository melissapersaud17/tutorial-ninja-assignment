Feature: Registration

  Scenario: Successful registration with valid data
    Given I am on the registration page
    When I fill out the registration form with valid data
    And I agree to the privacy policy
    And I click the registration submit button
    Then I should see the account created confirmation page

  Scenario: All field validation errors are displayed on empty form submission
    Given I am on the registration page
    When I submit the empty registration form
    Then each required field should display the correct error message
    And the page should display the privacy policy warning


  Scenario: Password confirmation mismatch error is displayed
    Given I am on the registration page
    When I enter mismatched passwords
    And I click the registration submit button
    Then I should see the password confirmation error message
