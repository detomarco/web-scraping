package it.univaq.tlp.webscraper.aggregatordata;

import java.util.Map;

public interface Storable {
	
	public void save(String kind, Map<String, Object> data) throws StorageException;
	
	public void get(String kind, String condition) throws StorageException;
	
	public void updateStored(String kind, Map<String,Object> data, String condition) throws StorageException;

}
