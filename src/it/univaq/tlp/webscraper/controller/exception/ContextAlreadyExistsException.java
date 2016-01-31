package it.univaq.tlp.webscraper.controller.exception;

/**
 * 
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
public class ContextAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -3110879852912449162L;
	
	/**
	 * Default constructor
	 */
	public ContextAlreadyExistsException(){
		
	}
	
	/**
	 * 
	 * @param message
	 */
	public ContextAlreadyExistsException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param throwable
	 */
	public ContextAlreadyExistsException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public ContextAlreadyExistsException(String message, Throwable throwable){
		super(message, throwable);
	}
}
