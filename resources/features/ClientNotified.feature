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
  This feature deals with the functionality of notifying the Client

  @tag1
  Scenario: Client is notified when one of its containers environment is outside the contents required threshold for environment
    Given a client with username "corona" and password "1234" has a container
    And the container has content "Banana" which has required evironment and threshold of 0.1
    When the environment in the container is outside the contents threshold 
    Then container management system notifies client of invalid environment
    
  Scenario: Client is notified when one of its containers reaches its final destination
  	Given: a client with username "corona" and password "1234" has a container
  	And the container has content "Banana" which has required evironment and threshold of 0.1
  	And the container is starting its journey at "Copenhagen" with coords 55.1 and 12.
		And the container has a final destination at "California" with coords 36.7 and 119.4
  	When the container reaches its final destination
  	Then container management system notifies client container arrived
  
