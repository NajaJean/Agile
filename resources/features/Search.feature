
Feature: Client or logistic-company are able to search specific information.

  Scenario: Logistic Company finds client in database using their email
  	Given a logistic company
  	And a client email 
  	When the logistic company types in the email
  	Then the client with the typed email will be found
  
  Scenario: Client finds all their containers in the database using the content as a search-word
   Given a client 
   When the client types the content of the container
   Then the containers belonging to the client and with the desired content is found. 

