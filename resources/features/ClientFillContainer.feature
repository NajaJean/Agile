	
	Feature: Client, Fill Container
	Description: The Client wants to fill a container 
	Actor: Client

	Scenario: Client is able to fill container
		Given that a client is able to book a container
		When the Client insert all information correctly (Name of content, required environment, threshold it is able to withstand, start location, end location)
		Then the container should be filled
		And container should no longer be empty in the database
		And the Client should get a message “Container successfully booked”

	Scenario: Client is not able to fill container
		Given that a client is able to book a container
		When the Client does not insert all information (Name of content,	required environment, threshold it is able to withstand, start location, end location)
		Then the container filling fails with a message “All fields has to be filled”
 