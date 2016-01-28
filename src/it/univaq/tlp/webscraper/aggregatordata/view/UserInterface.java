package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;

import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

public abstract class UserInterface {
	
	private DataAggregator aggregator;
	protected int last_insert;
	
	public UserInterface(Storable storage){
		this.aggregator = new DataAggregator(storage);
	} 
	
	public void scrap(String source, boolean is_list) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException{
		this.last_insert = aggregator.crawl(source, is_list);
	}
	
	public void insertWebsite(){
		
	}
	
	public void viewArticle(){
		
	}
	
	
}
