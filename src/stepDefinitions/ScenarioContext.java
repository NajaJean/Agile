package stepDefinitions;

import core.Database;
import core.NotifyObject;

public class ScenarioContext {
	private NotifyObject response;
	private Database d = new Database("agileProject.accdb"); 
	
	public void setResponse(NotifyObject response) {
		this.response = response;
	}
	
	public NotifyObject getResponse() {
		return response;
	}
}
