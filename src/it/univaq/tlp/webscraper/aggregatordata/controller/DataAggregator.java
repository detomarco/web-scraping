package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Template;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;

public class DataAggregator {
	
	private Storable storage;
	private ConnectorInterface connector;
	private WebsiteManaging website_manager;
	
	public DataAggregator(Storable storage){
		this.website_manager = new WebsiteManaging(storage);
		this.connector = new WebConnector(website_manager);
		this.storage = storage;
	}

	public void crawl(String source, boolean is_list) throws MalformedURLException{
		
		//**** CONTROLLO E UNIFORMAZIONE URL ****//
		if(!(source.startsWith("http"))) {
			source = "http://" + source;
		}
		
		URL url = new URL(source); // Throws MalformedURLException
		
		String host = url.getHost();
		
		if(!(host.startsWith("www."))){
			host = "www." + host;
		}
		
		String context = "";
		
		
		//**** RECUPERO SITO ****//
		Website website =  website_manager.getWebsite(host);
				
		List<AggregatedData> data = connector.collect(website, source, is_list);
		
	}
	
	
}
