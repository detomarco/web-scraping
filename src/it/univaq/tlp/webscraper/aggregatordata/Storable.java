package it.univaq.tlp.webscraper.aggregatordata;

import java.util.List;
import java.util.Map;

public interface Storable {
	
	public void save(String kind, Map<String, Object> data) throws StorageException;
	
	public List<Map<String, String>> get(String kind, String condition) throws StorageException;
	
	public List<Map<String, String>> get(String kind, String condition, int count) throws StorageException;
	
	public void updateStored(String kind, Map<String,Object> data, String condition) throws StorageException;

}
