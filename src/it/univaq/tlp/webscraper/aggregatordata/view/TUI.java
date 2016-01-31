package it.univaq.tlp.webscraper.aggregatordata.view;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.aggregatordata.controller.UserInterface;
import it.univaq.tlp.webscraper.aggregatordata.exception.ContextAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.DataOmittedException;
import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

@SuppressWarnings("resource")
public class TUI extends UserInterface{
	/**
	* Classe per la gestione della Text-based Graphic User dell'applicazione
	* @author Marco De Toma
	* @author Alessandro D'Errico
	* @author Gianluca Filippone
	*/	

	public TUI(Storable storage) {
		super(storage);
	}

	@Override
	public void run() {
			try {
				for(String asd : this.getWebsiteContexts("repubblica.it")){
					System.out.println(asd);
				}
			} catch (StorageException | WebsiteNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		Scanner in = new Scanner(System.in);
			
		do{
			
			System.out.println("Cosa vuoi fare?\n"
						+ "1. Recupera qualche articolo in giro per il web\n"
						+ "2. Aggiungi un nuovo sito web\n"
						+ "3. Aggiungi un nuovo template ad un sito web già esistente\n"
						+ "4. Mostra articoli");
			
			int choice = getInput(1, 4);
			
			switch (choice){		
			
				case 1:
					this.webScraper();
					break;
					
				case 2:
					this.websiteManagement();
					break;
				
				case 3:
					this.templateManagement();
					break;
				
				case 4:
					this.showArticles();
					break;
				
			}
			
			// Controlla se si vuole eseguire un'altra operazione
			System.out.println("Premi invio per uscire dall'applicazione, qualsiasi altro tasto per eseguire un'altra operazione");
	
		}while(!in.nextLine().equals(""));	
		
		// Controlla se si vuole eseguire un'altra operazione
		System.out.println("Fine programma");
			
	}

	/**
	* Modulo per recuperare nuovi articoli dal web
	* 
	*/
	public void webScraper(){
		
		boolean error_url;
		String url;
		Scanner in;
		
		do{
			in = new Scanner(System.in);
			System.out.print("Inserisci l'url: ");
			url = in.nextLine();
			
			try {						
				System.out.println("Attendere...");
				// Scraping dell'URL
				this.scrap(url);
				System.out.println("Sono stati aggiunti " + this.last_insert + " articoli");
				error_url = false;
				
			// URL non valido
			} catch (MalformedURLException e){
				System.out.println("Url non valido");
				error_url = true;
			}catch (ResponseException e) {
				System.out.println("Errore di connessione");
				error_url = false;
				
			// Sito web non trovato
			} catch (WebsiteNotFoundException e){
				System.out.println("Sito web non trovato");
				error_url = true;
				e.printStackTrace();
				
			// Template non trovato
			} catch (TemplateNotFoundException e){
				System.out.println("Template non trovato");
				error_url = true;
			
			// Errore con il database
			} catch (StorageException e){
				System.out.println("Si è verificato un errore con la repository");
				e.printStackTrace();
				error_url = false;
			} 
			
		}while(error_url);
		

	}
	
	/**
	* Modulo per inserire un nuovo sito web
	* 
	*/
	public void websiteManagement(){
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
	
	/**
	* Modulo per inserire un nuovo template
	* 
	*/
	
	public void templateManagement(){
		Scanner in = new Scanner(System.in);
		boolean error;
		String list, url, context, heading, summary, eyelet, author, date, text;
		ArticleListTemplate article_list; ArticleTemplate article;
		do{
			
			System.out.print("Inserisci il sito web: ");
			url = in.nextLine();
			System.out.print("Inserisci il contesto: ");
			context = in.nextLine();
			
			System.out.println("Inserisci i seguenti selettori:");
			System.out.print("Articoli della home: ");
			list = in.nextLine();
			System.out.print("Intestazione: ");
			heading = in.nextLine();
			System.out.print("Occhiello: ");
			eyelet = in.nextLine();
			System.out.print("Sommario: ");
			summary = in.nextLine();
			System.out.print("Testo: ");
			text = in.nextLine();
			System.out.print("Autore: ");
			author = in.nextLine();
			System.out.print("Data: ");
			date = in.nextLine();
			
			article_list = new ArticleListTemplate(context, list);
			article = new ArticleTemplate(context, heading, eyelet, summary, text, author, date);
			
			try {
				this.insertTemplate(article, article_list, url);
				System.out.println("Template inserito correttamente");
				error = false;
			} catch (MalformedURLException e) {
				System.out.println("Url non valido");
				error = true;
			} catch (WebsiteNotFoundException e) {
				System.out.println("Sito web non trovato");
				error = true;
			} catch (ContextAlreadyExistsException e) {
				System.out.println("Il contesto è già stato inserito");
				error = true;
			}catch (DataOmittedException e) {
				System.out.println("Il selettore dell'intestazione e del testo sono obbligatori");
				error = true;
			} catch (StorageException e) {
				System.out.println("Si è verificato un errore con la repository");
				e.printStackTrace();
				error = false;
			
			} 
			
			
		}while(error);
		
}
	
	/**
	* Modulo per mostrare tutti i gli articoligià inseriti di un determinato sito web
	* 
	*/
	public void showArticles(){
		boolean error;
		Scanner in = new Scanner(System.in);
		String host, context;
		Set<Article> articles;
		
		do{
			System.out.print("Inserisci il sito web: ");
			host = in.nextLine();
			System.out.print("Inserisci il contesto (lasciare vuoto per mostrare tutti gli articoli del sito): ");
			context = in.nextLine();
			try {
				articles = this.viewWebsiteArticles(host, context);
				
				if(articles.isEmpty()){
					System.out.println("Nessun articolo trovato");
				}else{
					Map<Integer, Article> map_articles = new HashMap<>();
					int cont = 0;
					System.out.println("");
					for(Article article: articles){
						map_articles.put(++cont, article);
						System.out.println(cont + ". " + article.getHeading());
					}
					System.out.print("\nSeleziona un articolo per visualizzare le relativa informazioni: ");
					
					int choice = getInput(1, cont);
					
					System.out.println("Fonte: " + map_articles.get(choice).getSource());
					System.out.println("Context: " + map_articles.get(choice).getContext());
					System.out.println("Intestazione: " + map_articles.get(choice).getHeading());
					System.out.println("Sommario: " + map_articles.get(choice).getSummary());
					System.out.println("Occhiello: " + map_articles.get(choice).getEyelet());
					System.out.println("Author: " + map_articles.get(choice).getAuthor());
					System.out.println("Date: " + map_articles.get(choice).getDate());
					System.out.println("Testo: " + map_articles.get(choice).getText());
				}
				error = false;
			} catch (MalformedURLException e) {
				System.out.println("Sito web non valido");
				error = true;
				
			} catch (WebsiteNotFoundException e) {
				System.out.println("Sito web non trovato");
				error = true;
				
			} catch (StorageException e) {
				System.out.println("Problema con la repository");
				error = false;
				e.printStackTrace();
			} 
			
		}while(error);
		
	}

	private static int getInput(int min, int max){
		
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
