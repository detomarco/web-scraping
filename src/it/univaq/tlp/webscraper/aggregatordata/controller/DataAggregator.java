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
	
	public void aggregate(String source){
		
		List<Map<String, String>> results;
		try{
			results = storage.get("web_datas", "source = "+source);
		} catch (StorageException e){
			e.printStackTrace();
			
			return;
		}
		
	}
	
	/*
	 * Componente Data Aggregator:
	 * 
	 * 1) riceve in input un url da cui ricavare dati
	 * 2) controlla nel database quali sono gli ultimi dati registrati per quell'url
	 * 3) controlla nel database qual e' il template utilizzato dal sito web
	 * 4) chiama il connettore web per recuperare i dati
	 * 5) memorizza i nuovi dati nel database
	 */
	
	
}
