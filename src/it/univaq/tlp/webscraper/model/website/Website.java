package it.univaq.tlp.webscraper.model.website;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.univaq.tlp.webscraper.utility.URL;

/**
 * This class represents websites
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
public class Website {
	
	private Integer id;
	private String name;
	private String address;
	private String description;
		
	private Set<ArticleTemplate> article_templates;
	private Set<ArticleListTemplate> list_templates;
	
	/**
	 * 
	 * @param name
	 * @param address
	 * @param description
	 */
	public Website(String name, String address, String description) throws MalformedURLException{
		this.name = name;		
		this.address = new URL(address).getHost();
		this.description = description;
		this.article_templates = new HashSet<ArticleTemplate>();
		this.list_templates = new HashSet<ArticleListTemplate>();
	}
	
	/**
	 * 
	 * @param from_storage
	 */
	public Website(Map<String, String> from_storage){
		this.id = Integer.parseInt(from_storage.get("id"));
		this.name = from_storage.get("name");
		this.address = from_storage.get("address");
		this.description = from_storage.get("description");
		this.article_templates = new HashSet<ArticleTemplate>();
		this.list_templates = new HashSet<ArticleListTemplate>();
	}
	
	/**
	 * 
	 * @return website id
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * 
	 * @return website name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @return website address
	 */
	public String getAddress(){
		return this.address;
	}
	
	/**
	 * 
	 * @return website description
	 */
	public String getDescription(){
		return this.description;
	}
		
	/**
	 * 
	 * @return set of all article templates stored for the website
	 */
	public Set<ArticleTemplate> getArticleTemplates(){
		return this.article_templates;
	}
	
	/**
	 * 
	 * @return all list templates stored for the website
	 */
	public Set<ArticleListTemplate> getArticleListTemplates(){
		return this.list_templates;
	}
	
	/**
	 * This method update website address
	 * @param address
	 */
	public void updateAddress(String address){
		this.address = address;
	}
	
	/**
	 * This method updates website description
	 * @param description
	 */
	public void updateDescription(String description){
		this.description = description;
	}
	
	/**
	 * This method adds a new template to the website
	 * @param template
	 */
	public void addTemplate(Template template){
		if (template instanceof ArticleTemplate){
			article_templates.add((ArticleTemplate)template);
		} else {
			list_templates.add((ArticleListTemplate)template);
		}
	}
	
	/**
	 * 
	 * @return number of templates stored for the website
	 */
	public int getTemplatesCount(){
		return article_templates.size()+list_templates.size();
	}
	
	@Override
	public String toString(){
		return name+" ("+address+"): "+description;
	}
	
	/**
	 * 
	 * @return Map representing website object
	 */
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		
		map.put("id", this.id);
		map.put("name", this.name);
		map.put("address", this.address);
		map.put("description", this.description);
		
		map.put("article_templates", this.article_templates);
		map.put("list_templates", this.list_templates);
		
		return map;
	}
}
