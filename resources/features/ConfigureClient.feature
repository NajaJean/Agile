
Feature: Configure Client  Mathilde
	Description: A client wants to configure its details.
	Actor: Client
	Scenario: Client configures its details.
		Given a client
		When a client configures its details
		Then the client should have the new configured details
		And the details should be updated in the database
		And a confirmation-message is displayed: "Your information is updated!"
