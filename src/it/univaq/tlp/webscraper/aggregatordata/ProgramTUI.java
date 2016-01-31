package it.univaq.tlp.webscraper.aggregatordata;

import it.univaq.tlp.webscraper.aggregatordata.controller.UserInterface;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.aggregatordata.view.TUI;

public class ProgramTUI {

	public static void main(String[] args) {
		try {
			Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
			UserInterface console = new TUI(storage);
			console.run();
			
		} catch (StorageException e) {
			// Se la connessione non è andata a buon fine
			System.out.println("Problema di connessione con la repository, si prega di riprovare più tardi");
			System.out.println("Dettagli errore:\n" + e.getMessage());
		}
	
	}

}
