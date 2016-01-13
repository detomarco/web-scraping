package it.univaq.tlp.webscraper.aggregatordata.model.website;

/*
 * L'oggetto template contiene tutte le informazioni riguardo la posizione di tutti gli elementi all'inteno
 * della pagina html.
 * 
 * Gli attributi sono stringhe al cui interno vengono posti i selettori CSS usati per individuare
 * nell'HTML i vari elementi della pagina.
 */
public class Template {
	
	protected String context;
	
	public Template(String context){
		this.context = context;
	}
	
	public String getContext(){
		return this.context;
	}
	
}
