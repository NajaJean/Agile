package core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DatabaseObserver implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch ((String) evt.getNewValue()) {
			case ("Clients"):
				DatabaseData.addClient((Client) evt.getOldValue()); 
				break;
			case ("Containers"):
				DatabaseData.addContainer((Container) evt.getOldValue());
				break;
			case ("Contents"):
				// addContent
				break;
			case ("Environments"):
				// addEnvironment
				break;
			case ("Journies"):
				DatabaseData.addContainerJourney((ContainerJourney) evt.getOldValue());
				break;
			case("Locations"):
				// addLocation
				break;
		}
	}
	
}
