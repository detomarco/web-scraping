package it.univaq.tlp.webscraper.aggregatordata.model.website;

import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe rappresenta il template dell'articolo recuperato dal web
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
	private String date_format;
	
	public ArticleTemplate(String context, String heading_selector, String eyelet_selector, String summary_selector, String text_selector, String author_selector, String date_selector, String date_format){
		super(context);
		this.heading_selector = heading_selector;
		this.eyelet_selector = eyelet_selector;
		this.summary_selector = summary_selector;
		this.text_selector = text_selector;
		this.author_selector = author_selector;
		this.date_selector = date_selector;
		this.date_format = date_format;
	}
	
	public ArticleTemplate(Map<String, String> from_storage){
		super(from_storage.get("context_name"));
		this.heading_selector = from_storage.get("heading_selector");
		this.eyelet_selector = from_storage.get("eyelet_selector");
		this.summary_selector = from_storage.get("summary_selector");
		this.text_selector = from_storage.get("text_selector");
		this.date_selector = from_storage.get("date_selector");
		this.date_format = from_storage.get("date_format");
		this.author_selector = from_storage.get("author_selector");
	}
	
	public String getHeadingSelector(){
		return this.heading_selector;
	}
	
	public String getEyeletSelector(){
		return this.eyelet_selector;
	}
	
	public String getSummarySelector(){
		return this.summary_selector;
	}
	
	public String getTextSelector(){
		return this.text_selector;
	}
	
	public String getAuthorSelector(){
		return this.author_selector;
	}
	
	public String getDateSelector(){
		return this.date_selector;
	}
	
	public String getDateFormat(){
		return this.date_format;
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("heading_selector", this.heading_selector);
		map.put("eyelet_selector", this.eyelet_selector);
		map.put("summary_selector", this.summary_selector);
		map.put("text_selector", this.text_selector);
		map.put("date_selector", this.date_selector);
		map.put("author_selector", this.author_selector);
		map.put("date_format", this.date_format);
		
		map.put("context", this.getContext());
		
		return map;
		
	}

}
