package it.univaq.tlp.webscraper.aggregatordata.controller;


import java.util.Set;

import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.aggregatordata.URL;
import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;

public interface ConnectorInterface {

	public Set<AggregatedData> collect(Website website, URL url, boolean is_list) throws TemplateNotFoundException, ResponseException;
	
}
