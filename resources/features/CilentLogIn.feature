
Feature: Client Login
  The client logs into the ContainerSystem 
	Actor: Client

  Scenario: Client can login
    Given that the client is not logged in
    And the username is correct and password is correct
    And username and password exits in the Database
    When the client logs in
    Then the client is logged in 

	Scenario: Client has the wrong password 
		Given that the client is not logged in 
		And the password or username is incorrect
		And username or password does not exits in the Database 
		When the client tries to log in
		Then an error-message is printed
		And the client is not logged in
	

