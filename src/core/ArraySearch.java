package core;

public class ArraySearch<T> {
	
	private Search<T> search;
	
	public ArraySearch(Search<T> search) {
		this.search = search;
	}
	
	public void setSearch(Search<T> search) {
		this.search = search;
	}
	
	public T find(int ID, T[] Objects) {
		T result = search.findFromID(ID, Objects);
		return result;
	}
	
	public T find(String string, T[] Objects) {
		T result = search.findFromString(string, Objects);
		return result;
	}
	
	public T find(String firstString, String secondString, T[] Objects) {
		T result = search.findFromStrings(firstString, secondString, Objects);
		return result;
	}
}
