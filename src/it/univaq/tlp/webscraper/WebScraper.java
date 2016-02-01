package it.univaq.tlp.webscraper;

import it.univaq.tlp.webscraper.controller.database.Database;
import it.univaq.tlp.webscraper.controller.database.MySQLDatabase;
import it.univaq.tlp.webscraper.controller.exception.StorageException;
import it.univaq.tlp.webscraper.view.TUI;
import it.univaq.tlp.webscraper.view.gui.GUI;
import it.univaq.tlp.webscraper.view.UserInterface;

@SuppressWarnings("unused")
public class WebScraper {

	public static void main(String[] args) {
		
		Database storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
		
		try {
			
			UserInterface console = new TUI(storage);
			console.run();
			
		} catch (StorageException e) { }
		
	}

}
