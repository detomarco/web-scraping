package it.univaq.tlp.webscraper.aggregatordata.model.website;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Website {
	
	private Integer id;
	private String name;
	private String address;
	
	private String description;
		
	private Set<Template> templates;
	
	public Website(String name, String address){
		this.name = name;
		this.address = address;
		this.description = "";
		this.templates = new HashSet<Template>();
	}
	
	public Website(Map<String, String> from_storage){
		this.id = Integer.parseInt(from_storage.get("id"));
		this.name = from_storage.get("mame");
		this.address = from_storage.get("address");
		this.description = from_storage.get("description");
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.description;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getDescription(){
		return this.description;
	}
		
	public Set<Template> getTemplates(){
		return this.templates;
	}
	
	public void updateAddress(String address){
		this.address = address;
	}
	
	public void updateDescription(String description){
		this.description = description;
	}
	
	public void addTemplate(Template template){
		templates.add(template);
	}
	
//	public Template getTemplateByContext(String context){
//		for(Template current: templates){
//			if(current.getContext().equals(context)) return current;
//		}
//		return null;
//	}
	
	public String toString(){
		return name+" ("+address+"): "+description;
	}
}
