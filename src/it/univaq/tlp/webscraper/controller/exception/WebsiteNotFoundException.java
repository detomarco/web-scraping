package it.univaq.tlp.webscraper.controller.exception;

/**
 * 
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
public class WebsiteNotFoundException extends Exception{

	private static final long serialVersionUID = -3110879852912449162L;

	/**
	 * Default constructor
	 */
	public WebsiteNotFoundException(){
		
	}
	
	/**
	 * 
	 * @param message
	 */
	public WebsiteNotFoundException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param throwable
	 */
	public WebsiteNotFoundException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public WebsiteNotFoundException(String message, Throwable throwable){
		super(message, throwable);
	}
	
}
