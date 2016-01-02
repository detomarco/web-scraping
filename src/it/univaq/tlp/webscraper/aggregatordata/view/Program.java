package it.univaq.tlp.webscraper.aggregatordata.view;

import it.univaq.tlp.webscraper.aggregatordata.controller.WebConnector;
import it.univaq.tlp.webscraper.aggregatordata.model.AggregatedData;

public class Program {

	public static void main(String[]args){
		
		WebConnector connector = new WebConnector();
		AggregatedData data = connector.collectData("http://www.marcodetoma.altervista.org");
		
		System.out.println(data.getTitle());
		
	}
	
	
}
