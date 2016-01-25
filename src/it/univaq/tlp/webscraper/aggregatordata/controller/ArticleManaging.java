package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;

public class ArticleManaging {

	private Storable storage;
	
	public ArticleManaging(Storable storage){
		this.storage = storage;
	}
	
	public List<Article> getWebsiteArticles(Website website) throws StorageException{
		
		List<Article> articles = new LinkedList<>();
		List<Map<String, String>> results;
		
		results = storage.get("articles", "fk_website = '"+website.getId()+"'");
		
		for(Map<String, String> current_result: results){
			articles.add(new Article(current_result));
		}
		
		return articles;
		
	}
	
	
	public List<Article> getArticles() throws StorageException{
		
		List<Article> articles = new LinkedList<>();
		List<Map<String, String>> results;
		
		results = storage.get("articles", "1 = '1'");
		
		for(Map<String, String> current_result: results){
			articles.add(new Article(current_result));
		}
		
		return articles;
	}
	
	public List<Article> getTopArticles(int count) throws StorageException{
		
		List<Article> articles = new LinkedList<>();
		List<Map<String, String>> results;
		
		results = storage.get("articles", "1 = '1'");
		
		for(Map<String, String> current_result: results){
			articles.add(new Article(current_result));
		}
		
		return articles;
		
	}
	
	public void saveArticle(AggregatedData article, Website website) throws StorageException{
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("title", article.getTitle().replaceAll("'", "''"));
		data.put("heading", article.getHeading().replaceAll("'", "''"));
		data.put("summary", article.getSummary().replaceAll("'", "''"));
		data.put("eyelet", article.getEyelet().replaceAll("'", "''"));
		data.put("text", article.getText().replaceAll("'", "''"));
		data.put("author", article.getAuthor().replaceAll("'", "''"));
		data.put("date", article.getDate());
		data.put("url", article.getSource());
		data.put("fk_website", website.getId());
		
		storage.save("articles", data);
	}
}
