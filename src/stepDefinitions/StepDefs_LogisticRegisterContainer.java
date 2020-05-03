package stepDefinitions;

import core.ArraySearch;
import core.Container;
import core.Database;
import core.DatabaseData;
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
	Location[] Locations;
	
	ArraySearch search;
	
	Location loc;
	
	public StepDefs_LogisticRegisterContainer() {
		this.Locations = DatabaseData.getLocations();
		this.search = new ArraySearch(new Location());
	}

	@Given("that Logistic Company wants to register a container")
	public void that_Logistic_Company_wants_to_register_a_container() {		
		l = new LogisticCompany();
	}

	@When("the Logistic Company inserts all information")
	public void the_Logistic_Company_inserts_all_information() {
		int locationIDX = search.findIDX("Copenhagen", Locations);
		loc = Locations[locationIDX];
	}

	@Then("the container is created with no content in the database and message displayed saying {string}")
	public void the_container_is_created_with_no_content_in_the_database_and_message_displayed_saying(String string) {
		C = new Container(loc);
		String s = C.toString();
		d.addToDatabase("Containers", s);
		System.out.print(string);
	}

	@Then("container is found in the database")
	public void container_is_found_in_the_database() {
	    d.getEmptyContainer(); 
	    d.removeFromDatabase("Containers", C.getContainerID());
	}
}
