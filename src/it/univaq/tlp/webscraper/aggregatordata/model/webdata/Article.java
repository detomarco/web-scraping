package it.univaq.tlp.webscraper.aggregatordata.model.webdata;

import java.util.Date;

public class Article extends AggregatedData {
	private int id;
	private int website_id;
	
	public Article(){ }
	
	public Article(int id, int website_id, String title, String heading, String eyelet, String summary, String text, String author, String source, String link, Date date){
		super(title, heading, eyelet, summary, text, author, source, link, date);
		this.id = id;
		this.website_id = website_id;
	}
	
	public Article(AggregatedData data){ }

}
