package it.univaq.tlp.webscraper.aggregatordata.model.website;

public class ArticleTemplate extends Template{
	
	private String title_selector;
	private String text_selector;
	private String date_selector;
	private String author_selector;
	private String date_format;
	
	public ArticleTemplate(){
		super("ciao");
	}
	
	public ArticleTemplate(String context, String title_selector, String text_selector, String author_selector, String date_selector, String date_format){
		super(context);
		this.title_selector = title_selector;
		this.text_selector = text_selector;
		this.author_selector = author_selector;
		this.date_selector = date_selector;
	}
	
	public String getTitleSelector(){
		return this.title_selector;
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
