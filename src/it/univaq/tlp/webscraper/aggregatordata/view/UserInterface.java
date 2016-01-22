package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.Scanner;

import it.univaq.tlp.webscraper.aggregatordata.Storable;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.database.MySQLDatabase;

public class UserInterface {

	public static void main(String[] args){
		
		run();
		
	}
	
	public static void run(){
		Scanner in = new Scanner(System.in);
		boolean running = true;
		while(running){
			
			System.out.println("Ciao :)\nCosa vuoi fare?\n"
					+ "1)Recupera qualche articolo in giro per il web\n"
					+ "2)Aggiungi un nuovo sito web\n"
					+ "3)Aggiungi un nuovo template ad un sito web già esistente");
			
			int choice = in.nextInt();
			
			switch (choice){		
			case 1:
				webScraper();
				break;
				
			case 2:
				websiteMnmgmt();
				break;
			
			case 3:
				templateMnmgmt();
				break;
			}
			
			System.out.println("Digita \"exit\" per uscire, qualsiasi altra cosa per continuare");
			
			String response = in.nextLine();
			if(!response.equals("exit")){
				running = false;
			}
		}
	}
	
	public static void webScraper(){
		Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
		
		DataAggregator aggregator = new DataAggregator(storage);
		
		try {
			aggregator.crawl("http://www.repubblica.it/politica", true);
		} catch (MalformedURLException e){
			e.printStackTrace();
		}
	}
	
	public static void websiteMnmgmt(){
		
	}
	
	public static void templateMnmgmt(){
		
	}
}
