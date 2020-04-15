package stepDefinitions;

import core.NotifyObject;

public class ScenarioContext {
	private NotifyObject response;
	
	public void setResponse(NotifyObject response) {
		this.response = response;
	}
	
	public NotifyObject getResponse() {
		return response;
	}
}
