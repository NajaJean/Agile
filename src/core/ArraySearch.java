package core;

public class ArraySearch {
	
	private Search search;
	
	public ArraySearch() {}
	
	public ArraySearch(Search search) {
		this.search = search;
	}
	
	public void setSearch(Search search) {
		this.search = search;
	}
	
	public int findIDX(int ID, Object[] Objects) {
		int result = search.findFromID(ID, Objects);
		return result;
	}
	
	public int findIDX(String string, Object[] Objects) {
		int result = search.findFromString(string, Objects);
		return result;
	}
	
	public int findIDX(String firstString, String secondString, Object[] Objects) {
		int result = search.findFromStrings(firstString, secondString, Objects);
		return result;
	}
}
