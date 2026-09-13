Feature: Validate the user registration into Parabank application

  @TC_REG_001 @smoke
  Scenario: Verify registration with all valid details
    Given the user is on the Parabank application homepage
    When the user clicks on the register link
    And the user enters the following registration details:
      | firstName       | Sponge     |
      | lastName        | Bob        |
      | address         | 143 St     |
      | city            | Austin     |
      | state           | Texas      |
      | zipCode         | 123456     |
      | phone           | 1234567890 |
      | ssn             | 1234567    |
      | username        | sponge     |
      | password        | sponge_123 |
      | confirmPassword | sponge_123 |
    And user clicks on the Register button
    Then the account should be created successfully with a confirmation message "Your account was created successfully. You are now logged in."
    And the user should see the welcome message reading "Welcome Sponge Bob"

  @TC_REG_002 @regression
  Scenario: Verify registration with duplicate username
    Given the user is on the Parabank application homepage
    When the user clicks on the register link
    And the user enters the following registration details:
      | firstName       | Sponge     |
      | lastName        | Bob        |
      | address         | 143 St     |
      | city            | Austin     |
      | state           | Texas      |
      | zipCode         | 123456     |
      | phone           | 1234567890 |
      | ssn             | 1234567    |
      | username        | sponge     |
      | password        | sponge_123 |
      | confirmPassword | sponge_123 |
    And user clicks on the Register button
    Then the account should be created successfully with a confirmation message "Your account was created successfully. You are now logged in."
    When the user clicks the log out link
    And the user clicks on the register link
    And the user attempts to register with the same username again
    And user clicks on the Register button
    Then the user should see an error message "This username already exists." near the username field

  @TC_REG_003 @regression
  Scenario: Verify registration with mismatched passwords
    Given the user is on the Parabank application homepage
    When the user clicks on the register link
    And the user enters the following registration details:
      | firstName       | Sponge1    |
      | lastName        | Bob1       |
      | address         | 143 St     |
      | city            | Austin     |
      | state           | Texas      |
      | zipCode         | 123456     |
      | phone           | 1234567890 |
      | ssn             | 1234567    |
      | username        | sponge     |
      | password        | sponge_123 |
      | confirmPassword | sponge_456 |
    And user clicks on the Register button
    Then the user should see an error message "Passwords did not match." near the confirm password field

  @TC_REG_004 @regression
  Scenario: Verify mandatory field validation on empty submission
    Given the user is on the Parabank application homepage
    When the user clicks on the register link
    And user leaves all the input fields empty
    And user clicks on the Register button
    Then the user should see required validation message near all the mandatory fields
