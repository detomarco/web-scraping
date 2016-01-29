package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.univaq.tlp.webscraper.aggregatordata.URL;
import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

public class TUI extends UserInterface{
			

	public TUI(Storable storage) {
		super(storage);
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		Scanner in = new Scanner(System.in);
			
		do{
			
			System.out.println("Cosa vuoi fare?\n"
					+ "1)Recupera qualche articolo in giro per il web\n"
					+ "2)Aggiungi un nuovo sito web\n"
					+ "3)Aggiungi un nuovo template ad un sito web già esistente");
			
			int choice = getInput(1, 3);
			
			switch (choice){		
			
				case 1:
					this.webScraper(storage);
					break;
					
				case 2:
					this.websiteManagement(storage);
					break;
				
				case 3:
					this.templateManagement(storage);
					break;
				
			}
			
			// Controlla se si vuole eseguire un'altra operazione
			System.out.println("Premi invio per uscire dall'applicazione, qualsiasi altro tasto per eseguire un'altra operazione");
	
		}while(!in.nextLine().equals(""));	
		
		// Controlla se si vuole eseguire un'altra operazione
		System.out.println("Fine programma");
			
	}

	@SuppressWarnings("resource")
	public void webScraper(Storable storage){
		
		boolean error_url;
		String url;
		Scanner in;
		
		do{
			in = new Scanner(System.in);
			System.out.print("Inserisci l'url: ");
			url = in.nextLine();
			
			try {						
				// Scraping dell'URL
				this.scrap(url);
				System.out.println("Sono stati aggiunti " + this.last_insert + " articoli");
				error_url = false;
				
			// URL non valido
			} catch (MalformedURLException e){
				System.out.println("Url non valido");
				error_url = true;
			
			// Sito web non trovato
			} catch (WebsiteNotFoundException e){
				System.out.println("Sito web non trovato");
				error_url = true;
				
			// Template non trovato
			} catch (TemplateNotFoundException e){
				System.out.println("Template non trovato");
				error_url = true;
			
			// Errore con il database
			} catch (StorageException e){
				System.out.println("Si è verificato un errore con la repository: " + e.getMessage());
				error_url = false;
			}
			
		}while(error_url);
		

	}
	
	public void websiteManagement(Storable storage){
		Scanner in = new Scanner(System.in);
		String url, name, description;
		Website website;
		boolean error_url;
		
		do{
			
			System.out.print("Inserisci url: ");
			url = in.nextLine();
			System.out.print("Inserisci nome: ");
			name = in.nextLine();
			System.out.print("Inserisci descrizione: ");
			description = in.nextLine();
			
			website = new Website(name, url, description);
			
			try {
				this.insertWebsite(website);
				System.out.println("Nuovo sito web inserito");
				error_url = false;
			} catch (MalformedURLException e) {
				System.out.println("Sito web non valido");
				error_url = true;
			
			} catch (WebsiteAlreadyExistsException e) {
				System.out.println("Il sito web è già stato inserito");
				error_url = true;
			
			} catch (StorageException e) {
				System.out.println("Si è verificato un errore con la repository: " + e.getMessage());
				error_url = false;
			}
			
		}while(error_url);
		
		
	}
	
	public void templateManagement(Storable storage){
		
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
