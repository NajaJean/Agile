package stepDefinitions;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Container;
import core.Content;
import core.Environment;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticUpdate {
	
	ScenarioContext context;
	
	public StepDefs_LogisticUpdate(ScenarioContext context) {
		this.context = context;
	}
	
	NotifyObject response;
	
	Environment enviro = new Environment(5.0, 5.0, 5.0);
	Environment newEnviro = new Environment(15.0, 15.0, 15.0);
	
	Client client = new Client("asd", "yoDaddy69");
	
	Content stuff = new Content("Stuff", enviro, 1.0);
	Content newStuff = new Content("NewStuff", newEnviro, 2.0);
	
	Container selectedC = new Container(client, enviro, stuff);
	
	@Given("the company selected a container")
	public void the_company_selected_a_container() {
		assertEquals(client.getID(), selectedC.getClientofContainer().getID());
	}

	@When("the company updates the content")
	public void the_company_updates_the_content() {
		response = selectedC.setContainerContent(newStuff);
		context.setResponse(response);
	}
	

	@Then("the content is updated")
	public void the_content_is_updated() {
		assertEquals(client.getID(), selectedC.getClientofContainer().getID());
		assertEquals(newStuff.getContentID(), selectedC.getContainerContent().getContentID());
		assertEquals(newStuff.getName(), selectedC.getContainerContent().getName());
		assertTrue(newStuff.getThreshold() == selectedC.getContainerContent().getThreshold());
		
		// The contents environment requirement do not need to be equal to the environment of the container. 
		// The container could be outside of the environment threshold of the contents for instance
		// Should delete 
		
		//assertEquals(newStuff.getEnvironment().getEnviro_ID(), selectedC.getContainerEnvironment().getEnviro_ID());
		//assertTrue(newStuff.getEnvironment().getHumidity() == selectedC.getContainerEnvironment().getHumidity());
		//assertTrue(newStuff.getEnvironment().getPressure() == selectedC.getContainerEnvironment().getPressure());
		//assertTrue(newStuff.getEnvironment().getTemp() == selectedC.getContainerEnvironment().getTemp());
	}
}
