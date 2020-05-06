
Feature: Client Login
  The client logs into the ContainerSystem 
	Actor: Client

  Scenario: Client can login
    Given a client with username "bob" and password "1234"
    And that the client is not logged in
    And the username is correct and password is correct
    And username and password exits in the Database
    When the client logs in
    Then the client is logged in 	
    And message is displayed saying "Login successful"

	Scenario: Client has the wrong password 
		Given a client with username "bob" and password "1234"
		And that the client is not logged in 
		And the password or username is incorrect
		When the client tries to log in
		Then message is displayed saying "Incorrect username or password"
		And the client is not logged in
	

