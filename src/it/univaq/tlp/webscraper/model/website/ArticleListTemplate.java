package it.univaq.tlp.webscraper.model.website;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents template for article listing web pages
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class ArticleListTemplate extends Template{

	private String article_link_selector;
	
	/**
	 * @param context
	 * @param article_link_selector
	 */
	public ArticleListTemplate(String context, String article_link_selector){
		super(context);
		this.article_link_selector = article_link_selector;
	}
	
	/**
	 * @param from_storage
	 */
	public ArticleListTemplate(Map<String, String> from_storage){
		this(from_storage.get("context_name"), from_storage.get("article_link_selector"));
	}
	
	/**
	 * @return css selector for links to listed articles
	 */
	public String getLinkSelector(){
		return this.article_link_selector;
	}
	
	@Override
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		
		map.put("article_link_selector", this.article_link_selector);
		map.put("context_name", this.context);
		
		return map;
	}
	
}
