package it.univaq.tlp.webscraper;

import it.univaq.tlp.webscraper.controller.UserInterface;
import it.univaq.tlp.webscraper.controller.repository.Storable;
import it.univaq.tlp.webscraper.controller.repository.StorageException;
import it.univaq.tlp.webscraper.controller.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.view.TUI;

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
