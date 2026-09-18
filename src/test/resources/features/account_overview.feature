Feature: Validate the functionality Accounts Overview module

  @requiresLogin @TC_ACC_001 @smoke
  Scenario: Verify Accounts Overview displays the logged-in user's accounts with balance
    Given the user is redirected to "Accounts Overview" page
    Then the user should see the Accounts overview table with "Account", "Balance*" and "Available Amount" columns
    And the Accounts overview table should display at least one account row
    And every account row in the table should be populated with non-empty values

  @requiresLogin @TC_ACC_002 @regression
  Scenario: Verify clicking an account number navigates to that account's Account Activity page
    Given the user is redirected to "Accounts Overview" page
    When the user clicks on an account number link in the table
    Then the user is redirected to "Account Activity" page of the account id selected
    And the user should see the matching Account Number, Account Type, Balance, and Available amount in the Account activity page

  @requiresLogin @TC_ACC_003 @regression
  Scenario: Verify a newly created account (via API) appears in the Accounts Overview list
    Given the user is redirected to "Accounts Overview" page
    When the user creates a new "CHECKING" account for the customer using the API
    And the user refreshes the "Accounts Overview" page
    Then the user should see the newly created AccountID via API in the Accounts Overview table

  @requiresLogin @TC_ACC_004 @regression
  Scenario: Verify the displayed Total matches the sum of individual account balances
    Given the user is redirected to "Accounts Overview" page
    When the user adds individual account balances in the Accounts Overview table
    And read the total displayed in the Accounts Overview table
    Then the sum of all balances in table should be equal to the total displayed in the Accounts Overview table
