package it.univaq.tlp.webscraper.aggregatordata;

import it.univaq.tlp.webscraper.aggregatordata.controller.UserInterface;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.aggregatordata.view.gui.GUI;
import it.univaq.tlp.webscraper.aggregatordata.view.gui.Dialog;;


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
