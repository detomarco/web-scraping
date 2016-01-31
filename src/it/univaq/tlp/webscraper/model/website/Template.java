package it.univaq.tlp.webscraper.model.website;

import java.util.Map;

/**
 * This class is the main abstraction of template
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public abstract class Template {
	
	protected String context;
	
	/**
	 * 
	 * @param context
	 */
	public Template (String context){
		this.context = context;
	}
	
	/**
	 * 
	 * @return website context for this template
	 */
	public String getContext(){
		return this.context;
	}
	
	public abstract Map<String, Object> toMap();
}
