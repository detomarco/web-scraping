package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Template;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

/**
 * Questa classe contiene metodi per recuperare ed inserire i template dal database
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class WebsiteManaging {
	
	private Storable storage;
	
	public WebsiteManaging(Storable storage){
		this.storage = storage;
	}
	
	/**
	 * Metodo che recupera dal database il sito internet corrispondente all'indirizzo fornito
	 * @param hostname
	 * @return Website
	 * @throws StorageException 
	 * @throws WebsiteNotFoundException
	 */
	public Website getWebsite(String hostname) throws StorageException, WebsiteNotFoundException{
		
		List<Map<String, String>> results;
		
		// Recupero website
		results = storage.get("websites", "address = '"+hostname+"'");
		
		if (results==null || results.isEmpty()){
			throw new WebsiteNotFoundException();
		}
		
		Website website = new Website(results.get(0));
		
		// Recupero template di articoli
		results = storage.get("article_templates", "fk_website = '"+website.getId()+"'");
		
		for(Map<String, String> current_result: results){
			website.addTemplate(new ArticleTemplate(current_result));
		}
		
		// Recupero template di elenchi
		results = storage.get("list_templates", "fk_website = '"+website.getId()+"'");
		
		for(Map<String, String> current_result: results){
			website.addTemplate(new ArticleListTemplate(current_result));
		}
		
		return website;
	}
	
	public int getWebsiteId(Website website) throws StorageException{
		
		List<Map<String, String>> results;
		
		results = storage.get("websites", "host = '"+website.getAddress()+"'");
		
		return Integer.parseInt(results.get(0).get("id"));
		
	}
	
	
	/**
	 * Metodo che recupera il template dal database
	 * @param website
	 * @param context
	 * @param is_list
	 * @return Template
	 * @throws TemplateNotFoundException
	 * @throws StorageException 
	 */
	public Template getTemplate(Website website, String context, boolean is_list) throws TemplateNotFoundException, StorageException{
		
		List<Map<String, String>> results = new LinkedList<>();
		String template_type;
		
		if(is_list) {
			template_type = "list_templates";
		} else {
			template_type = "article_templates";
		}
		
		results = storage.get(template_type, "(fk_website = '"+website.getId()+"' AND context_name ='"+context+"')");
		
		if (results==null || results.isEmpty()){
			throw new TemplateNotFoundException();
		}
		
		Template template;
		
		if(is_list) {
			template = new ArticleListTemplate(results.get(0));
		} else {
			template = new ArticleTemplate(results.get(0));
		}
		
		System.out.println("Template found on database!");
		
		return template;
		
	}
	
	/**
	 * Metodo che memorizza un nuovo sito web e, se ne contiene, i suoi template
	 * @param website
	 * @throws StorageException
	 */
	public void saveWebsite(Website website) throws StorageException {
		
		Map<String, Object> data = website.toMap();
		
		data.remove("id");
		
		@SuppressWarnings("unchecked")
		Set<ArticleTemplate> article_templates = (Set<ArticleTemplate>)data.remove("article.templates");
		
		@SuppressWarnings("unchecked")
		Set<ArticleListTemplate> list_templates = (Set<ArticleListTemplate>)data.remove("list_templates");
		
		storage.save("websites", data);
		
		for(ArticleTemplate template: article_templates){
			saveTemplate(template, website);
		}
		
		for(ArticleListTemplate template: list_templates){
			saveTemplate(template, website);
		}	}
	
	/**
	 * Metodo che memorizza un template relativo ad un dato sito web
	 * @param template
	 * @param website
	 * @throws StorageException
	 */
	public void saveTemplate(Template template, Website website) throws StorageException {
		
		Map<String, Object> data = new HashMap<>();
		
		data = template.toMap();
		
		data.put("fk_website", getWebsiteId(website));
		
		if (template instanceof ArticleTemplate){
			storage.save("article_templates", data);
		} else {
			storage.save("list_templates", data);
		}
	}
}
