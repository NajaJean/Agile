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
	
	public int findIDX(int ID, Search[] Objects) {
		int result = search.find(ID, Objects);
		return result;
	}
	
	public int findIDX(String string, Object[] Objects) {
		int result = search.find(string, Objects);
		return result;
	}
	
}
