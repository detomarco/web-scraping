package it.univaq.tlp.webscraper.controller.exception;

public class WebsiteAlreadyExistsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3110879852912449162L;

	public WebsiteAlreadyExistsException(){
		
	}
	
	public WebsiteAlreadyExistsException(String message){
		super(message);
	}
	
	public WebsiteAlreadyExistsException(Throwable throwable){
		super(throwable);
	}
	
	public WebsiteAlreadyExistsException(String message, Throwable throwable){
		super(message, throwable);
	}

}
