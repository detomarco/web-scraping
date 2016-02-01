package it.univaq.tlp.webscraper.controller;

import java.net.MalformedURLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.controller.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.controller.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.controller.repository.StorageException;
import it.univaq.tlp.webscraper.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.model.webdata.Article;
import it.univaq.tlp.webscraper.model.website.Website;
import it.univaq.tlp.webscraper.utility.URL;

/**
 * This class provides methods to collect data from websites
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico 
 */
class DataAggregator {
	
	private ConnectorInterface connector;
	private WebsiteManaging website_manager;
	private ArticleManaging article_manager;
	
	/**
	 * Constructor method
	 * @param storage
	 */
	DataAggregator(WebsiteManaging website_manager, ArticleManaging article_manager){
		this.website_manager = website_manager;
		this.article_manager = article_manager;
		this.connector = new WebConnector();
	}
	
	/**
	 * This method recovers datas from given source and stores new articles into the storage
	 * @param source
	 * @return number of new articles inserted into the storage
	 * @throws MalformedURLException
	 * @throws WebsiteNotFoundException
	 * @throws TemplateNotFoundException
	 * @throws StorageException
	 * @throws ResponseException
	 * @throws NotFound 
	 */
	public int crawl(String source) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException, ResponseException, NotFound{
		
		URL url = new URL(source); // Throws MalformedURLException
		
		// Recupero sito
		Website website =  website_manager.getWebsite(url.getHost()); // Throws WebsiteNotFoundException
		
		// Recupera tutti gli articoli trovati (opportunamente strutturati)
		Set<AggregatedData> new_articles = connector.collect(website, url); // Throws TemplateNotFoundException;
		
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
