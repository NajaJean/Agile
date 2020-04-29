@tag
Feature: Client, Advanced queries 
  A client is able too see advanced statistics on their data

  @tag1
  Scenario: Client, Advanced queries 
    Given A client has data stored in the database
    When advanced statistics about their usage want to be calculated
    Then these are validated for the client
