package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.aggregatordata.URL;
import it.univaq.tlp.webscraper.aggregatordata.exception.ContextNotFoundException;
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
	 * @throws ResponseException 
	 */
	public int crawl(String source) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException, ResponseException{
		
		URL url = new URL(source); // Throws MalformedURLException
		
		
		// Recupero sito
		Website website =  website_manager.getWebsite(url.getHost()); // Throws WebsiteNotFoundException
		
		// Recupera tutti gli articoli trovati (opportunamente strutturati)
		Set<AggregatedData> new_articles = connector.collect(website, url, url.isList()); // Throws TemplateNotFoundException;
		
		// Recupera tutti gli articoli già inseriti nel website
		Set<Article> stored_articles = new LinkedHashSet<>();
		stored_articles = article_manager.getWebsiteArticles(website, "");
		
		// Salva solo gli articoli che non stono già stati trovati
		int saved_counter = 0;
		for(AggregatedData new_article : new_articles){
			if(!(stored_articles.contains(new_article)) && !new_article.getHeading().equals("") && !new_article.getText().equals("")){
				// Salva nuovo articolo
				article_manager.saveArticle(new_article, website);
				saved_counter ++;
			}
		}
		
		return saved_counter;
				
	}

}
