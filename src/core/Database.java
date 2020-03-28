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
				try {
					values[row][column] = result.getString(column);
					column++;
				}
				catch(SQLException e){System.out.println(e);}
			}
			System.out.println(tableName + "  table imported sucessfully!");
		} catch (SQLException e){System.out.println(e);}
		return values;
	}

	public void queryDatabase(String sql) {  
		ResultSet result;
		try {
			result = s.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt("ID");
				String name = result.getString("Name");
				String email = result.getString("Email");
				String address = result.getString("Address");

				System.out.println(id + ", " + name + ", " + email + ", " + address);
			}
		} catch (SQLException e){System.out.println(e);} 	
	}

	public void addToDatabase(String tableName, String[] values){
		String addRow = "INSERT INTO " + tableName + " VALUES ( ";
		for(String value : values)
		{
			addRow = addRow + value;
		}
		addRow = addRow + "')";
		try {
			s.execute(addRow);
		} catch (SQLException e){System.out.println(e);} 		
	}
}

