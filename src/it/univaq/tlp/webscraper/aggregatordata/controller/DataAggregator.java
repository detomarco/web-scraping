package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;
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
	
	private ConnectorInterface connector;
	private WebsiteManaging website_manager;
	private ArticleManaging article_manager;
	
	public DataAggregator(Storable storage){
		this.website_manager = new WebsiteManaging(storage);
		this.article_manager = new ArticleManaging(storage);
		this.connector = new WebConnector();
	}
	
	/**
	 * Metodo che recupera informazioni dall'URL e lancia WebConnector.java
	 * @param source
	 * @param is_list
	 * @return int
	 * @throws MalformedURLException
	 * @throws StorageException 
	 */
	public int crawl(String source, boolean is_list) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException{
		
		URL url = new URL(URLUtility.conformURL(source)); // Throws MalformedURLException

		// Recupero sito
		Website website =  website_manager.getWebsite(URLUtility.getHost(url)); // Throws WebsiteNotFoundException
		
		System.out.println("Sito web non trovato! + ID:"+website.getId());
		System.out.println(website);
		
		System.out.println("Sono stati trovati "+website.getTemplatesCount()+" in questo sito web");
		
		List<AggregatedData> data = connector.collect(website, url, is_list); // Throws TemplateNotFoundException;
		
		// Inserimento dei dati nella repository
		return updateCollected(data, website);
				
	}
	
	/**
	 * Metodo che inserisce i nuovi articoli nella repository
	 * @param data
	 * @return il numero degli articoli inseriti
	 * @throws StorageException 
	 */
	private int updateCollected(List<AggregatedData> just_collected, Website website) throws StorageException{
		
		List<Article> stored_articles = article_manager.getWebsiteArticles(website);
		
		Iterator<AggregatedData> it = just_collected.iterator();
		
		int saved_counter = 0;
		
		while(it.hasNext()){
			AggregatedData current_article = it.next();
			if(!(stored_articles.contains(current_article))){
				article_manager.saveArticle(current_article, website);
				saved_counter ++;
			}
		}
		
		return saved_counter;
		
	}
	
	
}
