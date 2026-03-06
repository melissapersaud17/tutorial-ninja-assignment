Feature: Login

  Scenario: Successful login with valid credentials
    Given I have registered a new account
    And I am on the login page
    When I log in with the registered credentials
    And I click the login button
    Then I should see the My Account page

  Scenario: Unsuccessful login with invalid credentials
    Given I am on the login page
    When I enter invalid credentials
    And I click the login button
    Then I should see the login error message


