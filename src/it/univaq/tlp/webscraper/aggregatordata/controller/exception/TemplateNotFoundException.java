package it.univaq.tlp.webscraper.aggregatordata.controller.exception;

public class TemplateNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3110879852912449162L;

	public TemplateNotFoundException(){
		
	}
	
	public TemplateNotFoundException(String message){
		super(message);
	}
	
	public TemplateNotFoundException(Throwable throwable){
		super(throwable);
	}
	
	public TemplateNotFoundException(String message, Throwable throwable){
		super(message, throwable);
	}
	
}
