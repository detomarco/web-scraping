package it.univaq.tlp.webscraper.aggregatordata.exception;

public class DataOmittedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3110879852912449162L;

	public DataOmittedException(){
		
	}
	
	public DataOmittedException(String message){
		super(message);
	}
	
	public DataOmittedException(Throwable throwable){
		super(throwable);
	}
	
	public DataOmittedException(String message, Throwable throwable){
		super(message, throwable);
	}
}
