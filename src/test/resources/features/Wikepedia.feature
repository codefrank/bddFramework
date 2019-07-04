@wiki
Feature: Wikipedia search language
  As a wikipedia user
  I want to be able to search for a topic
  So that I can get information on that topic

  Scenario Outline: Wikipedia demo scenario
    Given User is on the wikipedia home page
    When User searches for <term>
    Then User should see search results containing heading <term>
    Examples:
      | term |
      | Pune |
