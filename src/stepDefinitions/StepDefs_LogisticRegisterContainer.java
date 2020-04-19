package stepDefinitions;

import core.Container;
import core.Database;
import core.Environment;
import core.Location;
import core.LogisticCompany;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticRegisterContainer {
	
	Database d = new Database("agileProject.accdb");
	Container C;
	LogisticCompany l;
	
	
	Location loc;
	Environment enviro;

	@Given("that Logistic Company wants to register a container")
	public void that_Logistic_Company_wants_to_register_a_container() {
		l = new LogisticCompany();
		// create a new container? 
	}

	@When("the Logistic Company inserts all information")
	public void the_Logistic_Company_inserts_all_information() {
		enviro = new Environment(5.3,1.1,0.85);
		double[] cphgps = {55.1, 12.6};
		loc = new Location("Copenhagen",cphgps);
	}

	@Then("the container is created with no content in the database and message displayed saying {string}")
	public void the_container_is_created_with_no_content_in_the_database_and_message_displayed_saying(String string) {
		C = new Container(enviro,loc);
		String s = C.toString();
		d.addToDatabase("Containers", s);
		System.out.print(string);
	}
	
	@Then("the container is given an ID")
	public void the_container_is_given_an_ID() {
		C.getContainerID();
	}

	@Then("container is found in the database")
	public void container_is_found_in_the_database() {
	    d.getEmptyContainer(); 
	}
}
