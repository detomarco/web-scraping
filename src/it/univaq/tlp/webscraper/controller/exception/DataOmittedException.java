package it.univaq.tlp.webscraper.controller.exception;

/**
 * 
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
public class DataOmittedException extends Exception{

	private static final long serialVersionUID = -3110879852912449162L;

	/**
	 * Default constructor
	 */
	public DataOmittedException(){
		
	}
	
	/**
	 * 
	 * @param message
	 */
	public DataOmittedException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param throwable
	 */
	public DataOmittedException(Throwable throwable){
		super(throwable);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public DataOmittedException(String message, Throwable throwable){
		super(message, throwable);
	}
}
