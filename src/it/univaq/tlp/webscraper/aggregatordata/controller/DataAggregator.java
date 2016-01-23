package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.URLUtility;
import it.univaq.tlp.webscraper.aggregatordata.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;

/**
 * Questa classe si occupa di salvare gli articoli recuperati dal web.
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico 
 */
public class DataAggregator {
	
	private Storable storage;
	private ConnectorInterface connector;
	private WebsiteManaging website_manager;
	private ArticleManaging article_manager;
	
	public DataAggregator(Storable storage){
		this.website_manager = new WebsiteManaging(storage);
		this.article_manager = new ArticleManaging(storage);
		this.connector = new WebConnector();
		this.storage = storage;
	}
	
	/**
	 * Metodo che recupera informazioni dall'URL e lancia WebConnector.java
	 * @param source
	 * @param is_list
	 * @throws MalformedURLException
	 */
	public void crawl(String source, boolean is_list) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException{
		
		URL url = new URL(URLUtility.conformURL(source)); // Throws MalformedURLException

		// Recupero sito
		Website website =  website_manager.getWebsite(URLUtility.getHostFromURL(url)); // Throws WebsiteNotFoundException
		
		System.out.println("Website found on database! + ID:"+website.getId());
		System.out.println(website);
		
		System.out.println("Found "+website.getTemplatesCount()+" templates for this website");
		
		List<AggregatedData> data = null;

		data = connector.collect(website, url, is_list); // Throws TemplateNotFoundException
		
	}
	
	/**
	 * Metodo che aggiorna gli articoli salvati salvando quelli appena recuperati dal web connector 
	 * @param data
	 */
	private void updateCollected(List<AggregatedData> data, Website website){
		
		List<Article> articles = article_manager.getWebsiteArticles(website);
				
	}
	
	
}
