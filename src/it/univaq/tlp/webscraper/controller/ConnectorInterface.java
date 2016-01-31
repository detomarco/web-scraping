package it.univaq.tlp.webscraper.controller;


import java.util.Set;

import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.controller.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.model.website.Website;
import it.univaq.tlp.webscraper.utility.URL;

interface ConnectorInterface {

	public Set<AggregatedData> collect(Website website, URL url, boolean is_list) throws TemplateNotFoundException, ResponseException;
	
}
