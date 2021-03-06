package it.univaq.tlp.webscraper.controller.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.controller.Storable;
import it.univaq.tlp.webscraper.controller.exception.StorageException;

/**
 * This abstract class implements interface Storable to bind with a database-type storage
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
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
	public Set<Map<String, String>> get(String table, String condition) throws StorageException{
		return get(table, condition, 0);
	}
	
	@Override
	public Set<Map<String, String>> get(String table, String condition, int count) throws StorageException{
		
		try{
			
			ResultSet results;
			if(count == 0){
				results = this.select(table, condition, "'id' DESC");
			} else {
				results = this.select(table, condition, "'id' DESC LIMIT "+count);
			}
			
			Set<Map<String, String>> rows = new LinkedHashSet<Map<String, String>>();
			
			// Recupero nomi colonne (in array)
			ResultSetMetaData metadata = results.getMetaData();
			int column_count = metadata.getColumnCount();
			
			String column_labels[] = new String[column_count];
			
			for(int i=1; i<=column_count; i++){
				column_labels[i-1] = metadata.getColumnLabel(i);
			}
			
			while (results.next()){
				Map<String, String> row = new HashMap<String, String>();
				
				for (int i=1; i<=column_count; i++){
					row.put(column_labels[i-1], results.getString(i));
				}
				
				rows.add(row);
			}
			
			return rows;
			
		} catch (Exception e){
			throw new StorageException(e.getMessage(), e.getCause());
		}
		
	}
	
	@Override
	public Set<Map<String, String>> getGrouped(String kind, String condition, String to_group) throws StorageException{
		try{
			return this.get(kind, condition+" GROUP BY "+to_group);
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
	
	/**
	 * This method provides an interface to sorted select for database tables
	 * @param table
	 * @param condition
	 * @param order
	 * @return ResultSet of selected records
	 * @throws Exception
	 */
	protected abstract ResultSet select(String table, String condition, String order) throws Exception;
	
	/**
	 * This method provides an interface to select for database tables
	 * @param table
	 * @param order
	 * @return
	 * @throws Exception
	 */
	protected abstract ResultSet select(String table, String order) throws Exception;
	
	/**
	 * This method provides an interface to insert for database tables
	 * @param table
	 * @param data
	 * @throws Exception
	 */
	protected abstract void insert(String table, Map<String, Object> data) throws Exception;
	
	/**
	 * This method provides an interface to update for database tables
	 * @param table
	 * @param data
	 * @param condition
	 * @throws Exception
	 */
	protected abstract void update(String table, Map<String,Object> data, String condition) throws Exception;
}
