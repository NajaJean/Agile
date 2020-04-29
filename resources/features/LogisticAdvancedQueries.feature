@tag
Feature: Logistic Company, Advanced queries 
  The logistic company is able too see advanced statistics on the system

  @tag1
  Scenario: Logistic Company, Advanced queries 
    Given A logistic company has preloaded data from the database
    When advanced statistics want to be calculated
    Then these are validated for the company

