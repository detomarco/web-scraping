package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.List;
import java.util.Map;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Template;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;

public class WebsiteManaging {
	
	private Storable storage;
	
	public WebsiteManaging(Storable storage){
		this.storage = storage;
	}
	
	public Website getWebsite(String hostname){
		
		List<Map<String, String>> results;
		
		try {
			results = storage.get("websites", "address = '"+hostname+"'");
		} catch (StorageException e) {
			e.printStackTrace();
			return null;
		}
		
		return new Website(results.get(0));		
		
	}
	
	
	
	public Template getTemplate(Website website, String context, boolean is_list){
		
		List<Map<String, String>> results;
		String template_type;
		
		if(is_list) {
			template_type = "list_templates";
		} else {
			template_type = "article_templates";
		}
		
		try {
			results = storage.get(template_type, "fk_website = '"+website.getId()+"' AND context_name = '"+context+"'");
		} catch (StorageException e) {
			e.printStackTrace();
			return null;
		}
		
		Template template;
		
		if(is_list) {
			template = new ArticleListTemplate(results.get(0));
		} else {
			template = new ArticleTemplate(results.get(0));
		}
		
		return template;
		
	}
}
