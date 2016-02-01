package it.univaq.tlp.webscraper.controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import it.univaq.tlp.webscraper.controller.exception.StorageException;

/**
 * This class provides MySQL implementation methods for databases
 * @author Gianluca
 *
 */
public class MySQLDatabase extends Database {
	
	private Connection db;
	
	private String user;
	private String password;
	private String host;
	private int port;
	private String db_name;
	
	/**
	 * 
	 * @param user
	 * @param password
	 * @param host
	 * @param port
	 * @param db_name
	 */
	public MySQLDatabase(String user, String password, String host, int port, String db_name) {
		this.user = user;
		this.password = password;
		this.host = host;
		this.port = port;
		this.db_name = db_name;
	}

	/**
	 * This method allows connection to database
	 */
	@Override
	public void connect() throws StorageException{
		Properties connProps = new Properties();
		connProps.put("user", user);
		connProps.put("password", password);
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.db = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db_name, connProps);
		} catch (Exception e){
			throw new StorageException();
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
			
			query = query + entry.getKey().replaceAll("'", "''") + " = '" +entry.getValue().toString().replaceAll("'", "''")+ "', ";
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

