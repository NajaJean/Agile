
Feature: Logistic Company Login
	Description: The Logistic Company logs into the ContainerSystem 
	Actor: Logistic Company
	
	Scenario: Logistic Company can login 
		Given that the Logistic Company is not logged in 
		And the username is "MSK" and password is "1234"
		When the logistic Company logs in
		Then the Logistic Company is logged in
	
	Scenario: Logistic Company has the wrong password 
		Given that the Logistic Company is not logged in 
		And the username is "wrong username" or the password is "wrong password" 
		When the logistic Company tries logs in
		Then an error-message is printed
		And the Logistic Company is not logged in

