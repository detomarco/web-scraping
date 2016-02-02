package it.univaq.tlp.webscraper.utility;

import java.net.MalformedURLException;

import org.apache.commons.validator.routines.UrlValidator;

public class URL {
	
	/**
	* This class provides methods to handle a URL
	* @author Marco De Toma
	* @author Alessandro D'Errico
	* @author Gianluca Filippone
	*/	

	private java.net.URL url;
	private String source;
	private String host;
	private String context;
	private String path;
	
	/**
	* @param source, url to handle
	*/
	public URL(String source) throws MalformedURLException{
		this.source = source;
		this.setSource();
		this.url = new java.net.URL(this.source);
		this.validate();
		this.setHost();
		this.setContext();
		this.path = this.url.getPath();
	}
	
	/** 
	* @return url host
	* 
	*/
	public String getHost(){
		return this.host;
	}
	
	/**
	* @return url context
	* 
	*/
	public String getContext(){
		return this.context;
	}
	
	/**
	* @return url parameters
	* 
	*/
	public String getPath(){
		return this.path;
	}
	
	/**
	* @return url source
	* 
	*/
	public String getSource(){
		return this.source;
	}
	
	/**
	 * Check if url source represents a home page
	* @return true if it is represents a home page, false otherwise
	* 
	*/
	public boolean isList(){

		if(this.context.equals("")) return true;
		
		if(url.toString().equals("http://www." + this.host + "/" + this.context)) return true;
		
		return false;
		
	}
	
	private void setHost(){
		
		this.host = url.getHost();
		
		if(this.host.startsWith("www.")){
			this.host = this.host.substring(4);
		}
		
	}
	
	private void setContext(){
		
		String path = url.getPath();
		if(path.length()>0){
			this.context = path.substring(1)+"/";
			this.context = this.context.substring(0, this.context.indexOf("/"));
			try{
				Integer.parseInt(this.context);
				this.context = "";
			}catch(NumberFormatException e){  }
		} else {
			this.context = "";
		}
			
	}
	
	private String setSource(){
		this.source = source.trim();
		
		
		// L'url deve iniziare con 'http://www.'
		if(!(this.source.startsWith("http")) && !(this.source.startsWith("https"))) {
			if(!(this.source.startsWith("www."))){
				this.source = "www." + this.source;
			}
			this.source = "http://" + this.source;
		}
		
		
		// Elimina tutto ciò che c'è dopo '?'
		if(this.source.contains("?")){
			this.source = this.source.substring(0, this.source.indexOf("?"));
		}
		
		// Elimina tutto ciò che c'è dopo '#'
		if(this.source.contains("#")){
			this.source = this.source.substring(0, this.source.indexOf("#"));
		}
		
		// Nel caso l'url finisce per '/', eliminalo
		if(this.source.charAt(this.source.length()-1) == '/'){
			this.source = this.source.substring(0, this.source.length()-1);
		}
		
		return this.source;
		
	}
	
	/**
	 * Check if url source represents a valid url
	* @return true if it is a valid url, false otherwise
	* 
	*/
	private void validate() throws MalformedURLException{
		URL.validate(this.source);
	}
	
	/**
	 * Check if a url represents a valid url
	* @return true if it is a valid url, false otherwise
	* 
	*/
	public static void validate(String url) throws MalformedURLException{
		String[] schemes = {"http","https"};
		UrlValidator validator = new UrlValidator(schemes);
		if(!validator.isValid(url)) throw new MalformedURLException();
	}
}
