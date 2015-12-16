package it.univaq.tlp.webscraper.database;

public class DatabaseConfig {
	
	private String user;
	private String password;
	private String port;
	private String host;
	private String dbname;
	
	public DatabaseConfig(String user, String pass, String host, String port, String dbname){
		this.user = user;
		this.password = pass;
		this.port = port;
		this.host = host;
		this.dbname = dbname;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getHost(){
		return this.host;
	}
	
	public String getPort(){
		return this.port;
	}
	
	public String getDbname(){
		return this.dbname;
	}
	
}