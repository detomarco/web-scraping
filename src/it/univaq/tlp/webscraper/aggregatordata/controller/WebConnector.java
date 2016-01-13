package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import jodd.jerry.Jerry;

public class WebConnector {
	
	private UserAgent userAgent;
	
	public WebConnector(){
		this.userAgent = new UserAgent();
		userAgent.settings.autoSaveAsHTML = true;
	}
		
	public AggregatedData collectArticleData(ArticleTemplate template, String url){
		
		// Pezzo comune overload metodo
		AggregatedData data = new AggregatedData();
		
		try{
			userAgent.visit(url);		    
		} catch (ResponseException e){
			e.printStackTrace();
		}
		
		String HTML = "";
		
		try{
			HTML = userAgent.doc.getFirst("<html>").innerHTML();
		} catch (NotFound e){
			e.printStackTrace();
		}
		// ...fine pezzo comune	
		
		Jerry doc = Jerry.jerry(HTML);
		
		// Estrazione informazioni ed inserimento nell'oggetto
		data.putTitle((doc.$(template.getTitleSelector()).html()));
		data.putText((doc.$(template.getTextSelector()).html()));
		data.putAuthor((doc.$(template.getTextSelector()).html()));
		
		String date = doc.$(template.getDateSelector()).html();
		DateFormat df = new SimpleDateFormat(template.getDateFormat());
		Date parsedDate;
		
		try{
			parsedDate = df.parse(date);
		} catch (ParseException e){
			parsedDate = null;
		}
		
		data.putDate(parsedDate);
		data.putSource(url);
		
	    return data;
	}
	
	
	
	
}
