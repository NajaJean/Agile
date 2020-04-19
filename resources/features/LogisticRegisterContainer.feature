Feature: Logistic Company, Register Container 
	Description: The Logistic Company wants to register a container 
	Actor: Logistic Company

Scenario: Logistic Company is able to register container
	Given that Logistic Company wants to register a container
	When the Logistic Company inserts all information
	Then the container is created with no content in the database and message displayed saying “Container successfully registered”
	And container is found in the database
