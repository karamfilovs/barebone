@test
Feature: EQS COCKPIT LOGIN

  Scenario Outline: Cant login with invalid credentials
    Given I am at the Login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I press Login button
    Then error message "Wrong log-in attempt" should be displayed
    And logout icon should not be displayed
    Examples:
      | username | password     |
      | alex     | not-existing |
      |          | 123456       |
      | alex     |              |
      |          |              |


  Scenario Outline: Can navigate back to Login page from bad login page
    Given I am at the Login page
    Then login form table should contain text "<note>"
    When I enter username "bad"
    And I enter password "bad"
    And I press Login button
    Then error message "Wrong log-in attempt" should be displayed
    When I click on link with text "EQS COCKPIT"
    Then login form table should contain text "<note>"
    Examples:
      | note                                                               |
      | Internet Explorer versions 6-9 are no longer supported in COCKPIT. |


  Scenario: Default language can be switched to German
    Given I am at the Login page
    Then login form table should contain text "Internet Explorer versions 6-9 are no longer supported in COCKPIT."
    When I switch language to German
    Then login form table should contain text "Die Internet Explorer Versionen 6-9 werden im COCKPIT"
    When I enter password "german"
    And I enter password "user"
    And I press Login button
    Then error message "fehlerhafter Anmeldeversuch" should be displayed
    When I click on lin with text "EQS COCKPIT"
    Then login form table should contain text "Die Internet Explorer Versionen 6-9 werden im COCKPIT"


  Scenario: 1 switch coverage of languages(ENG/DE)
    Given I am at the Login page
    Then login form table should contain text "Internet Explorer versions 6-9 are no longer supported in COCKPIT."
    When I switch language to German
    Then login form table should contain text "Die Internet Explorer Versionen 6-9 werden im COCKPIT"
    When I switch language to English
    Then login form table should contain text "Internet Explorer versions 6-9 are no longer supported in COCKPIT."















