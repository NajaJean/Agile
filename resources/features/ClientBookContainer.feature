
Feature: Client, Book Container 
	Description: The Client wants to book a container 
	Actor: Client

	Scenario: Client is not able to fill container
		Given that there exists an empty container in the database 
		When the client tries to book a container by filling it with no content
		Then message is displayed saying "All fields has to be filled"

	Scenario: Client is able to book container
		Given that there exists an empty container in the databasew 
		When the client books a container by filling it with a content
		Then the first empty container existing in the database should be assigned to the client
		Then the container should contain the content in the database
		Then message is displayed saying "Container is succesfully booked"
		
	Scenario: Client is not able to book container 
		Given that there does not exist an empty container in the database 
		When the Client tries to book a container
		Then message is displayed saying "No empty containers available"
