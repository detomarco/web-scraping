package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.controller.DataAggregator;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.repository.database.MySQLDatabase;

public class UserInterface {
	public static void main(String[] args){
		
		try {
			// Connessione al databse
			Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
			run(storage);
		} catch (StorageException e) {
			// Se la connessione non è andata a buon fine
			System.out.println("Problema di connessione con la repository, si prega di riprovare più tardi");
			System.out.println("Dettagli errore:\n" + e.getMessage());
		}
		
	}
	
	public static void run(Storable storage){
		Scanner in = new Scanner(System.in);
		boolean exit = false;
	
		do{
			
			System.out.println("Cosa vuoi fare?\n"
					+ "1)Recupera qualche articolo in giro per il web\n"
					+ "2)Aggiungi un nuovo sito web\n"
					+ "3)Aggiungi un nuovo template ad un sito web già esistente");
			
			int choice = getInput(1, 4);
			
			switch (choice){		
			
				case 1:
					webScraper(storage);
					break;
					
				case 2:
					websiteManagement(storage);
					break;
				
				case 3:
					templateManagement(storage);
					break;
				
			}
			
			// Controlla se si vuole eseguire un'altra operazione
			System.out.println("Premi invio per uscire dall'applicazione, qualsiasi altro tasto per eseguire un'altra operazione");
			if(in.nextLine().equals("")) exit = true;
			
		}while(!exit);	
	}
	
	
	public static void webScraper(Storable storage){
		String url;
		
		boolean is_list = false, error_url = false;
		Scanner in = new Scanner(System.in);
		
		

		DataAggregator aggregator = new DataAggregator(storage);
		System.out.println("Nello specifico, cosa intendi recuperare?\n"
							+ "1)Una lista di articoli\n"
							+ "2)Un singolo articolo");
		
		int choice = getInput(1, 2);
		
		switch(choice){
		
			// Se si inserisce 1, l'url viene interpretato come lista di articoli, altrimenti come articolo singolo (default) 
			case 1: is_list = true;
			case 2: 
				
				do{
					System.out.print("Inserisci l'url: ");
	
					try {
						url = in.nextLine();
						// 
						aggregator.crawl(url, is_list);
						
					} catch (MalformedURLException e){
						System.out.println("Url non valido");
						error_url = true;
						e.printStackTrace();
					
					} catch (WebsiteNotFoundException e){
						System.out.println("Sito web non trovato");
						error_url = true;
						e.printStackTrace();
						
					} catch (TemplateNotFoundException e){
						System.out.println("Template non trovato");
						error_url = true;
						e.printStackTrace();
						
					} catch (StorageException e){
						System.out.println("C'è stato un errore con il database: " + e.getMessage());
						e.printStackTrace();
					}
					
				}while(error_url);
				break;
		
		}
	
		in.close();
		
		
	}
	
	public static void websiteManagement(Storable storage){
		
	}
	
	public static void templateManagement(Storable storage){
		
	}

	public static int getInput(int min, int max){
		
		boolean flag = true;
		int input;
		Scanner in;
		
		// Controllo dell'input
		do{
			// Se l'input � stato chiesto gi� una volta
			if(!flag){
				// Mostra messaggio di errore
				System.out.println("Input non valido: riprovare!");
			}
			
			try{
				// Reinizializza Scanner
				in = new Scanner(System.in);
				// Richiedi input
				input = in.nextInt();
			
			}catch(InputMismatchException e){
				// Eccezione in caso di un input diverso da un intero
				input = 0;
			}
			
			flag = false;
		
		// Se l'input � minore di 1 o maggiore di 4, mostra messaggio di errore e richiedi input
		}while(input < min || input > max);
		
		return input;
	}
}
