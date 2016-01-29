package it.univaq.tlp.webscraper.aggregatordata.model.website;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Website {
	
	private Integer id;
	private String name;
	private String address;
	private String description;
		
	private Set<ArticleTemplate> article_templates;
	private Set<ArticleListTemplate> list_templates;
	
	public Website(String name, String address, String description){
		this.name = name;
		this.address = address;
		this.description = description;
		this.article_templates = new HashSet<ArticleTemplate>();
		this.list_templates = new HashSet<ArticleListTemplate>();
	}
	
	public Website(Map<String, String> from_storage){
		this.id = Integer.parseInt(from_storage.get("id"));
		this.name = from_storage.get("name");
		this.address = from_storage.get("address");
		this.description = from_storage.get("description");
		this.article_templates = new HashSet<ArticleTemplate>();
		this.list_templates = new HashSet<ArticleListTemplate>();
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getDescription(){
		return this.description;
	}
		
	public Set<ArticleTemplate> getArticleTemplates(){
		return this.article_templates;
	}
	
	public Set<ArticleListTemplate> getArticleListTemplates(){
		return this.list_templates;
	}
	
	public void updateAddress(String address){
		this.address = address;
	}
	
	public void updateDescription(String description){
		this.description = description;
	}
	
	public void addTemplate(Template template){
		if (template instanceof ArticleTemplate){
			article_templates.add((ArticleTemplate)template);
		} else {
			list_templates.add((ArticleListTemplate)template);
		}
	}
	
	public int getTemplatesCount(){
		return article_templates.size()+list_templates.size();
	}
	
	@Override
	public String toString(){
		return name+" ("+address+"): "+description;
	}
	
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
