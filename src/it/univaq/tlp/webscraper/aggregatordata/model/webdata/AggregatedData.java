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
	public void putTitle(String title){
		this.title = title;
	}
	
	public void putHeading(String heading){
		this.heading = heading;
	}
	
	public void putEyelet(String eyelet){
		this.eyelet = eyelet;
	}
	
	public void putSummary(String summary){
		this.summary = summary;
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
	
	public void addMetadata(String key, String value){
		metadata.put(key, value);
	}
	
	public void addImgCaption(String value){
		img_caption.add(value);
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
	
	
	
	public String getHeading() {
		return heading;
	}

	public String getEyelet() {
		return eyelet;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public String getText(){
		return this.text;
	}
	
	public String getAuthor(){
		return this.author;
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
	
	@Override
	public String toString(){
		return title+": "+text+"\n"+metadata;
	}

	

}
