package it.univaq.tlp.webscraper.aggregatordata.model.website;

import java.util.HashSet;
import java.util.Set;

public class Website {

	private String name;
	private String address;
	
	private String description;
	
//	private Set<String> topics;
	
	private Set<Template> templates;
	
	public Website(String name, String address){
		this.name = name;
		this.address = address;
		this.description = "";
//		this.topics = new HashSet<String>();
		this.templates = new HashSet<Template>();
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
	
//	public Set<String> getTopics(){
//		return this.topics;
//	}
	
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
	
	public Template getTemplateByContext(String context){
		for(Template current: templates){
			if(current.getContext().equals(context)) return current;
		}
		return null;
	}
	
//	public void addTopic(String topic){
//		topics.add(topic);
//	}
//	
//	public void removeTopic(String topic){
//		topics.remove(topic);
//	}
//	
//	public boolean hasTopic(String topic){
//		return topics.contains(topic);
//	}
	
	public String toString(){
		return name+" ("+address+"): "+description;
	}
}
