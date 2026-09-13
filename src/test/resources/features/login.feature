Feature: Validate the user login into Parabank application

  @TC_LOG_001 @login @smoke
  Scenario: Verify login with valid credentials
    Given the user is on the Parabank application homepage
    When the user enters the username "valid" and password "valid" and logs in
    Then the user is redirected to "Accounts Overview" page
    And the user should see the welcome message reading "valid"

  @TC_LOG_002 @login @regression
  Scenario: Verify login with invalid username
    Given the user is on the Parabank application homepage
    When the user enters the username "invalidUser999" and password "valid" and logs in
    Then the user should see the error message reading "The username and password could not be verified."

  @TC_LOG_003 @login @regression
  Scenario: Verify login with invalid password
    Given the user is on the Parabank application homepage
    When the user enters the username "valid" and password "wrongPassword" and logs in
    Then the user should see the error message reading "The username and password could not be verified."

  @TC_LOG_004 @login @regression
  Scenario: Verify login with empty username and password
    Given the user is on the Parabank application homepage
    When the user enters the username "" and password "" and logs in
    Then the user should see the error message reading "Please enter a username and password."

  @TC_LOG_005 @login @regression
  Scenario: Verify logout functionality
    Given the user is on the Parabank application homepage
    When the user enters the username "valid" and password "valid" and logs in
    Then the user is redirected to "Accounts Overview" page
    And the user clicks the log out link
    Then the user is redirected to "Home" page

  @TC_LOG_006 @login @regression
  Scenario: Verify direct Accounts Overview URL access while logged out
    Given the user is on the Parabank application homepage and has no active session
    When the user navigates to the Accounts overview directly without logging in
    Then the user should see the error message reading "An internal error has occurred and has been logged."
