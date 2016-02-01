package it.univaq.tlp.webscraper.controller;


import java.util.Set;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.controller.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.model.website.Website;
import it.univaq.tlp.webscraper.utility.URL;

/**
 * 
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 *
 */
interface ConnectorInterface {

	/**
	 * This method collects all article datas from the web
	 * @param website
	 * @param url
	 * @param is_list
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws ResponseException
	 */
	public Set<AggregatedData> collect(Website website, URL url, boolean is_list) throws TemplateNotFoundException, ResponseException, NotFound;
	
}
