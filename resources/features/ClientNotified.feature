#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: ClientNotified
  This feature deals with the functionality of notifying the Client when it has a container 
  with an environment that is outside the contents threshold for environment

  @tag1
  Scenario: Client is notified when one of its containers environment is outside the contents threshold for environment
    Given a client with username "corona" and password "1234" has a container
    And the container has contents "banana" with a threshold of 0.1
    When The environment in the container is outside the contents threshold 
    Then system displays message that the client is notified

