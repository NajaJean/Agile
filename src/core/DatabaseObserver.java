package core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DatabaseObserver implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch ((String) evt.getPropertyName()) {
			case ("Clients"):
				DatabaseData.addClient((Client) evt.getNewValue());
				break;
			case ("Containers"):
				DatabaseData.addContainer((Container) evt.getNewValue());
				break;
			case ("Contents"):
				// addContent
				break;
			case ("Environments"):
				// addEnvironment
				break;
			case ("Journies"):
				DatabaseData.addContainerJourney((ContainerJourney) evt.getNewValue());
				break;
			case("Locations"):
				// addLocation
				break;
		}
	}
	
}
