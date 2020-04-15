
Feature: Client can see current container status
	Description: A client should be able access the current status of their containers. 
	Actor: Client.

	Scenario: Logistic company updates the current status, which is updated for the client as well
		Given a client with a container
		And a logistic company
		When a logistic company updates the current status of the container
		Then the updates should be visible for the client