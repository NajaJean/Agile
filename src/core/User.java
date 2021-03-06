package core;
import core.NotifyObject;

public interface User {
	
	public abstract NotifyObject logIn(boolean b);
	
	public abstract boolean isLoggedIn();
	
	public abstract String getUsername();
	
	public abstract String getPassword();
	
	public abstract String getName();
	
}
