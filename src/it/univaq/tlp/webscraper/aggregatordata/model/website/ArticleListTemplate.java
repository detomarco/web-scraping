package it.univaq.tlp.webscraper.aggregatordata.model.website;

import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe rappresenta il template per le 
 * pagine contenenti gli elenchi degli articoli
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class ArticleListTemplate extends Template{

	private String article_link_selector;
	
	public ArticleListTemplate(String context, String article_link_selector){
		super(context);
		this.article_link_selector = article_link_selector;
	}
	
	public ArticleListTemplate(Map<String, String> from_storage){
		this(from_storage.get("context_name"), from_storage.get("article_link_selector"));
	}
	
	public String getLinkSelector(){
		return this.article_link_selector;
	}
	
	@Override
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		
		map.put("article_link_selector", this.article_link_selector);
		map.put("context", this.getContext());
		
		return map;
	}
	
}
