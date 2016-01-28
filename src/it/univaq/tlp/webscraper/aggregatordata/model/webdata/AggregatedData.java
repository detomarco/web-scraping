package it.univaq.tlp.webscraper.aggregatordata.model.webdata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Questa classe si occupa di recuperare gli elementi dall'articolo e inserirli nell'oggetto
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class AggregatedData {
	
	private String title;
	
	private String heading;
	private String summary;
	private String eyelet;
	
	private String text;	
	private String author;
	
	private String source;
	private String date;
//	private String topic;
	private List<String> img_caption;
	private Map<String, String> metadata;
	
	/*
	 * Default constructor
	 */
	public AggregatedData(){
		this.img_caption = new ArrayList<>();
		this.metadata = new HashMap<String, String>();
		
		this.title = "";
		this.heading = "";
		this.summary = "";
		this.eyelet = "";
		this.text = "";
		this.author = "";
		this.source = "";
		this.date = "";
		
	}
	
	public AggregatedData(String title, String heading, String eyelet, String summary, String text, String author, String source, String date){
		this();
		
		putTitle(title);
		putHeading(heading);
		putEyelet(eyelet);
		putSummary(summary);
		putText(text);
		putAuthor(author);
		putSource(source);
		putDate(date);
	}
	
	/*
	 * METODI PER INSERIMENTO ELEMENTI NELL'OGGETTO
	 */
	
	/**
	 * Metodo che inserisce il titolo nell'articolo
	 * @param title
	 */
	public void putTitle(String title){
		if(title!=null){
			this.title = title;
		}
	}
	
	/**
	 * Metodo che inserisce nell'articolo
	 * @param heading
	 */
	public void putHeading(String heading){
		if(heading!=null){
			this.heading = heading;
		}
	}
	
	/**
	 * Metodo che inserisce l'occhiello nell'articolo
	 * @param eyelet
	 */
	public void putEyelet(String eyelet){
		if(eyelet!=null){
			this.eyelet = eyelet;
		}
	}
	
	/**
	 * Metodo che inserisce il sommario nell'articolo
	 * @param summary
	 */
	public void putSummary(String summary){
		if(summary!=null){
			this.summary = summary;
		}	
	}
	
	/**
	 * Metodo che inserisce il testo nell'articolo
	 * @param text
	 */
	public void putText(String text){
		if(text!=null){
			this.text = text;
		}
	}
	
	/**
	 * Metodo che inserisce l'autore nell'articolo
	 * @param author
	 */
	public void putAuthor(String author){
		if(author!=null){
			this.author = author;
		}
	}
	
	/**
	 * Metodo che inserisce la fonte (l'indirizzo) nell'articolo
	 * @param source
	 */
	public void putSource(String source){
		if(source!=null){
			this.source = source;
		}
	}
	
	/**
	 * Metodo che inserisce la data nell'articolo
	 * @param date
	 */
	public void putDate(String date){
		if(date!=null){
			this.date = date;
		}
	}

	/**
	 * Metodo che inserisce i Metadata nell'articolo
	 * @param key
	 * @param value
	 */
	public void addMetadata(String key, String value){
		metadata.put(key, value);
	}
	
	/**
	 * Metodo che inserisce l'immagine nell'articolo
	 * @param value
	 */
	public void addImgCaption(String value){
		img_caption.add(value);
	}
	
	/*
	 * METODI PER RECUPERO ELEMENTI DALL'OGGETTO
	 */
	
	/**
	 * Metodo che recupera il titolo dall'oggetto
	 * @return String
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Metodo che recupera il sottotitolo dall'oggetto
	 * @return String
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * Metodo che recupera l'occhiello dall'oggetto
	 * @return String
	 */
	public String getEyelet() {
		return eyelet;
	}
	
	/**
	 * Metodo che recupera il sommario dall'oggetto
	 * @return String
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Metodo che recupera il testo dall'oggetto
	 * @return String
	 */
	public String getText(){
		return this.text;
	}
	
	/**
	 * Metodo che recupera l'autore dall'oggetto
	 * @return String
	 */
	public String getAuthor(){
		return this.author;
	}
	
	/**
	 * Metodo che recupera il sorgente dall'oggetto
	 * @return String
	 */
	public String getSource(){
		return this.source;
	}
	
	/**
	 * Metodo che recupera la data dall'oggetto
	 * @return Date
	 */
	public String getDate(){
		return this.date;
	}
	
	/**
	 * Metodo che recupera i Metadata dall'oggetto
	 * @return Map<String, String>
	 */
	public Map<String, String> getMetadata(){
		return this.metadata;
	}
	
	/**
	 * Metodo che recupera i Metadata dall'oggetto
	 * @return String
	 */
	public String getMetadata(String key){
		return metadata.get(key);
	}
	
	@Override
	public String toString(){
		return title+": "+text+"\n"+metadata;
	}

	@Override
	public boolean equals(Object obj){
		if ((obj instanceof AggregatedData)) {
			
			AggregatedData data = (AggregatedData) obj;
			
			if (this.source.equals(data.getSource())) return true;
		}
		return false;
	}
	

}
