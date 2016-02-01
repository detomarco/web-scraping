package it.univaq.tlp.webscraper.model.webdata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe per la gestione di un nuovo articoli recuperato dal web
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
	private String context;
	
	private Map<String, Object> metadata;
	
	/**
	 *  Default constructor
	 */
	public AggregatedData(){
		this.metadata = new HashMap<String, Object>();
		this.context = "";
		this.title = "";
		this.heading = "";
		this.summary = "";
		this.eyelet = "";
		this.text = "";
		this.author = "";
		this.source = "";
		this.date = "";
		
	}
	
	/**
	 * 
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
	public AggregatedData(String context, String title, String heading, String eyelet, String summary, String text, String author, String source, String date){
		this();
		putContext(context);
		putTitle(title);
		putHeading(heading);
		putEyelet(eyelet);
		putSummary(summary);
		putText(text);
		putAuthor(author);
		putSource(source);
		putDate(date);
	}
	
	/**
	 * This method puts article context
	 * @param context
	 */
	public void putContext(String context){
		if(context!=null){
			this.context = context.replaceAll("\\s+", " ").trim();
		}
	}
	
	/**
	 * This method puts article page title
	 * @param title
	 */
	public void putTitle(String title){
		if(title!=null){
			this.title = title.replaceAll("\\s+", " ").trim();
		}
	}
	
	/**
	 * This method puts article heading
	 * @param heading
	 */
	public void putHeading(String heading){
		if(heading!=null){
			this.heading = heading.replaceAll("\\s+", " ").trim();
		}
	}
	
	/**
	 * This method puts article eyelet
	 * @param eyelet
	 */
	public void putEyelet(String eyelet){
		if(eyelet!=null){
			this.eyelet = eyelet.replaceAll("\\s+", " ").trim();
		}
	}
	
	/**
	 * This method puts article summary
	 * @param summary
	 */
	public void putSummary(String summary){
		if(summary!=null){
			this.summary = summary.replaceAll("\\s+", " ").trim();
		}	
	}
	
	/**
	 * This method puts article text
	 * @param text
	 */
	public void putText(String text){
		if(text!=null){
			this.text = text.replaceAll("\\s+", " ").trim();
		}
	}
	
	/**
	 * This method puts article author
	 * @param author
	 */
	public void putAuthor(String author){
		if(author!=null){
			this.author = author.replaceAll("\\s+", " ").trim();
		}
	}
	
	/**
	 * This method puts article source (web address)
	 * @param source
	 */
	public void putSource(String source){
		if(source!=null){
			this.source = source.replaceAll("\\s+", " ").trim();
		}
	}
	
	/**
	 * This method puts article date
	 * @param date
	 */
	public void putDate(String date){
		if(date!=null){
			this.date = date.replaceAll("\\s+", " ").trim();
		}
	}

	/**
	 * This method puts article metadatas
	 * @param key
	 * @param value
	 */
	public void addMetadata(String key, String value){
		metadata.put(key.replaceAll("\\s+", " ").trim(), value.replaceAll("\\s+", " ").trim());
	}

	/**
	 * 
	 * @return article context
	 */
	public String getContext(){
		return this.context;
	}
	
	/**
	 *
	 * @return article page title
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * 
	 * @return article heading
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * 
	 * @return article eyelet
	 */
	public String getEyelet() {
		return eyelet;
	}
	
	/**
	 * 
	 * @return article summary
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * 
	 * @return article text
	 */
	public String getText(){
		return this.text;
	}
	
	/**
	 * 
	 * @return article author
	 */
	public String getAuthor(){
		return this.author;
	}
	
	/**
	 * 
	 * @return article web address
	 */
	public String getSource(){
		return this.source;
	}
	
	/**
	 * 
	 * @return article date
	 */
	public String getDate(){
		return this.date;
	}
	
	/**
	 * 
	 * @return map containing article page metadatas
	 */
	public Map<String, Object> getMetadata(){
		return this.metadata;
	}
	
	/**
	 * 
	 * @param key
	 * @return article metadata having given key
	 */
	public Object getMetadata(String key){
		return metadata.get(key);
	}
	
	@Override
	public String toString(){
		return title+": "+text+"\n"+metadata;
	}

	@Override
	/**
	 * @return true if article source are equal
	 */
	public boolean equals(Object obj){
		if ((obj instanceof AggregatedData)) {
			
			AggregatedData data = (AggregatedData) obj;
			
			if (this.source.equals(data.getSource())) return true;
		}
		return false;
	}
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.source);
        return hash;
    }
   

}
