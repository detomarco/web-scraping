package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.List;
import java.util.Map;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.model.AggregatedData;

public class DataAggregator {
	
	private Storable storage;
	
	public DataAggregator(Storable storage){
		this.storage = storage;
	}
	
	public void aggregate(AggregatedData data){
		
		String source = data.getSource();
		
		List<Map<String, String>> results;
		try{
			results = storage.get("web_datas", "source = "+source);
		} catch (StorageException e){
			e.printStackTrace();
			
			return;
		}
		
	}
	
	
}
