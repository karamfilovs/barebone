@test
Feature: Project X tests

  Scenario: Can search in the website
    Given user is at home page
    When I search for text:"Софтуерно Тестване"
    Then posts should be visible



