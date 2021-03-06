package it.univaq.tlp.webscraper.model.website;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the template for article pages
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class ArticleTemplate extends Template{
	
	private String heading_selector;
	private String eyelet_selector;
	private String summary_selector;
	private String text_selector;
	private String date_selector;
	private String author_selector;
	
	/**
	 * 
	 * @param context
	 * @param heading_selector
	 * @param eyelet_selector
	 * @param summary_selector
	 * @param text_selector
	 * @param author_selector
	 * @param date_selector
	 */
	public ArticleTemplate(String context, String heading_selector, String eyelet_selector, String summary_selector, String text_selector, String author_selector, String date_selector){
		super(context);
		this.heading_selector = heading_selector;
		this.eyelet_selector = eyelet_selector;
		this.summary_selector = summary_selector;
		this.text_selector = text_selector;
		this.author_selector = author_selector;
		this.date_selector = date_selector;
	}
	
	/**
	 *
	 * @param from_storage
	 */
	public ArticleTemplate(Map<String, String> from_storage){
		super(from_storage.get("context_name"));
		this.heading_selector = from_storage.get("heading_selector");
		this.eyelet_selector = from_storage.get("eyelet_selector");
		this.summary_selector = from_storage.get("summary_selector");
		this.text_selector = from_storage.get("text_selector");
		this.date_selector = from_storage.get("date_selector");
		this.author_selector = from_storage.get("author_selector");
	}
	
	/**
	 * @return css selector for article heading
	 */
	public String getHeadingSelector(){
		return this.heading_selector;
	}
	
	/**
	 * @return css selector for article eyelet
	 */
	public String getEyeletSelector(){
		return this.eyelet_selector;
	}
	
	/**
	 * @return css selector for article summary
	 */
	public String getSummarySelector(){
		return this.summary_selector;
	}
	
	/**
	 * @return css selecotr for article text
	 */
	public String getTextSelector(){
		return this.text_selector;
	}
	
	/**
	 * @return css selector for article author
	 */
	public String getAuthorSelector(){
		return this.author_selector;
	}
	
	/**
	 * @return css selector for article date
	 */
	public String getDateSelector(){
		return this.date_selector;
	}
	
	@Override
	/**
	 * This method converts the template object into Map
	 */
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		
		map.put("heading_selector", this.heading_selector);
		map.put("eyelet_selector", this.eyelet_selector);
		map.put("summary_selector", this.summary_selector);
		map.put("text_selector", this.text_selector);
		map.put("date_selector", this.date_selector);
		map.put("author_selector", this.author_selector);
		
		map.put("context_name", this.context);
		
		return map;
		
	}

}
