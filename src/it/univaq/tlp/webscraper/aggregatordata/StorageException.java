package it.univaq.tlp.webscraper.aggregatordata;

public class StorageException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6391020068970792123L;
	
	public StorageException(){
		
	}
	
	public StorageException(String message){
		super(message);
	}
	
	public StorageException(Throwable throwable){
		super(throwable);
	}
	
	public StorageException(String message, Throwable throwable){
		super(message, throwable);
	}

}
