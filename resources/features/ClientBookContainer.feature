
Feature: Client, Book Container 
	Description: The Client wants to book a container 
	Actor: Client

	Scenario: Client is able to book container
		Given that there exists an empty container in the database 
		When the Client books a container
		Then the first empty container existing in the database should be assigned to the Client
		Then message is displayed saying "Container is succesfully booked"

	Scenario: Client is not able to book container 
		Given that there does not exist an empty container in the database 
		When the Client tries to book a container
		Then message is displayed saying "No empty containers available"
