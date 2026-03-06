Feature: Registration

  Scenario: Successful registration with valid data
    Given I am on the registration page
    When I fill out the registration form with the following details:
      | firstName       | John       |
      | lastName        | Doe        |
      | email           | <generated> |
      | telephone       | 1234567890 |
      | password        | Test1234   |
      | passwordConfirm | Test1234   |
    And I agree to the privacy policy
    And I click the registration submit button
    Then I should see the account created confirmation page

  Scenario: Registration fails when email is already registered
    Given I am on the registration page
    When I fill out the registration form with the following details:
      | firstName       | John       |
      | lastName        | Doe        |
      | email           | <generated> |
      | telephone       | 1234567890 |
      | password        | Test1234   |
      | passwordConfirm | Test1234   |
    And I agree to the privacy policy
    And I click the registration submit button
    And I register again with the same details
    Then I should see the duplicate email warning

  Scenario: Field errors are displayed when form is submitted with values exceeding maximum length
    Given I am on the registration page
    When I submit the form with each field's invalid value
    Then each required field should display the correct error message

  Scenario: All field validation errors are displayed on empty form submission
    Given I am on the registration page
    When I submit the empty registration form
    Then each required field should display the correct error message
    And the page should display the privacy policy warning


  Scenario: Password confirmation mismatch error is displayed
    Given I am on the registration page
    When I fill out the registration form with the following details:
      | firstName       | John              |
      | lastName        | Doe               |
      | email           | <generated>       |
      | telephone       | 1234567890        |
      | password        | Test1234          |
      | passwordConfirm | DifferentPassword |
    And I agree to the privacy policy
    And I click the registration submit button
    Then I should see the password confirmation error message
