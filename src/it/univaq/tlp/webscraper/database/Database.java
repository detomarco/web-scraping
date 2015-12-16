package it.univaq.tlp.webscraper.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import it.univaq.tlp.webscraper.aggregatordata.Storable;

public abstract class Database implements Storable{
	
	@Override
	public void save(){
		
	}
	
	@Override
	public void get(){
		
	}
	
	@Override
	public void update(){
		
	}
	
	protected abstract ResultSet select(String table, String condition, String order) throws Exception;
	
	protected abstract ResultSet select(String table, String order) throws Exception;
	
	protected abstract void insert(String table, Map<String, Object> data) throws Exception;
	
	protected abstract void update(String table, Map<String,Object> data, String condition) throws Exception;
}
