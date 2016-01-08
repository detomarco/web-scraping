package it.univaq.tlp.webscraper.aggregatordata.controller;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.model.AggregatedData;

public class DataAggregator {
	
	private Storable storage;
	
	public DataAggregator(Storable storage){
		this.storage = storage;
	}
	
	public void aggregate(AggregatedData data){
		
		String source = data.getSource();
		
	
		
	}
	
	
}
