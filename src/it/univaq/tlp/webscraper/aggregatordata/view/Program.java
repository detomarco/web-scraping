package it.univaq.tlp.webscraper.aggregatordata.view;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.aggregatordata.controller.WebConnector;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.database.MySQLDatabase;

public class Program {

	public static void main(String[]args){
		
		//Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraping");
		
		DataAggregator aggregator = new DataAggregator();
		
		aggregator.aggregate("http://www.wired.it/attualita/ambiente/2016/01/18/pontevedra-smog-linquinamento-banditi-15-anni-rischio-misure-immediate/?utm_source=facebook.com&utm_medium=marketing&utm_campaign=wired");
		
//		AggregatedData data = connector.collectData("http://www.marcodetoma.altervista.org");
		
//		aggregator.aggregate(data);
		
//		System.out.println(data.getTitle());
		
	}
	
	
}
