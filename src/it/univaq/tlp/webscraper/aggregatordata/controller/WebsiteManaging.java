package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.aggregatordata.URL;
import it.univaq.tlp.webscraper.aggregatordata.exception.ContextAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.DataOmittedException;
import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
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
	
	public int getWebsiteId(Website website) throws StorageException{
		
		Set<Map<String, String>> results;
		
		results = storage.get("websites", "host = '"+website.getAddress()+"'");
		Iterator<Map<String, String>> iter = results.iterator();
		return Integer.parseInt(((Map<String, String>) iter.next()).get("id"));
		
	}
	
	public Set<Website> getAllWebsite() throws StorageException{
		Set<Map<String, String>> results = storage.get("websites", " 1=1");
		Set<Website> data = new LinkedHashSet<>();
		
		for(Map<String, String> element: results){
			data.add(new Website(element.get("name"), element.get("address"), element.get("description")));
		}
		
		return data;
		
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
		
		System.out.println("Template found on database!");
		
		return template;
		
	}
	
	/**
	 * Metodo che memorizza un nuovo sito web e, se ne contiene, i suoi template
	 * @param website
	 * @throws StorageException
	 * @throws MalformedURLException 
	 * @throws WebsiteAlreadyExistsException 
	 */
	public void saveWebsite(Website website) throws StorageException, MalformedURLException, WebsiteAlreadyExistsException {
		
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
	 * Metodo che memorizza un template relativo ad un dato sito web
	 * @param template
	 * @param website
	 * @throws StorageException
	 * @throws WebsiteNotFoundException 
	 * @throws MalformedURLException 
	 * @throws ContextAlreadyExistsException 
	 * @throws DataOmittedException 
	 */
	public void saveTemplate(ArticleTemplate article, ArticleListTemplate article_list, String website_url) throws StorageException, WebsiteNotFoundException, MalformedURLException, ContextAlreadyExistsException, DataOmittedException {
		URL url = new URL(website_url);
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
