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
Feature: CreateContainer feature
  This feature deals with the functionality of creating a container with a client, environment, and content.  

  @tag1
  Scenario: Create a container with a specific client, environment, and content
    Given a new Container
    And it has a client with username "corona" and password "1234"
    And it has an environment with temperature 13.3, pressure , and humidity 0.90
    And it has a content 
    Then 

