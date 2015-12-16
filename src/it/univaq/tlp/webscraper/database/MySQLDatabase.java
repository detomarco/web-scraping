package it.univaq.tlp.webscraper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

public class MySQLDatabase implements Database {
	
	private Connection db;
	
	// Quando la classe viene istanziata, si connette automaticamente
	public MySQLDatabase(DatabaseConfig cfg){
		connect(cfg);
	}
	
	// Connette il database (setta la variabile Connection)
	public void connect(DatabaseConfig cfg) {
		
		Properties connProps = new Properties();
		connProps.put("user", cfg.getUser());
		connProps.put("password", cfg.getPassword());
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.db = DriverManager.getConnection("jdbc:mysql://"+cfg.getHost()+":"+cfg.getPort()+"/"+cfg.getDbname(), connProps);
			System.out.println("Database connesso!");
		} catch (SQLException e){
			System.out.println("Errore conenssione: SQLException");
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			System.out.println("Class Not Found!");
		} catch (Exception e){
			System.out.println("Eccezione generica!");
		} finally {
			System.out.println("End connection try");
		}
		
	}
	
	@Override
	public ResultSet select(String table, String condition, String order) throws SQLException{	
		Statement s = this.db.createStatement();  
		ResultSet records = s.executeQuery("SELECT * FROM "+table+" WHERE "+condition+" ORDER BY "+order);  
		return records;
	}
	
	@Override
	public ResultSet select(String table, String order) throws SQLException{
		return this.select(table, "'1'='1'", order);
	}
	
	@Override
	public void insert(String table, Map<String, Object> data) throws SQLException{	  
		Statement s = this.db.createStatement();
		String query = "INSERT INTO "+table+" SET ";
		for (Map.Entry<String, Object> entry: data.entrySet()){
			query = query + entry.getKey()+ " = '" +entry.getValue()+ "', ";
		}
        query = query.substring(0, query.length() - 2);       
        s.executeUpdate(query);  
	}	
	
	@Override
	public void update(String table, Map<String,Object> data, String condition) throws SQLException{
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

