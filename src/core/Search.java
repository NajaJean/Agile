package core;

public interface Search<T> {
	public abstract T findFromID(int ID, T[] Objects);
	
	public abstract T findFromString(String string, T[] Objects);
	
	public abstract T findFromStrings(String firstString, String secondString, T[] Objects);
}
