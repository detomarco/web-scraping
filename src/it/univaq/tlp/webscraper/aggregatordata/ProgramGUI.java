package it.univaq.tlp.webscraper.aggregatordata;
import org.eclipse.swt.widgets.Shell;

import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.aggregatordata.view.UserInterface;
import it.univaq.tlp.webscraper.aggregatordata.view.gui.GUI;
import it.univaq.tlp.webscraper.aggregatordata.view.gui.ErrorDialog;;


public class ProgramGUI extends Thread{

	public static void main(String[] args) throws Exception {
		try {
			Storable storage = new MySQLDatabase("root", "root", "localhost", 3306, "web_scraper");
			UserInterface console = new GUI(storage);
			console.run();
			
		} catch (StorageException e) {
			ErrorDialog dialog = new ErrorDialog();
			dialog.open();
		}
	
	}

}
