package it.univaq.tlp.webscraper.aggregatordata.model.webdata;

import java.util.Date;

/**
 * Questa classe è un estensione di AggregatedData e si occupa di ......
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
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
