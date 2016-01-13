package it.univaq.tlp.webscraper.aggregatordata.model;

/*
 * L'oggetto template contiene tutte le informazioni riguardo la posizione di tutti gli elementi all'inteno
 * della pagina html.
 * 
 * Gli attributi sono stringhe al cui interno vengono posti i selettori CSS usati per individuare
 * nell'HTML i vari elementi della pagina.
 */
public class Template {
	
	private String title;
	private String text;
	private String date;
	private String author;
	
	public Template(String title, String text, String date, String author){
		this.title = title;
		this.text = text;
		this.date = date;
		this.author = author;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getText(){
		return this.text;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getAuthor(){
		return this.author;
	}
}
