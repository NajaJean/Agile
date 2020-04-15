	
Feature: Client, Fill Container
	Description: The Client wants to fill a container 
	Actor: Client

	Scenario: Client is able to fill container
		Given that a client has an empty container
		And some content (Name of content, required environment, threshold it is able to withstand, start location, end location)
		When the client fills a container with the content
		Then the container contains the content
		And the container should no longer be empty in the database
		And a success-message is displayed "Container successfully booked"

	Scenario: Client is not able to fill container
		Given that a client has an empty container
		And no content
		When the client tries to fill the container
		Then the container filling fails 
		And an error-message is displayed: "All fields has to be filled"
 