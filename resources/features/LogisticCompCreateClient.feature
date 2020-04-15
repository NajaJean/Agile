
Feature: Logistics Company, Register new client
  Logistics companies can register new clients in the system. 

  
  Scenario: Logistics Company, Register new client
    Given a logistic company
    And given a new client
    When a logistic company wants to create a new client
    Then the client is created and message displayed says "Client successfully created!"
    And is found in the database

  
