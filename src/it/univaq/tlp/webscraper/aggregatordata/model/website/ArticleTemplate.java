package it.univaq.tlp.webscraper.aggregatordata.model.website;

public class ArticleTemplate extends Template{
	
	private String heading_selector;
	private String eyelet_selector;
	private String summary_selector;
	private String text_selector;
	private String date_selector;
	private String author_selector;
	private String date_format;
	
	public ArticleTemplate(){
		super("ciao");
	}
	
	public ArticleTemplate(String context, String heading_selector, String eyelet_selector, String summary_selector, String text_selector, String author_selector, String date_selector, String date_format){
		super(context);
		this.heading_selector = heading_selector;
		this.eyelet_selector = eyelet_selector;
		this.summary_selector = summary_selector;
		this.text_selector = text_selector;
		this.author_selector = author_selector;
		this.date_selector = date_selector;
		this.date_format = date_format;
	}
	
	public String getHeadingSelector(){
		return this.heading_selector;
	}
	
	public String getEyeletSelector(){
		return this.eyelet_selector;
	}
	
	public String getSummarySelector(){
		return this.summary_selector;
	}
	
	public String getTextSelector(){
		return this.text_selector;
	}
	
	public String getAuthorSelector(){
		return this.author_selector;
	}
	
	public String getDateSelector(){
		return this.date_selector;
	}
	
	public String getDateFormat(){
		return this.date_format;
	}

}
