package it.univaq.tlp.webscraper.controller.exception;

public class ContextAlreadyExistsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3110879852912449162L;

	public ContextAlreadyExistsException(){
		
	}
	
	public ContextAlreadyExistsException(String message){
		super(message);
	}
	
	public ContextAlreadyExistsException(Throwable throwable){
		super(throwable);
	}
	
	public ContextAlreadyExistsException(String message, Throwable throwable){
		super(message, throwable);
	}
}
