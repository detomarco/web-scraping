package it.univaq.tlp.webscraper.aggregatordata.model;

import java.util.Date;

public class AggregatedData {
	
	/*
	 * NOTA: Gli elementi di questa classe devono essere raffinati, la traccia richiede
	 * di creare altre classi che vadano a raffinare i dati in modo da poter contenere più informazioni
	 * 
	 */
	private String title;
	private String text;
	private String source;
	private String link;
	private Date date;
	private String topic;
	
	/*
	 * Default constructor
	 */
	public AggregatedData(){ }
	
	public AggregatedData(String title, String text, String source, String link, Date date, String topic){
		this.title = title;
		this.text = text;
		this.source = source;
		this.link = link;
		this.date = date;
		this.topic = topic;	
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
	
	public void putSource(String source){
		this.source = source;
	}
	
	public void putLink(String link){
		this.link = link;
	}
	
	public void putDate(Date date){
		this.date = date;
	}
	
	public void putTopic(String topic){
		this.topic = topic;
	}
	
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
	
	public String getTopic(){
		return this.topic;
	}
}
