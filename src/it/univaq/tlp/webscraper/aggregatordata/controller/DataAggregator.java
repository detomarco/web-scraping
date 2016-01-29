package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import it.univaq.tlp.webscraper.aggregatordata.URL;
import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

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
	public int crawl(String source) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException{
		
		URL url = new URL(source); // Throws MalformedURLException
		//repubblica.it/politica
		System.out.println(url.getSource());
		// Recupero sito
		Website website =  website_manager.getWebsite(url.getHost()); // Throws WebsiteNotFoundException
		
		// Recupera tutti gli articoli trovati (opportunamente strutturati)
		List<AggregatedData> data = connector.collect(website, url, url.isList()); // Throws TemplateNotFoundException;
		
		// Recupera tutti gli articoli già inseriti nel website
		List<Article> stored_articles = article_manager.getWebsiteArticles(website);
		
		// Salva solo gli articoli che non stono già stati trovati
		Iterator<AggregatedData> iter = data.iterator();
		int saved_counter = 0;
		while(iter.hasNext()){
			AggregatedData current_article = iter.next();
			if(!(stored_articles.contains(current_article)) && !current_article.getHeading().equals("") && !current_article.getText().equals("")){
				// Salva nuovo articolo
				article_manager.saveArticle(current_article, website);
				saved_counter ++;
			}
		}
		
		return saved_counter;
				
	}

}
