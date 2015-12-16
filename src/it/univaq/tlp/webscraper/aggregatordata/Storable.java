package it.univaq.tlp.webscraper.aggregatordata;

import java.util.Map;

public interface Storable {
	
	public void save(String kind, Map<String, Object> data) throws Exception;
	
	public void get(String kind, String condition) throws Exception;
	
	public void updateStored(String kind, Map<String,Object> data, String condition) throws Exception;

}
