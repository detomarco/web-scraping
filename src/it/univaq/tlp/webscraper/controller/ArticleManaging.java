package it.univaq.tlp.webscraper.controller;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.controller.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.controller.repository.Storable;
import it.univaq.tlp.webscraper.controller.repository.StorageException;
import it.univaq.tlp.webscraper.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.model.webdata.Article;
import it.univaq.tlp.webscraper.model.website.Website;
import it.univaq.tlp.webscraper.utility.URL;

class ArticleManaging {

	private Storable storage;
	
	ArticleManaging(Storable storage){
		this.storage = storage;
	}
	
	/**
	* Visualizza tutti gli articoli di un sito web
	* @param url, indirizzo del sito web
	* @param context, contesto degli articoli da recuperare (se stringa vuota, recupera tutti gli articoli del sito web)
	* @return lista di articoli trovati
	*/
	public Set<Article> getWebsiteArticles(Website website, String context) throws StorageException, WebsiteNotFoundException, MalformedURLException{

		Set<Article> articles = new LinkedHashSet<>();
		Set<Map<String, String>> results;
		
		results = storage.get("articles", "fk_website = '" + website.getId()+"'");
		
		for(Map<String, String> current_result: results){
			URL url = new URL(current_result.get("url"));
			if(context.trim().equals("") || url.getContext().equals(context)){
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
	
	public Set<Article> getLastArticles(int count) throws StorageException{
		
		Set<Article> articles = new LinkedHashSet<>();
		Set<Map<String, String>> results;
		
		results = storage.get("articles", "1 = '1'", count);
		
		for(Map<String, String> current_result: results){
			articles.add(new Article(current_result));
		}
		
		return articles;
		
	}
	
	public Set<String> getWebsiteContexts(Website website) throws StorageException{
		
		Set<String> contexts = new LinkedHashSet<>();
		Set<Map<String, String>> results = storage.getGrouped("articles", "fk_website = '"+ website.getId() + "'", "context_name");
		
		for(Map<String, String> current_result: results){
			contexts.add(current_result.get("context_name"));
		}
		
		return contexts;
		
	}
	
	public Article getArticleByUrl(String url) throws StorageException{
		
		return new Article(storage.get("articles", "url = '" + url + "'").iterator().next());
		
		
	}
	public void saveArticle(AggregatedData article, Website website) throws StorageException{
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("title", article.getTitle());
		data.put("heading", article.getHeading());
		data.put("summary", article.getSummary());
		data.put("eyelet", article.getEyelet());
		data.put("text", article.getText());
		data.put("author", article.getAuthor());
		data.put("date", article.getDate());
		data.put("url", article.getSource());
		data.put("fk_website", website.getId());
		data.put("context_name", article.getContext());
		storage.save("articles", data);
		
		// Recupera id dell'articolo appena inserito
		int id_art = this.getArticleByUrl(article.getSource()).getId();
		
		Map<String, Object> metadata = new HashMap<>();
		metadata.put("fk_article", id_art);
		for(Map.Entry<String, Object> entry:  article.getMetadata().entrySet()){
			metadata.put("name",entry.getKey());
			metadata.put("content", entry.getValue());
			storage.save("metadata", metadata);
		}
		
	}
}