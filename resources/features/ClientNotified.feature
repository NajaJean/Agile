
@tag
Feature: ClientNotified
  Description: This feature deals with the functionality of notifying the Client
  Actor: Client

  @tag1
  Scenario: Client is notified when one of its containers environment is outside the contents required threshold for environment
    Given a client has a container 
    When the environment in the container is outside the contents threshold 
    Then container management system notifies client of invalid environment
    
  
	Scenario: Client is notified when one of its containers reaches its final destination
  	Given a client has a container
  	When the container reaches its final destination
  	Then container management system notifies client container arrived
  
