package it.univaq.tlp.webscraper.model.webdata;

import java.util.Map;

/**
 * Classe per la gestione di un articolo recuperato dal database
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class Article extends AggregatedData {
	private int id;
	private int website_id;
	
	
	/**
	 * 
	 * @param id
	 * @param website_id
	 * @param context
	 * @param title
	 * @param heading
	 * @param eyelet
	 * @param summary
	 * @param text
	 * @param author
	 * @param source
	 * @param date
	 */
	public Article(int id, int website_id, String context, String title, String heading, String eyelet, String summary, String text, String author, String source, String date){
		super(context, title, heading, eyelet, summary, text, author, source, date);
		this.id = id;
		this.website_id = website_id;
	}
	
	/**
	 * 
	 * @param data
	 */
	public Article(AggregatedData data){ }
	
	/**
	 * 
	 * @param from_storage
	 */
	public Article(Map<String, String> from_storage){
		super(from_storage.get("context_name"), from_storage.get("title"), from_storage.get("heading"), from_storage.get("eyelet"), from_storage.get("summary"),
				from_storage.get("text"), from_storage.get("author"), from_storage.get("url"), from_storage.get("date"));
				

		this.id = Integer.parseInt(from_storage.get("id"));
		this.website_id = Integer.parseInt(from_storage.get("fk_website"));

	}

	/**
	 * 
	 * @return article id
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * 
	 * @return article website id
	 */
	public int getWebsiteId(){
		return this.website_id;
	}
	
}
