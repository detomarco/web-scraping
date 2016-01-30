package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.aggregatordata.URL;
import it.univaq.tlp.webscraper.aggregatordata.exception.ContextNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

public class ArticleManaging {

	private Storable storage;
	private WebsiteManaging website_manager;
	public ArticleManaging(Storable storage){
		this.storage = storage;
		website_manager = new WebsiteManaging(storage);
	}
	
	public Set<Article> getWebsiteArticles(Website website, String context) throws StorageException, WebsiteNotFoundException, MalformedURLException, ContextNotFoundException{

		if(!context.trim().equals("")){
			// Controlla se il contesto esiste
			Set<ArticleListTemplate> list_templates = website.getArticleListTemplates();
			boolean context_found = false;
			for(ArticleListTemplate list_template: list_templates){
				if(list_template.getContext().equals(context)) context_found = true;
			}
			if(!context_found) throw new ContextNotFoundException();
		}
		
		Set<Article> articles = new LinkedHashSet<>();
		Set<Map<String, String>> results;
		
		results = storage.get("articles", "fk_website = '" + website.getId()+"'");
		
		for(Map<String, String> current_result: results){
			URL url = new URL(current_result.get("url"));
			if(url.getContext().equals(context)){
				articles.add(new Article(current_result));
			}
			
		}
		
		return articles;
		
	}
	
	
	public Set<Article> getArticles() throws StorageException{
		
		Set<Article> articles = new LinkedHashSet<>();
		Set<Map<String, String>> results;
		
		results = storage.get("articles", "1 = '1'");
		
		for(Map<String, String> current_result: results){
			articles.add(new Article(current_result));
		}
		
		return articles;
	}
	
	public Set<Article> getTopArticles(int count) throws StorageException{
		
		Set<Article> articles = new LinkedHashSet<>();
		Set<Map<String, String>> results;
		
		results = storage.get("articles", "1 = '1'", count);
		
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
