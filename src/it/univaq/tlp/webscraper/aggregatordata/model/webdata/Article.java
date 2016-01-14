package it.univaq.tlp.webscraper.aggregatordata.model.webdata;

import java.util.Date;

public class Article extends AggregatedData {

	public Article(){
		
	}
	
	public Article(String title, String text, String author, String source, String link, Date date){
		super(title, text, author, source, link, date);
	}
	
	public Article(AggregatedData data){
	}
}
