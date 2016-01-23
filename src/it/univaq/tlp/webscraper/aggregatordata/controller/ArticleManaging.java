package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;

public class ArticleManaging {

	private Storable storage;
	
	public ArticleManaging(Storable storage){
		this.storage = storage;
	}
	
	public List<Article> getWebsiteArticles(Website website){
		
		List<Article> articles = new LinkedList<>();
		List<Map<String, String>> results;
		
		try{
			results = storage.get("articles", "fk_website = '"+website.getId()+"'");
		} catch (StorageException e){
			e.printStackTrace();
			return null;
		}
		
		for(Map<String, String> current_result: results){
			articles.add(new Article(current_result));
		}
		
		return articles;
		
	}
}
