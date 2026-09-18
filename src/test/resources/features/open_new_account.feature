Feature: Validate the user can open new account in Parabank application

  @TC_OPA_001 @requiresLogin @smoke
  Scenario: Verify opening a new CHECKING account funded from an existing account, and confirm it appears in Accounts Overview
    Given the user is redirected to "Accounts Overview" page
    When the user creates a new "CHECKING" account for the customer using the API
    And the user clicks the "Open New Account" link from the side menu
    Then the user is redirected to "Open New Account" page
    And the user selects account type as "CHECKING" from the dropdown
    And the user selects account id from which the funds should be transferred
    And the user clicks "Open new Account" button
    Then the user should see the success message with newly opened Account's ID
    And the user notes the new accountId
    And the user clicks the "Accounts Overview" link from the side menu
    Then the user should see the newly created AccountID via UI in the Accounts Overview table

  @TC_OPA_002 @requiresLogin @smoke
  Scenario: Verify opening a new SAVINGS account funded from an existing account, and confirm it appears in Accounts Overview
    Given the user is redirected to "Accounts Overview" page
    When the user creates a new "SAVINGS" account for the customer using the API
    And the user clicks the "Open New Account" link from the side menu
    Then the user is redirected to "Open New Account" page
    And the user selects account type as "SAVINGS" from the dropdown
    And the user selects account id from which the funds should be transferred
    And the user clicks "Open new Account" button
    Then the user should see the success message with newly opened Account's ID
    And the user notes the new accountId
    And the user clicks the "Accounts Overview" link from the side menu
    Then the user should see the newly created AccountID via UI in the Accounts Overview table

  @TC_OPA_003 @requiresLogin @smoke
  Scenario: Verify opening a new account funded from an account with a negative balance is not blocked
    Given the user is redirected to "Accounts Overview" page
    When the user identifies the account with negative balance from Accounts Overview table
    And the user clicks the "Open New Account" link from the side menu
    Then the user is redirected to "Open New Account" page
    And the user selects account type as "CHECKING" from the dropdown
    And the user selects noted negative balance account id from which the funds should be transferred
    And the user clicks "Open new Account" button
    Then the user should see the success message with newly opened Account's ID
    And the user notes the new accountId
    And the user clicks the "Accounts Overview" link from the side menu
    Then the user should see the newly created AccountID via UI in the Accounts Overview table
