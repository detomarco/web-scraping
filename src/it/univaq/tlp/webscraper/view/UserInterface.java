package it.univaq.tlp.webscraper.view;

import it.univaq.tlp.webscraper.controller.Controller;
import it.univaq.tlp.webscraper.controller.Storable;

public abstract class UserInterface {

	protected Controller controller;
	
	public UserInterface(Storable storage) {
		controller = new Controller(storage);
	}
	
	public abstract void run();
}
