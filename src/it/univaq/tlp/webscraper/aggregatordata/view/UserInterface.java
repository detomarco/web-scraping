package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.List;

import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.controller.ArticleManaging;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.aggregatordata.controller.WebsiteManaging;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Template;
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
	
	public void insertWebsite(Website website) throws StorageException {
		websiteManager.saveWebsite(website);
	}
	
	public void insertTemplate(Template template, Website website) throws StorageException {
		websiteManager.saveTemplate(template, website);
	}
	
	public List<Article> viewLastAddedArticles() throws StorageException {
		return articleManager.getTopArticles(last_insert);
	}
	
	public List<Article> viewWebsiteArticles(Website website) throws StorageException {
		return articleManager.getWebsiteArticles(website);
	}
	
	public List<Article> viewArticles() throws StorageException {
		return articleManager.getArticles();
	}
	
	public Storable getStorage() {
		return this.storage;
	}
	
	public abstract void run();
}
