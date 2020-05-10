package stepDefinitions;

import ExternalData.Database;
import ExternalData.DatabaseData;
import core.ArraySearch;
import core.Container;
import core.Environment;
import core.Location;
import core.LogisticCompany;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticRegisterContainer {
	
	Database d = DatabaseData.getDatabase();
	Container C;
	LogisticCompany l;
	Location[] Locations;
	ScenarioContext context;
	NotifyObject response;
	ArraySearch search;
	
	Location loc;
	
	public StepDefs_LogisticRegisterContainer(ScenarioContext context) {
		this.context = context;
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

	@Then("the container is created with no content in the database")
	public void the_container_is_created_with_no_content_in_the_database() {
		C = new Container(loc);
		d.addToDatabase(C);
		response = new NotifyObject(202, "Container successfully registered");
		context.setResponse(response);	
	}

	@Then("container is found in the database")
	public void container_is_found_in_the_database() {
	    d.getEmptyContainer(); 
	    d.removeFromDatabase(C);
	}
}
