package core;

import java.sql.*; 

public class Database {
	private Connection c;
	private Statement s;

	public Database(String url) {
		try{  
			this.c = DriverManager.getConnection("jdbc:ucanaccess://"+url); 

		}catch(Exception ee){System.out.println(ee);} 
		try {
			this.s=c.createStatement();
		} catch (SQLException e){System.out.println(e);}  
	}

	public String[][] getTable(String tableName) {
		String[][] values = new String[50][10];
		ResultSet result;
		int column = 1;
		int row = 0;
		try {
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
			System.out.println(tableName + "  table imported sucessfully!");
		} catch (SQLException e){System.out.println(e);}
		return values;
	}
	
	public String queryDatabase(String sql) {  
		ResultSet result;
		String query = "";
		int i = 1;
		try {
			result = s.executeQuery(sql);
			while (result.next()) {
				while(true) {
					try {
						query = query + result.getString(i) + ", ";
						i++;
					}
					catch (SQLException e){
						query = query + "\n";
						i = 1;
						break;
					} 
				}
			}
		} catch (SQLException e){System.out.println(e);} 
		return query;
	}

	public void addToDatabase(String tableName, String values){
		String addRow = "INSERT INTO " + tableName + " VALUES (";
		addRow = addRow + values +")"; 
		try {
			s.execute(addRow);
		} catch (SQLException e){System.out.println(e);} 		
	}
	
	public boolean checkUser(String user, String pass) {
		boolean approved = false;
		try {
			ResultSet result = s.executeQuery("SELECT Username, password FROM Clients");
			while (result.next()) {
				String username = result.getString(1);
				String password = result.getString(2);
				if(user.equals(username) && pass.equals(password)) {
					approved = true;
					break;
				}
			}
		} catch (SQLException e){System.out.println(e);} 
		return approved;
	}
	
	public int getEmptyContainer() {
		ResultSet result;
		String query = "SELECT Container_ID FROM Containers WHERE Content_ID IS NULL AND Client_ID IS NULL";
		int index = 0;
		try {
			result = s.executeQuery(query);
			while (result.next()) {
				 index = result.getInt(1);
			}
		} catch (SQLException e){System.out.println(e);} 
		return index;
	}
}

