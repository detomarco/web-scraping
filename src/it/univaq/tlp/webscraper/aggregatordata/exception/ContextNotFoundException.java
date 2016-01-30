package it.univaq.tlp.webscraper.aggregatordata.exception;

public class ContextNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3110879852912449162L;

	public ContextNotFoundException(){
		
	}
	
	public ContextNotFoundException(String message){
		super(message);
	}
	
	public ContextNotFoundException(Throwable throwable){
		super(throwable);
	}
	
	public ContextNotFoundException(String message, Throwable throwable){
		super(message, throwable);
	}
}
