package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Database;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefs_LogisticsCompCreateClient{
    
    private ScenarioContext context;
    
    public StepDefs_LogisticsCompCreateClient(ScenarioContext context){
    	this.context=context;
    	
    }
    
    Client C = new Client("bob", "1234", "Bob Smith", "bob_smith@gmail.com","l34 Candy ln");
    Database d = new Database("agileProject.accdb");
    
    NotifyObject response;
    
    @Given("that the logistics company wants to create a new client")
    public void that_the_logistics_company_wants_to_create_a_new_client() {
        Client C; // do i need to put more here, or am i indicating that i've just referenced the client above?  
    }
    
    @Given ("they enter a client name")
    public void they_enter_a_client_name() {
        assertEquals(C.getName(), "Bob Smith");
    }
    
    @Given ("they enter an unused username")
    public void they_enter_an_unused_username() {
        assertEquals(C.getUserName(), "bob");
    }
    
    @Given ("they enter a password")
    public void they_enter_a_password() {
        assertEquals(C.getPassword(), "1234");
    }
    
    @Given ("they enter a valid email")
    public void they_enter_a_valid_email() {
        assertEquals(C.getEmail(), "bob_smith@gmail.com");
    }
    
    @Given ("they enter an address")
    public void they_enter_an_address() {
        assertEquals(C.getAddress(), "l34 Candy ln");
    }
    
    @Then ("the client is successfully created and given a valid client ID")
        public void the_client_is_successfully_created_and_given_a_valid_client_ID() {
//            d.addToDatabase(tableName, values); // what to insert here again? Is ID # automatically given?
        }
        
        
        
        
        
    }
 
 
