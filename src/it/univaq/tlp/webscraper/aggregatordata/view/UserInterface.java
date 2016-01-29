package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.List;
import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.controller.ArticleManaging;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.aggregatordata.controller.WebsiteManaging;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Template;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

public class UserInterface {
	
	private DataAggregator aggregator;
	
	protected WebsiteManaging websiteManager;
	protected ArticleManaging articleManager;
	
	protected int last_insert;
	
	protected UserInterface(Storable storage){
		this.aggregator = new DataAggregator(storage);
		this.websiteManager = new WebsiteManaging(storage);
		this.articleManager = new ArticleManaging(storage);	
	} 
	
	protected void scrap(String source, boolean is_list) throws MalformedURLException, WebsiteNotFoundException, TemplateNotFoundException, StorageException {
		this.last_insert = aggregator.crawl(source, is_list);
	}
	
	protected void insertWebsite(Website website) throws StorageException {
		websiteManager.saveWebsite(website);
	}
	
	protected void insertTemplate(Template template, Website website) throws StorageException {
		websiteManager.saveTemplate(template, website);
	}
	
	protected List<Article> viewLastAddedArticles() throws StorageException {
		return articleManager.getTopArticles(last_insert);
	}
	
	protected List<Article> viewWebsiteArticles(Website website) throws StorageException {
		return articleManager.getWebsiteArticles(website);
	}
	
	protected List<Article> viewArticles() throws StorageException {
		return articleManager.getArticles();
	}
}
