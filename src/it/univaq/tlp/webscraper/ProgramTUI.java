package it.univaq.tlp.webscraper;

import it.univaq.tlp.webscraper.controller.repository.Storable;
import it.univaq.tlp.webscraper.controller.repository.StorageException;
import it.univaq.tlp.webscraper.controller.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.view.TUI;
import it.univaq.tlp.webscraper.view.UserInterface;

public class ProgramTUI {

	public static void main(String[] args) {
		
		Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
		
		try {
			
			UserInterface console = new TUI(storage);
			console.run();
			
		} catch (StorageException e) { }
		
	}

}
