package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.List;
import java.util.Map;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;

public class DataAggregator {
	
	private Storable storage;
	private WebConnector connector;
	
	public DataAggregator(Storable storage){
		this.storage = storage;
		this.connector = new WebConnector();
	}
	
	public void aggregate(String source){
		
//		List<Map<String, String>> results;
		
		AggregatedData data = connector.collectArticleData(new ArticleTemplate(), source);
		
		System.out.println(data);
	}
	
	/*
	 * Componente Data Aggregator:
	 * 
	 * 1) riceve in input un url da cui ricavare dati
	 * 2) controlla nel database quali sono gli ultimi dati registrati per quell'url
	 * 3) controlla nel database qual e' il template utilizzato dal sito web
	 * 4) chiama il connettore web per recuperare i dati
	 * 5) memorizza i nuovi dati nel database
	 * 
	 */
	
	
}
