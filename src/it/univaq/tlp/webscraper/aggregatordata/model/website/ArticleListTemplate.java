package it.univaq.tlp.webscraper.aggregatordata.model.website;

public class ArticleListTemplate extends Template{

	private String article_link_selector;
	
	public ArticleListTemplate(String context, String article_link_selector){
		super(context);
		this.article_link_selector = article_link_selector;
	}
	
	public String getLinkSelector(){
		return this.article_link_selector;
	}
	
}
