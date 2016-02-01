package it.univaq.tlp.webscraper.controller;

import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.controller.exception.StorageException;

public interface Storable {
	
	public void connect() throws StorageException; 
	
	public void save(String kind, Map<String, Object> data) throws StorageException;
	
	public Set<Map<String, String>> get(String kind, String condition) throws StorageException;
	
	public Set<Map<String, String>> get(String kind, String condition, int count) throws StorageException;
	
	public void updateStored(String kind, Map<String,Object> data, String condition) throws StorageException;

	public Set<Map<String, String>> getGrouped(String kind, String condition, String to_group) throws StorageException;

}
