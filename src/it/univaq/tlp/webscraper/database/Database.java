package it.univaq.tlp.webscraper.database;

import java.sql.ResultSet;
import java.util.Map;
import java.lang.Exception;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;

public abstract class Database implements Storable{
	
	@Override
	public void save(String table, Map<String, Object> data) throws StorageException{
		try{
			this.insert(table, data);
		} catch (Exception e){
			throw new StorageException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public void get(String table, String condition) throws StorageException{
		try{
			this.select(table, condition, "");
		} catch (Exception e){
			throw new StorageException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public void updateStored(String table, Map<String,Object> data, String condition) throws StorageException{
		try{
			this.update(table, data, "");
		} catch (Exception e){
			throw new StorageException(e.getMessage(), e.getCause());
		}

	}
	
	protected abstract ResultSet select(String table, String condition, String order) throws Exception;
	
	protected abstract ResultSet select(String table, String order) throws Exception;
	
	protected abstract void insert(String table, Map<String, Object> data) throws Exception;
	
	protected abstract void update(String table, Map<String,Object> data, String condition) throws Exception;
}
