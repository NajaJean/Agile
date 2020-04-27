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
				case ("Journies"):
					DatabaseData.addContainerJourney((ContainerJourney) evt.getNewValue());
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
				case ("Journies"):
					DatabaseData.updateJourney((ContainerJourney) evt.getNewValue());
					break;
			}
		} else if (action == "Remove") {
			switch ((String) evt.getOldValue()) {
				case ("Clients"):
					DatabaseData.removeClient((Client) evt.getNewValue());
					break;
				case ("Containers"):
					DatabaseData.removeContainer((Container) evt.getNewValue());
					break;
				case ("Journies"):
					DatabaseData.removeContainerJourney((ContainerJourney) evt.getNewValue());
					break;
			}
		}
	}
}
