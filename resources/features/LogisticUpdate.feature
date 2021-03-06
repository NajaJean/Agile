Feature: Logistic Company, Update container status 
	Description: The Logistic Company wants to update the status of a container 
	Actor: Logistic Company

	Scenario: Logistic Company is able to update the status of container
		Given company selected a container
		When the company updates the environment
		Then the environment is updated
		And the environment should be updated in the database
		And message is displayed saying "Environment is updated successfully"
		
	Scenario: Logistic Company is able to update the status of container
		Given the company selected a container journey
		When the company updates the current location
		Then the current location is updated
		And the current location should be updated in the database
		And message is displayed saying "The update was successful"

	