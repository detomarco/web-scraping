package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

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
	
	private DataAggregator aggregator;
	
	protected WebsiteManaging websiteManager;
	protected ArticleManaging articleManager;
	
	protected int last_insert;
	protected Storable storage;
	
	public UserInterface(Storable storage){
		
		// Connessione al databse
		this.storage = storage;
		this.aggregator = new DataAggregator(storage);
		this.websiteManager = new WebsiteManaging(storage);
		this.articleManager = new ArticleManaging(storage);
			
	} 
	
	public void scrap(String source) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException {
		this.last_insert = aggregator.crawl(source);
	}
	
	public void insertWebsite(Website website) throws StorageException, MalformedURLException, WebsiteAlreadyExistsException {
		websiteManager.saveWebsite(website);
	}
	
	public void insertTemplate(ArticleTemplate article, ArticleListTemplate article_list, String website_url) throws StorageException, MalformedURLException, WebsiteNotFoundException, ContextAlreadyExistsException, DataOmittedException {
		websiteManager.saveTemplate(article, article_list, website_url);
	}
	
	public List<Article> viewLastAddedArticles() throws StorageException {
		return articleManager.getTopArticles(last_insert);
	}
	
	public Set<Article> viewWebsiteArticles(String host, String context) throws StorageException, MalformedURLException, WebsiteNotFoundException, ContextNotFoundException {
		return articleManager.getWebsiteArticles(host, context);
	}
	
	public List<Article> viewArticles() throws StorageException {
		return articleManager.getArticles();
	}
	
	public abstract void run();
}
