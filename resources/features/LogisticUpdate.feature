Feature: Logistic Company, Update container status 
	Description: The Logistic Company wants to update the status of a container 
	Actor: Logistic Company

	Scenario: Logistic Company is able to update the status of container
		Given company selected a container
		When the company updates the content
		Then the content is updated
		And a message is displayed: "Content is updated successfully"
		
	Scenario: Logistic Company is able to update the status of container
		Given the company selected a container journey
		When the company updates the current location
		Then the current location is updated
		And a message is displayed: "The current location update was successful"

	