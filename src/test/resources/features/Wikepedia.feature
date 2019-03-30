@wiki
Feature: Wikipedia search language
  As a wikipedia user
  I want to be able to change the search language
  So that I can view information in various languages

  Scenario Outline: Wikipedia demo scenario
    Given User is on the wikipedia home page
    When User searches for <term>
    Then User should see search results containing heading <term>
    Examples:
      | term |
      | Pune |
