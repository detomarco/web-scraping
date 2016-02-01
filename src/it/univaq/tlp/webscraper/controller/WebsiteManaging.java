package it.univaq.tlp.webscraper.controller;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.controller.exception.ContextAlreadyExistsException;
import it.univaq.tlp.webscraper.controller.exception.DataOmittedException;
import it.univaq.tlp.webscraper.controller.exception.StorageException;
import it.univaq.tlp.webscraper.controller.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.controller.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.controller.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.model.website.Template;
import it.univaq.tlp.webscraper.model.website.Website;
import it.univaq.tlp.webscraper.utility.URL;

/**
 * This class provides methods to manage websites and templates
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
class WebsiteManaging {
	
	private Storable storage;
	
	WebsiteManaging(Storable storage){
		this.storage = storage;
	}
	
	/**
	 * This method recovers from the storage the website having the given hostname
	 * @param hostname
	 * @return website
	 * @throws StorageException
	 * @throws WebsiteNotFoundException
	 */
	public Website getWebsite(String hostname) throws StorageException, WebsiteNotFoundException{
		
		Set<Map<String, String>> results;
		
		// Recupero website
		results = storage.get("websites", "address = '"+hostname+"'");
		
		if (results==null || results.isEmpty()){
			throw new WebsiteNotFoundException();
		}
		
		Iterator<Map<String, String>> iter = results.iterator();
		Website website = new Website((Map<String, String>) iter.next());
		
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
	
	/**
	 * This methods recovers from the storage the id of the given website
	 * @param website
	 * @return website id
	 * @throws StorageException
	 */
	public int getWebsiteId(Website website) throws StorageException{
		
		Set<Map<String, String>> results;
		
		results = storage.get("websites", "host = '"+website.getAddress()+"'");
		Iterator<Map<String, String>> iter = results.iterator();
		return Integer.parseInt(((Map<String, String>) iter.next()).get("id"));
		
	}
	
	/**
	 * This methods recovers all websites stored. Note: templates for websites will not be recovered
	 * @return Set of website
	 * @throws StorageException
	 */
	public Set<Website> getAllWebsite() throws StorageException{
		Set<Map<String, String>> results = storage.get("websites", " 1=1");
		Set<Website> data = new LinkedHashSet<>();
		
		for(Map<String, String> element: results){
			data.add(new Website(element));
		}
		
		return data;
		
	}
	
	/**
	 * 
	 * @param website
	 * @return all contexts available for given website
	 * @throws StorageException
	 */
	public Set<String> getWebsiteContexts(Website website) throws StorageException{
		
		Set<String> contexts = new LinkedHashSet<>();
		Set<Map<String, String>> results = storage.getGrouped("articles", "fk_website = '"+ website.getId() + "'", "context_name");
		
		for(Map<String, String> current_result: results){
			contexts.add(current_result.get("context_name"));
		}
		
		return contexts;
		
	}
	
	/**
	 * This method recovers from the storage the template for given website and context
	 * @param website
	 * @param context
	 * @param is_list
	 * @return template for given context
	 * @throws TemplateNotFoundException
	 * @throws StorageException
	 */
	public Template getTemplate(Website website, String context, boolean is_list) throws TemplateNotFoundException, StorageException{
		
		Set<Map<String, String>> results = new LinkedHashSet<>();
		String template_type;
		
		if(is_list) {
			template_type = "list_templates";
		} else {
			template_type = "article_templates";
		}
		
		results = storage.get(template_type, "(fk_website = '"+website.getId()+"' AND context ='"+context+"')");
		
		if (results==null || results.isEmpty()){
			throw new TemplateNotFoundException();
		}
		
		Template template;
		Iterator<Map<String, String>> iter = results.iterator();
		if(is_list) {
			template = new ArticleListTemplate((Map<String, String>) iter.next());
		} else {
			template = new ArticleTemplate((Map<String, String>) iter.next());
		}
				
		return template;
		
	}
	
	/**
	 * This method stores the website and its templates
	 * @param website
	 * @throws StorageException
	 * @throws MalformedURLException
	 * @throws WebsiteAlreadyExistsException
	 */
	public void saveWebsite(Website website) throws StorageException, MalformedURLException, WebsiteAlreadyExistsException, MalformedURLException {
		
		URL url = new URL(website.getAddress());
		
		try {
			
			getWebsite(url.getHost());
			
			// Se il website è già esistente, solleva l'eccezione
			throw new WebsiteAlreadyExistsException();
			
		// Se non è stato trovato nessun sito con lo stesso url
		} catch (WebsiteNotFoundException e) {
			
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("name", website.getName());
			data.put("address", website.getAddress());
			data.put("description", website.getDescription());
			
			storage.save("websites", data);
		}
		
	}
	
	/**
	 * This method stores the template for the given website
	 * @param article
	 * @param article_list
	 * @param website_url
	 * @throws StorageException
	 * @throws WebsiteNotFoundException
	 * @throws MalformedURLException
	 * @throws ContextAlreadyExistsException
	 * @throws DataOmittedException
	 */
	public void saveTemplate(ArticleTemplate article, ArticleListTemplate article_list, String website_url) throws StorageException, WebsiteNotFoundException, MalformedURLException, ContextAlreadyExistsException, DataOmittedException {
		
		URL.validate(website_url);
		
		Website website = this.getWebsite(website_url);
		
		// Controlla se il contesto è già stato inserito
		Set<ArticleListTemplate> list_templates = website.getArticleListTemplates();
		for(ArticleListTemplate list_template: list_templates){
			if(list_template.getContext().equals(article_list.getContext())) throw new ContextAlreadyExistsException();
			
		}
		
		if(article.getHeadingSelector().equals("") || article.getTextSelector().equals("")) throw new DataOmittedException();
		
		// Inserimento del template list
		Map<String, Object> data_list = article_list.toMap();
		data_list.put("fk_website", website.getId());
		storage.save("list_templates", data_list);
		
		// Inserimento del template dell'articolo
		Map<String, Object> data_article = article.toMap();
		data_article.put("fk_website", website.getId());
		storage.save("article_templates", data_article);
		
		website.addTemplate(article);
		website.addTemplate(article_list);
	}
}
