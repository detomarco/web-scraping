package it.univaq.tlp.webscraper.aggregatordata.controller;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import it.univaq.tlp.webscraper.aggregatordata.model.AggregatedData;

public class WebConnector {

	public AggregatedData collectData(String source){
		
		AggregatedData data = new AggregatedData();
		
		UserAgent userAgent = new UserAgent();      								
	    userAgent.settings.autoSaveAsHTML = true;
	    
	    try {
			userAgent.visit(source);
		    
		    Element head = userAgent.doc.findFirst("<head>");
//		    Element body = userAgent.doc.findFirst("<body>");
		    
		    data.putTitle(head.findFirst("<title>").innerHTML());
		    
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (NotFound e){
			e.printStackTrace();
		}
	    
	    return data;
	}
	
	
}
