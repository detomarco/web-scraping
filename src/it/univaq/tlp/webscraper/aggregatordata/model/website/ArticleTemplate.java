package it.univaq.tlp.webscraper.aggregatordata.model.website;

public class ArticleTemplate extends Template{
	
	private String title;
	private String text;
	private String date;
	private String author;
	
	public ArticleTemplate(String context, String title, String text, String date, String author){
		super(context);
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
