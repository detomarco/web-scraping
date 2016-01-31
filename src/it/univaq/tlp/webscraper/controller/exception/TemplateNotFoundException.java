package it.univaq.tlp.webscraper.controller.exception;

/**
 * 
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
public class TemplateNotFoundException extends Exception{

	private static final long serialVersionUID = -3110879852912449162L;

	/**
	 * Default constructor
	 */
	public TemplateNotFoundException(){
		
	}
	
	/**
	 * 
	 * @param message
	 */
	public TemplateNotFoundException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param throwable
	 */
	public TemplateNotFoundException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public TemplateNotFoundException(String message, Throwable throwable){
		super(message, throwable);
	}
	
}
