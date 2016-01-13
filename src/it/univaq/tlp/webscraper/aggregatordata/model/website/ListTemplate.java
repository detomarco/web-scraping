package it.univaq.tlp.webscraper.aggregatordata.model.website;

public class ListTemplate extends Template{

	private String article_link;
	
	public ListTemplate(String context, String article_link){
		super(context);
		this.article_link = article_link;
	}
	
	public String getLink(){
		return this.article_link;
	}
	
}
