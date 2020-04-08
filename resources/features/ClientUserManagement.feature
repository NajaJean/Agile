
@tag
Feature: ClientUserManagment
	Description: This feature deals with the functionality of the user management and role-based access for the logistic company and the clients
	Actor: User of Container Management System (client or logistic company)

  @tag1
  Scenario: Only the specific client has private access to their container
  Given a client books a container
  And the client sets the contents of the container
  When clients log into the Container Management System
  Then only the client with username "bob" and password "1234" has access to that container
  
