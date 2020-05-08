package core;

public abstract class Search {
	protected int ID;
	
	public int findFromID(int ID, Search[] objects) {
		int index = -1;
		try {
			for(int i = 0; i < objects.length; i++) {
				if(ID == (objects[i]).ID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}
	
	public abstract int findFromString(String string, Object[] Objects);
}
