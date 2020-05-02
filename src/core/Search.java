package core;

public interface Search {
	public abstract int findFromID(int ID, Object[] Objects);
	
	public abstract int findFromString(String string, Object[] Objects);
	
	public abstract int findFromStrings(String firstString, String secondString, Object[] Objects);
}
