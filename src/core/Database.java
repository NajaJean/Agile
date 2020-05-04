package core;

import java.sql.*; 

public class Database {	
	private Connection c;

	public Database(String url) {
		try{  
			this.c = DriverManager.getConnection("jdbc:ucanaccess://"+url); 

		}catch(Exception ee){System.out.println(ee);} 
	}

	public String[][] getTable(String tableName) {
		String[][] values = new String[50][10];
		ResultSet result;
		int column = 1;
		int row = 0;
		try {
			Statement s = c.createStatement();
			result = s.executeQuery("SELECT * FROM "+tableName);
			while (result.next()) {
				row++;
				while(true) {
					try {
						values[row][column] = result.getString(column);
						column++;
					}
					catch(SQLException e){
						column = 1;
						break;
					}
				}
			}
			//System.out.println(tableName + "  table imported sucessfully!");
			s.close();
		} catch (SQLException e){System.out.println(e);}

		return values;
	}
	
	public static int lengthTable(String[][] table) {
		int len = 0;
		for(int i = 1; i < table.length; i++) {
			if (!(table[i][1] == null)) {
				len++;
			}
		}
		return len;
	}
	
	public void removeFromDatabase(DatabaseEntity e) {
		String removeRow = "DELETE FROM " + e.databaseTable() + " WHERE ID = "+e.entityID();
		try {
			Statement s = c.createStatement();
			s.execute(removeRow);
			System.out.println("data removed sucessfully");
			s.close();
		} catch (SQLException ex){System.out.println(ex);}
	}
	
	public String queryDatabase(String sql) {  
		ResultSet result;
		String query = "";
		int i = 1;
		try {
			Statement s = c.createStatement();
			result = s.executeQuery(sql);
			while (result.next()) {
				while(true) {
					try {
						query = query + result.getString(i);
						i++;
					}
					catch (SQLException e){
						i = 1;
						break;
					} 
				}
			}
			s.close();
		} catch (SQLException e){System.out.println(e);} 
		return query;
	}

	public void addToDatabase(String tableName, String values){
		String objType = tableName;
		String addRow = "INSERT INTO " + tableName + " VALUES (" + values + ")";
		try {
			Statement s = c.createStatement();
			s.execute(addRow);
			System.out.println("data added sucessfully");
			s.close();
		} catch (SQLException e){System.out.println(e);} 		
	}
	// Changing current data to a new value
	public NotifyObject updateDatabase(String tableName, String column, String value, String condition) {
		NotifyObject response;
		String updateRow = "UPDATE " + tableName + " SET " + column + "=" + "'"+value+"'"
				+ " WHERE ID = " + condition;
		try {
			Statement s = c.createStatement();
			s.execute(updateRow);
			System.out.println("data updated sucessfully");
			s.close();
		} catch (SQLException e){
			System.out.println(e);
			response = new NotifyObject(0, "The update failed");
			return response;
		}
		response = new NotifyObject(23, "The update was successful");
		return response;
	}
	// Changing current data to a null field 
	public NotifyObject updateDatabase(String tableName, String column, String condition) {
		NotifyObject response;
		String updateRow = "UPDATE " + tableName + " SET " + column + "= NULL"
				+ " WHERE ID = " + condition;
		try {
			Statement s = c.createStatement();
			s.execute(updateRow);
			System.out.println("data updated sucessfully");
			s.close();
		} catch (SQLException e){
			System.out.println(e);
			response = new NotifyObject(0, "The update failed");
			return response;
		}
		response = new NotifyObject(23, "The update was successful");
		return response;
	}
	
	public boolean checkUser(String user, String pass) {
		boolean approved = false;
		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT Username, password FROM Clients");
			while (result.next()) {
				String username = result.getString(1);
				String password = result.getString(2);
				if(user.equals(username) && pass.equals(password)) {
					approved = true;
					break;
				}
			}
			s.close();
		} catch (SQLException e){System.out.println(e);} 
		return approved;
	}
	
	public int getEmptyContainer() {
		ResultSet result;
		String query = "SELECT ID FROM Containers WHERE Content_ID IS NULL AND Client_ID IS NULL";
		int index = 0;
		try {
			Statement s = c.createStatement();
			result = s.executeQuery(query);
			while (result.next()) {
				 index = result.getInt(1);
				 return index;
			}
			s.close();
		} catch (SQLException e){System.out.println(e);} 
		return index;
	}
	
}

