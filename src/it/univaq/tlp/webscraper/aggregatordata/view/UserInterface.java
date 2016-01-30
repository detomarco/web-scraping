package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.Set;

import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.aggregatordata.URL;
import it.univaq.tlp.webscraper.aggregatordata.controller.ArticleManaging;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.aggregatordata.controller.WebsiteManaging;
import it.univaq.tlp.webscraper.aggregatordata.exception.ContextAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.ContextNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.DataOmittedException;
import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

public abstract class UserInterface {
	
	/**
	* Interfaccia per la gestione della UI
	* @author Marco De Toma
	* @author Alessandro D'Errico
	* @author Gianluca Filippone
	*/	

	private DataAggregator aggregator;
	
	protected WebsiteManaging website_manager;
	protected ArticleManaging article_manager;
	
	protected int last_insert;
	protected Storable storage;
	
	/**
	* Metodo costruttore
	* @param storage, repository utilizzata nell'applicazione
	*/

	public UserInterface(Storable storage){
		
		// Connessione al databse
		this.storage = storage;
		this.aggregator = new DataAggregator(storage);
		this.website_manager = new WebsiteManaging(storage);
		this.article_manager = new ArticleManaging(storage);
			
	} 
	
	/**
	* Esegue il web-scraping su un determinato url
	* @param url, indirizzo web da analizzare
	 * @throws ResponseException 
	*/
	public void scrap(String source) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException, ResponseException {
		this.last_insert = aggregator.crawl(source);
	}
	
	/**
	* Inserisce un nuovo sito web
	* @param website, sito web da inserire
	*/
	public void insertWebsite(Website website) throws StorageException, MalformedURLException, WebsiteAlreadyExistsException {
		website_manager.saveWebsite(website);
	}
	
	/**
	* Inserisce un nuovo template
	* @param article, template dell'articolo
	* @param arcible_list, template per la lista delgli articoli
	* @param website_url, indirizzo del sito web a cui inserire il nuovo template
	*/
	public void insertTemplate(ArticleTemplate article, ArticleListTemplate article_list, String website_url) throws StorageException, MalformedURLException, WebsiteNotFoundException, ContextAlreadyExistsException, DataOmittedException {
		website_manager.saveTemplate(article, article_list, website_url);
	}
	
	/**
	 * Visualizza gli articoli aggiunti dopo l'ultima ricerca
	 * @return
	 * @throws StorageException
	 */
	public Set<Article> viewLastAddedArticles() throws StorageException {
		return article_manager.getTopArticles(last_insert);
	}
	
	/**
	* Visualizza tutti gli articoli di un sito web
	* @param url, indirizzo del sito web
	* @param context, contesto degli articoli da recuperare (se stringa vuota, recupera tutti gli articoli del sito web)
	* @return lista di articoli trovati
	*/
	public Set<Article> viewWebsiteArticles(String address, String context) throws StorageException, MalformedURLException, WebsiteNotFoundException {
		
		URL url = new URL(address);
		Website website = website_manager.getWebsite(url.getHost());
		
		return article_manager.getWebsiteArticles(website, context);
	}
	
	/**
	 * Visualizza tutti i contesti relativi agli articoli salvati per quel sito web
	 * @param address
	 * @return
	 * @throws StorageException
	 * @throws MalformedURLException
	 * @throws WebsiteNotFoundException
	 */
	public Set<String> viewContexts(String address) throws StorageException, MalformedURLException, WebsiteNotFoundException{
		URL url = new URL(address);
		Website website = website_manager.getWebsite(url.getHost());
		
		return article_manager.getWebsiteContexts(website);
	}

	
	public abstract void run();
}
