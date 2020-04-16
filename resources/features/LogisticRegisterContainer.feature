Feature: Logistic Company, Register Container 
	Description: The Logistic Company wants to register a container 
	Actor: Logistic Company

Scenario: Logistic Company is able to register container
	Given that Logistic Company wants to register a container
	When the Logistic Company insert all information (Current environment and current location)
	Then the container is created with no content in the database
	And a message “Container successfully registered” is displayed.

Scenario: Logistic Company is not able to register container 
	Given that Logistic Company wants to register a container
	When the Logistic Company does not insert all information (Current environment and current location)
	Then the container registering fails with a message “All fields has to be filled”
