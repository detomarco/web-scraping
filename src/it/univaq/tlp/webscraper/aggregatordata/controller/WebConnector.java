package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Template;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import jodd.jerry.Jerry;

public class WebConnector implements ConnectorInterface{
	
	private UserAgent userAgent;
	private WebsiteManaging website_manager;
	
	public WebConnector(WebsiteManaging website_manager){
		this.website_manager = website_manager;
		this.userAgent = new UserAgent();
		userAgent.settings.autoSaveAsHTML = true;
	}
	
	
	public AggregatedData getArticle(ArticleTemplate template, String url){
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
//			e.printStackTrace();
			
		}
		// ...fine pezzo comune	
		
		Jerry doc = Jerry.jerry(HTML);
		
		// Estrazione informazioni ed inserimento nell'oggetto
		data.putTitle((doc.$("head title").html()));
		if(template.getHeadingSelector() != null) 	data.putHeading((doc.$(template.getHeadingSelector()).html()));
		if(template.getEyeletSelector() != null) 	data.putEyelet((doc.$(template.getEyeletSelector()).html()));
		if(template.getSummarySelector() != null) 	data.putSummary((doc.$(template.getSummarySelector()).html()));
		if(template.getTextSelector() != null) 		data.putText((doc.$(template.getTextSelector()).text()));
		if(template.getAuthorSelector() != null) 	data.putAuthor((doc.$(template.getAuthorSelector()).html()));
		if(template.getDateSelector() != null) 		data.putDate((doc.$(template.getDateSelector()).html()), template.getDateFormat());
		
		data.putSource(url);
		
		// Inserimento metadati
		Map<String, String> metadata = new HashMap<String, String>();
		
		try{
		    for(Element node: userAgent.doc.findFirst("<head>").findEvery("<meta>")){
		    	// Se � presente un contenuto (verificare che questo sia il controllo giusto)
		    	if(!node.getAttx("content").equals("")){
		    		data.addMetadata(node.getAttx("name"), node.getAttx("content"));
		    	}
		    }
	    } catch (NotFound e){ }
		
		// Inserimento didascalie delle immagini
		List img_caption = new ArrayList<>();
	    for(Element node: userAgent.doc.findEvery("<img>")){
	    	// Se � presente una didascalia (verificare che questo sia il controllo giusto)
	    	if(!node.getAttx("alt").equals("")){
	    		data.addImgCaption(node.getAttx("alt"));
	    	}
	    	
	    }
	    
	    return data;
	}
	
	public List<AggregatedData> collect(Website website, String url, boolean is_list){
		
		// Pezzo comune overload metodo	
		List <AggregatedData> list = new LinkedList<>();
	
		if (is_list){
			
			ArticleListTemplate template = website_manager.getTemplate(website, context, is_list);
			
			
		}
	}
}
