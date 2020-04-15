
Feature: Logistic Company Login
	Description: The Logistic Company logs into the ContainerSystem 
	Actor: Logistic Company
	
	Scenario: Logistic Company can login 
		Given that the Logistic Company is not logged in 
		And the username is "MSK" and password is "1234"
		When the logistic Company logs in
		Then the Logistic Company is logged in
		And message is displayed saying "Login successful"
	
	Scenario: Logistic Company has the wrong password 
		Given that the Logistic Company is not logged in 
		And the username or the password is wrong 
		When the logistic Company tries logs in
		Then message is displayed saying "Incorrect username or password"
		And the Logistic Company is not logged in

