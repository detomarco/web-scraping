package it.univaq.tlp.webscraper.aggregatordata.model.webdata;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AggregatedData {
	
	private String title;
	private String text;	
	private String author;
	private String source;
	private String link;
	private Date date;
//	private String topic;
	private Map<String, String> metadata;
	
	/*
	 * Default constructor
	 */
	public AggregatedData(){
		this.metadata = new HashMap<String, String>();
	}
	
	public AggregatedData(String title, String text, String author, String source, String link, Date date/*, String topic*/){
		this();
		
		this.title = title;
		this.text = text;
		this.author = author;
		this.source = source;
		this.link = link;
		this.date = date;
//		this.topic = topic;	
	}
	
	/*
	 * METODI PER INSERIMENTO ELEMENTI NELL'OGGETTO
	 */
	public void putTitle(String title){
		this.title = title;
	}
	
	public void putText(String text){
		this.text = text;
	}
	
	public void putAuthor(String author){
		this.author = author;
	}
	
	public void putSource(String source){
		this.source = source;
	}
	
	public void putLink(String link){
		this.link = link;
	}
	
	public void putDate(Date date){
		this.date = date;
	}
	
	public void addMetadata(String key, String value){
		metadata.put(key, value);
	}
	
//	public void putTopic(String topic){
//		this.topic = topic;
//	}
	
	/*
	 * METODI PER RECUPERO ELEMENTI DALL'OGGETTO
	 */
	public String getTitle(){
		return this.title;
	}
	
	public String getText(){
		return this.text;
	}
	
	public String getSource(){
		return this.source;
	}
	
	public String getLink(){
		return this.link;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public Map<String, String> getMetadata(){
		return this.metadata;
	}
	
	public String getMetadata(String key){
		return metadata.get(key);
	}
	
//	public String getTopic(){
//		return this.topic;
//	}
}
