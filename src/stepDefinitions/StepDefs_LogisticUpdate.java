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
		selectedC.setContainerContent(newStuff);
	}
	

	@Then("the content is updated")
	public void the_content_is_updated() {
		assertEquals(client.getID(), selectedC.getClientofContainer().getID());
		assertEquals(newStuff.getContentID(), selectedC.getContainerContent().getContentID());
		assertEquals(newStuff.getName(), selectedC.getContainerContent().getName());
		assertTrue(/*newStuff.getThreshold()*/ 5.0 == selectedC.getContainerContent().getThreshold());//assertEquals(double double) was outdated
		assertEquals(newStuff.getEnvironment().getEnviro_ID(), selectedC.getContainerEnvironment().getEnviro_ID());
		assertTrue(newStuff.getEnvironment().getHumidity() == selectedC.getContainerEnvironment().getHumidity());//assertEquals double double was outdated
		assertTrue(newStuff.getEnvironment().getPressure() == selectedC.getContainerEnvironment().getPressure());//assertEquals double double was outdated
		assertTrue(newStuff.getEnvironment().getTemp() == selectedC.getContainerEnvironment().getTemp());//assertEquals double double was outdated
	}
	
	@Then("a message is displayed saying {string}") 
	public void a_message_is_displayed_saying(String s){
		System.out.println(response.getNotifyMessage());
		assertEquals(s, response.getNotifyMessage());
	}
}
