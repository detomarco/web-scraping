package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.List;

import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;

public interface ConnectorInterface {

	public List<AggregatedData> collect(Website website, String url, boolean is_list);
	
}
