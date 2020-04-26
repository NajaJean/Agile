package core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DatabaseObserver implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String action = evt.getPropertyName();
		
		if (action == "Add") {
			switch ((String) evt.getOldValue()) {
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
		} else if (action == "Update") {
			switch ((String) evt.getOldValue()) {
				case ("Clients"):
					DatabaseData.updateClient((Client) evt.getNewValue());
					break;
				case ("Containers"):
					DatabaseData.updateContainer((Container) evt.getNewValue());
					break;
				case ("Contents"):
					// updateContent
					break;
				case ("Environments"):
					// updateEnvironment
					break;
				case ("Journies"):
					DatabaseData.updateJourney((ContainerJourney) evt.getNewValue());
					break;
				case("Locations"):
					// updateLocation
					break;
			}
		} else if (action == "Reomve") {
			// Remove logic
		}
	}
	
}
