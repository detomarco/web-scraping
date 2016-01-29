package it.univaq.tlp.webscraper.aggregatordata.model.website;

import java.util.Map;

public abstract class Template {
	
	protected String context;
	
	public Template (String context){
		this.context = context;
	}
	
	public String getContext(){
		return this.context;
	}
	
	public abstract Map<String, Object> toMap();
}
