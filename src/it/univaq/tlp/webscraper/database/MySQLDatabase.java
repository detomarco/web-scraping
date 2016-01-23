package it.univaq.tlp.webscraper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import it.univaq.tlp.webscraper.aggregatordata.StorageException;

public class MySQLDatabase extends Database {
	
	private Connection db;
	
	// Quando la classe viene istanziata, si connette automaticamente
	public MySQLDatabase(String user, String password, String host, int port, String db_name) throws StorageException{
		Properties connProps = new Properties();
		connProps.put("user", user);
		connProps.put("password", password);
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.db = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db_name, connProps);
		} catch (SQLException e){
			throw new StorageException("SQLException " + e.getMessage());
		} catch (ClassNotFoundException e){
			throw new StorageException("Class Not Found!");
		} catch (Exception e){
			throw new StorageException("Non definito");
		} 
	}
	
	
	
	@Override
	protected ResultSet select(String table, String condition, String order) throws SQLException{	
		Statement s = this.db.createStatement();  
		ResultSet records = s.executeQuery("SELECT * FROM "+table+" WHERE "+condition+" ORDER BY "+order);  
		return records;
	}
	
	@Override
	protected ResultSet select(String table, String order) throws SQLException{
		return this.select(table, "'1'='1'", order);
	}
	
	@Override
	protected void insert(String table, Map<String, Object> data) throws SQLException{	  
		Statement s = this.db.createStatement();
		String query = "INSERT INTO "+table+" SET ";
		for (Map.Entry<String, Object> entry: data.entrySet()){
			query = query + entry.getKey()+ " = '" +entry.getValue()+ "', ";
		}
        query = query.substring(0, query.length() - 2);       
        s.executeUpdate(query);  
	}	
	
	@Override
	protected void update(String table, Map<String,Object> data, String condition) throws SQLException{
		Statement s = this.db.createStatement();
		String query = "UPDATE " +table+" SET ";
		for(Map.Entry<String, Object> entry: data.entrySet()){
			query = query + entry.getKey()+ " = '" +entry.getValue()+ "', ";
		}
        query = query.substring(0, query.length() - 2);       
        query = query + " WHERE " + condition;
        s.executeUpdate(query);
	}

}

