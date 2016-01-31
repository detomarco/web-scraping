package it.univaq.tlp.webscraper;

import it.univaq.tlp.webscraper.controller.UserInterface;
import it.univaq.tlp.webscraper.controller.repository.Storable;
import it.univaq.tlp.webscraper.controller.repository.StorageException;
import it.univaq.tlp.webscraper.controller.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.view.gui.Dialog;
import it.univaq.tlp.webscraper.view.gui.GUI;;


public class ProgramGUI {

	public static void main(String[] args) throws Exception {
		try {
			Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
			UserInterface console = new GUI(storage);
			console.run();
			
		} catch (StorageException e) {
			Dialog dialog = new Dialog(Dialog.ERROR_DATABASE);
			dialog.open();
		}
	
	}

}
