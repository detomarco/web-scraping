package it.univaq.tlp.webscraper;

import it.univaq.tlp.webscraper.controller.repository.Storable;
import it.univaq.tlp.webscraper.controller.repository.StorageException;
import it.univaq.tlp.webscraper.controller.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.view.UserInterface;
import it.univaq.tlp.webscraper.view.gui.GUI;;


public class ProgramGUI {

	public static void main(String[] args) {
		
		Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
		
		UserInterface console;
		try {
			console = new GUI(storage);
			console.run();
			
		} catch (StorageException e) { }
			
		
	}
	
}
