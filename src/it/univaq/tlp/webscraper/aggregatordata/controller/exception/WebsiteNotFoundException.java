package it.univaq.tlp.webscraper.aggregatordata.controller.exception;

public class WebsiteNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3110879852912449162L;

	public WebsiteNotFoundException(){
		
	}
	
	public WebsiteNotFoundException(String message){
		super(message);
	}
	
	public WebsiteNotFoundException(Throwable throwable){
		super(throwable);
	}
	
	public WebsiteNotFoundException(String message, Throwable throwable){
		super(message, throwable);
	}
	
}
