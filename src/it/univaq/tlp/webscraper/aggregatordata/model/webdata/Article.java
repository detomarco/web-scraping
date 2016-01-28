package it.univaq.tlp.webscraper.aggregatordata.model.webdata;

import java.util.Date;
import java.util.Map;

/**
 * Questa classe &egrave un estensione di AggregatedData e rappresenta l'articolo recuperato dal database
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class Article extends AggregatedData {
	private int id;
	private int website_id;
	
	public Article(){ }
	
	public Article(int id, int website_id, String title, String heading, String eyelet, String summary, String text, String author, String source, String date){
		super(title, heading, eyelet, summary, text, author, source, date);
		this.id = id;
		this.website_id = website_id;
	}
	
	public Article(AggregatedData data){ }
	
	public Article(Map<String, String> from_storage){
		super(from_storage.get("title"), from_storage.get("heading"), from_storage.get("eyelet"), from_storage.get("summary"),
				from_storage.get("text"), from_storage.get("author"), from_storage.get("url"), from_storage.get("date"));
				
		this.id = Integer.parseInt(from_storage.get("id"));
		this.website_id = Integer.parseInt(from_storage.get("website_id"));

	}

	public int getId(){
		return this.id;
	}
	
	public int getWebsiteId(){
		return this.website_id;
	}
	
}
