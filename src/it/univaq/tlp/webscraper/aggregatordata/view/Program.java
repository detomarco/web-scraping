package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.aggregatordata.controller.WebConnector;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.database.MySQLDatabase;

public class Program {

	public static void main(String[]args){
		
		Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
		
		DataAggregator aggregator = new DataAggregator(storage);
		
		try {
			aggregator.crawl("http://www.repubblica.it", true);
		} catch (MalformedURLException e){
			e.printStackTrace();
		}
		
		
	}
	
	
}
