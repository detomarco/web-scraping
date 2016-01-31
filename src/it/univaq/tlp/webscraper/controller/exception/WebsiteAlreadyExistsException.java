package it.univaq.tlp.webscraper.controller.exception;

/**
 * 
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
public class WebsiteAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -3110879852912449162L;

	/**
	 * Default constructor
	 */
	public WebsiteAlreadyExistsException(){
		
	}
	
	/**
	 * 
	 * @param message
	 */
	public WebsiteAlreadyExistsException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param throwable
	 */
	public WebsiteAlreadyExistsException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public WebsiteAlreadyExistsException(String message, Throwable throwable){
		super(message, throwable);
	}

}
