package it.univaq.tlp.webscraper.controller;

import java.net.MalformedURLException;
import java.util.Set;

import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.controller.exception.ContextAlreadyExistsException;
import it.univaq.tlp.webscraper.controller.exception.DataOmittedException;
import it.univaq.tlp.webscraper.controller.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.controller.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.controller.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.controller.repository.Storable;
import it.univaq.tlp.webscraper.controller.repository.StorageException;
import it.univaq.tlp.webscraper.model.webdata.Article;
import it.univaq.tlp.webscraper.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.model.website.Website;
import it.univaq.tlp.webscraper.utility.URL;

/**
 * This class provides methods to hide implementative details towards view classes
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @aurhor Alessandro D'Errico
 *
 */
public class Controller {

	private DataAggregator aggregator;
	
	private WebsiteManaging website_manager;
	private ArticleManaging article_manager;
		
	/**
	* Constructor
	* @param storage, repository utilizzata nell'applicazione
	*/
	public Controller(Storable storage){
		
		// Connessione al databse
		this.aggregator = new DataAggregator(storage);
		this.website_manager = new WebsiteManaging(storage);
		this.article_manager = new ArticleManaging(storage);
			
	} 
	
	/**
	 * This method calls the crawler method of DataAggregator
	 * @param source
	 * @return number of new articles collected from given source
	 * @throws MalformedURLException
	 * @throws WebsiteNotFoundException
	 * @throws TemplateNotFoundException
	 * @throws StorageException
	 * @throws ResponseException
	 */
	public int scrap(String source) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException, ResponseException {
		return aggregator.crawl(source);
	}
	
	/**
	 * This method inserts a new website into the storage
	 * @param website
	 * @throws StorageException
	 * @throws MalformedURLException
	 * @throws WebsiteAlreadyExistsException
	 */
	public void insertWebsite(Website website) throws StorageException, MalformedURLException, WebsiteAlreadyExistsException {
		website_manager.saveWebsite(website);
	}
	
	/**
	 * This method inserts a new website into the storage
	 * @param article
	 * @param article_list
	 * @param website_url
	 * @throws StorageException
	 * @throws MalformedURLException
	 * @throws WebsiteNotFoundException
	 * @throws ContextAlreadyExistsException
	 * @throws DataOmittedException
	 */
	public void insertTemplate(ArticleTemplate article, ArticleListTemplate article_list, String website_url) throws StorageException, MalformedURLException, WebsiteNotFoundException, ContextAlreadyExistsException, DataOmittedException {
		website_manager.saveTemplate(article, article_list, website_url);
	}
	
	/**
	 * This method gets from the storage all articles from given websites 
	 * @param address
	 * @param context
	 * @return Set of articles
	 * @throws StorageException
	 * @throws MalformedURLException
	 * @throws WebsiteNotFoundException
	 */
	public Set<Article> viewWebsiteArticles(String address, String context) throws StorageException, MalformedURLException, WebsiteNotFoundException {
		
		URL url = new URL(address);
		Website website = website_manager.getWebsite(url.getHost());
		
		return article_manager.getWebsiteArticles(website, context);
	}
	
	/**
	 * This method gets from the storage all contexts from the given address
	 * @param address
	 * @return Set of strings representing contexts
	 * @throws StorageException
	 * @throws MalformedURLException
	 * @throws WebsiteNotFoundException
	 */
	public Set<String> viewContexts(String address) throws StorageException, MalformedURLException, WebsiteNotFoundException{
		URL url = new URL(address);
		Website website = website_manager.getWebsite(url.getHost());
		
		return article_manager.getWebsiteContexts(website);
	}

	/**
	 * This method gets from the storage all the contexts from the given website
	 * @param website
	 * @return Set of strings representing contexts
	 * @throws StorageException
	 */
	public Set<String> getWebsiteContexts(Website website) throws StorageException{
		return this.article_manager.getWebsiteContexts(website);
	}
	
	/**
	 * This method gets from the storage all contexts from the given website address
	 * @param website_url
	 * @return Set of strings representing contexts
	 * @throws StorageException
	 * @throws WebsiteNotFoundException
	 */
	public Set<String> getWebsiteContexts(String website_url) throws StorageException, WebsiteNotFoundException{
		return this.article_manager.getWebsiteContexts(this.website_manager.getWebsite(website_url));
	}
	
	/**
	 * This method gets from the storage all websites 
	 * @return Set of websites
	 * @throws StorageException
	 */
	public Set<Website> getAllWebsite() throws StorageException{
		return this.website_manager.getAllWebsite();
	}
	
	
	
}
