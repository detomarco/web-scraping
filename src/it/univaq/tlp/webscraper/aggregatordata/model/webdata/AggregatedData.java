package it.univaq.tlp.webscraper.aggregatordata.model.webdata;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Questa classe si occupa di ......
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
	private String link;
	private Date date;
//	private String topic;
	private List<String> img_caption;
	private Map<String, String> metadata;
	
	/*
	 * Default constructor
	 */
	public AggregatedData(){
		this.img_caption = new ArrayList<>();
		this.metadata = new HashMap<String, String>();
	}
	
	public AggregatedData(String title, String heading, String eyelet, String summary,  String text, String author, String source, String link, Date date/*, String topic*/){
		this();
		
		this.title = title;
		this.heading = heading;
		this.eyelet = eyelet;
		this.summary = summary;
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
	
	/**
	 * Metodo che inserisce il titolo nell'articolo
	 * @param title
	 */
	public void putTitle(String title){
		this.title = title;
	}
	
	/**
	 * Metodo che inserisce nell'articolo
	 * @param heading
	 */
	public void putHeading(String heading){
		this.heading = heading;
	}
	
	/**
	 * Metodo che inserisce l'occhiello nell'articolo
	 * @param eyelet
	 */
	public void putEyelet(String eyelet){
		this.eyelet = eyelet;
	}
	
	/**
	 * Metodo che inserisce il sommario nell'articolo
	 * @param summary
	 */
	public void putSummary(String summary){
		this.summary = summary;
	}
	
	/**
	 * Metodo che inserisce il testo nell'articolo
	 * @param text
	 */
	public void putText(String text){
		this.text = text;
	}
	
	/**
	 * Metodo che inserisce l'autore nell'articolo
	 * @param author
	 */
	public void putAuthor(String author){
		this.author = author;
	}
	
	/**
	 * Metodo che inserisce il sorgente nell'articolo
	 * @param source
	 */
	public void putSource(String source){
		this.source = source;
	}
	
	/**
	 * Metodo che inserisce il riferimento nell'articolo
	 * @param link
	 */
	public void putLink(String link){
		this.link = link;
	}
	
	/**
	 * Metodo che inserisce la data nell'articolo
	 * @param date
	 */
	public void putDate(Date date){
		this.date = date;
	}
	
	/**
	 * Metodo che inserisce la data nell'articolo
	 * @param date
	 * @param format
	 */
	public void putDate(String date, String format){
		DateFormat df = new SimpleDateFormat(format);
		Date parsedDate;
		
		try{
			parsedDate = df.parse(date);
		} catch (ParseException e){
			parsedDate = null;
		}
		
		this.date = parsedDate;
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
	
//	public void putTopic(String topic){
//		this.topic = topic;
//	}
	
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
	 * Metodo che recupera il riferimento dall'oggetto
	 * @return String
	 */
	public String getLink(){
		return this.link;
	}
	
	/**
	 * Metodo che recupera la data dall'oggetto
	 * @return Date
	 */
	public Date getDate(){
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
	
//	public String getTopic(){
//		return this.topic;
//	}
	
	@Override
	public String toString(){
		return title+": "+text+"\n"+metadata;
	}

	

}
