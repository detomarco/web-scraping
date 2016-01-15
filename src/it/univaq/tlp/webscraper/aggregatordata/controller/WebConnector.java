package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import jodd.jerry.Jerry;

public class WebConnector {
	
	private UserAgent userAgent;
	
	public WebConnector(){
		this.userAgent = new UserAgent();
		userAgent.settings.autoSaveAsHTML = true;
	}
		
	public AggregatedData collectArticleData(ArticleTemplate template, String url){
		
		// Pezzo comune overload metodo
		AggregatedData data = new AggregatedData();
		
		try{
			userAgent.visit(url);		    
		} catch (ResponseException e){
			e.printStackTrace();
		}
		
		String HTML = "";
		
		try{
			HTML = userAgent.doc.getFirst("<html>").innerHTML();
		} catch (NotFound e){
//			e.printStackTrace();
			
		}
		// ...fine pezzo comune	
		
		Jerry doc = Jerry.jerry(HTML);
		
		// Estrazione informazioni ed inserimento nell'oggetto
		data.putTitle((doc.$(template.getTitleSelector()).html()));
		data.putText((doc.$(template.getTextSelector()).html()));
		data.putAuthor((doc.$(template.getAuthorSelector()).html()));
		data.putDate((doc.$(template.getDateSelector()).html()), template.getDateFormat());
		data.putSource(url);
		
		// Inserimento metadati
		Map<String, String> metadata = new HashMap<String, String>();
		
		try{
		    for(Element node: userAgent.doc.findFirst("<head>").findEvery("<meta>")){
		    	// Se è presente un contenuto (verificare che questo sia il controllo giusto)
		    	if(!node.getAttx("content").equals("")){
		    		data.addMetadata(node.getAttx("name"), node.getAttx("content"));
		    	}
		    }
	    } catch (NotFound e){ }
		
		// Inserimento didascalie delle immagini
		List img_caption = new ArrayList<>();
	    for(Element node: userAgent.doc.findEvery("<img>")){
	    	// Se è presente una didascalia (verificare che questo sia il controllo giusto)
	    	if(!node.getAttx("alt").equals("")){
	    		data.addImgCaption(node.getAttx("alt"));
	    	}
	    	
	    }
	    
	    return data;
	}

}
