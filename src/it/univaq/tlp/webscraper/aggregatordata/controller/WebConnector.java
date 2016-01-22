package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;

/**
 * Questa classe si occupa di recuperare i dati relativi ad un articolo
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
public class WebConnector implements ConnectorInterface{
	
	private UserAgent userAgent;
	
	public WebConnector(){
		this.userAgent = new UserAgent();
		userAgent.settings.autoSaveAsHTML = true;
		userAgent.settings.autoRedirect = false;
	}
	
	/**
	 * Metodo che restituisce tutti i dati relativi ad un articolo
	 * @param template
	 * @param url
	 * @return AggregatedData
	 */
	public AggregatedData getArticle(ArticleTemplate template, URL url){
		
		System.out.println(template.getEyeletSelector());
		
		AggregatedData data = new AggregatedData();
		
		try{
			userAgent.visit(url.toString());		    
		} catch (ResponseException e){
			e.printStackTrace();
		}
		
		String HTML = "";
		
		try{
			HTML = userAgent.doc.getFirst("<html>").innerHTML();
		} catch (NotFound e){
//			e.printStackTrace();
			
		}
		
		Jerry doc = Jerry.jerry(HTML);
		
		// Estrazione informazioni ed inserimento nell'oggetto
		data.putTitle((doc.$("head title").html()));
		
		if(!(template.getHeadingSelector().equals("")) && (template.getHeadingSelector()!=null))
				data.putHeading((doc.$(template.getHeadingSelector()).html()));
		
		if(!(template.getEyeletSelector().equals("")) && (template.getEyeletSelector()!=null))
				data.putEyelet((doc.$(template.getEyeletSelector()).html()));	
		
		if(!(template.getSummarySelector().equals("")) && (template.getSummarySelector()!=null))
				data.putSummary((doc.$(template.getSummarySelector()).html()));
		
		if(!(template.getTextSelector().equals("")) && (template.getTextSelector()!=null))
				data.putText((doc.$(template.getTextSelector()).text()));
		
		if(!(template.getAuthorSelector().equals("")) && (template.getAuthorSelector()!=null))
				data.putAuthor((doc.$(template.getAuthorSelector()).html()));
		
		if(!(template.getDateSelector().equals("")) && (template.getDateSelector()!=null))
				data.putDate((doc.$(template.getDateSelector()).html()), template.getDateFormat());
		
		data.putSource(url.toString());
		
		// Inserimento metadati		
		try{
		    for(Element node: userAgent.doc.findFirst("<head>").findEvery("<meta>")){
		    	// Se � presente un contenuto (verificare che questo sia il controllo giusto)
		    	if(!node.getAttx("content").equals("")){
		    		data.addMetadata(node.getAttx("name"), node.getAttx("content"));
		    	}
		    }
	    } catch (NotFound e){ }
		
		// Inserimento didascalie delle immagini
	    for(Element node: userAgent.doc.findEvery("<img>")){
	    	// Se � presente una didascalia (verificare che questo sia il controllo giusto)
	    	if(!node.getAttx("alt").equals("")){
	    		data.addImgCaption(node.getAttx("alt"));
	    	}
	    	
	    }
	    
	    return data;
	}
	
	

	/**
	 * Metodo che si occupa di collezionare gli articoli recuperati
	 * @param website
	 * @param url
	 * @param is_list
	 * @return List<AggregatedData>
	 */
	@Override
	public List<AggregatedData> collect(Website website, URL url, boolean is_list) throws TemplateNotFoundException{
		
		List <URL> urls = new LinkedList<>();
		List<AggregatedData> articles = new LinkedList<>();
	
		if (is_list){
			
			String path = url.getPath();
			String context;
			if(path.length()>0){
				context = path.substring(1)+"/";
				context = context.substring(0, context.indexOf("/"));
			} else {
				context = "";
			}
			
			System.out.println(path);
			System.out.println(context);
			
			ArticleListTemplate template = null;

			for(ArticleListTemplate current_template: website.getArticleListTemplates()){
				if(current_template.getContext().equals(context)){
					template = current_template;
					break;
				}
			}
			
			String link_selector = null;
			try{
				link_selector = template.getLinkSelector();
			} catch (NullPointerException e){
				throw new TemplateNotFoundException();
			}
				
			try{
				userAgent.visit(url.toString());		    
			} catch (ResponseException e){
				e.printStackTrace();
			}
			
			String HTML = "";
			
			try{
				HTML = userAgent.doc.getFirst("<html>").innerHTML();
			} catch (NotFound e){
//				e.printStackTrace();	
			}
			
			Jerry doc = Jerry.jerry(HTML);

			doc.$(link_selector).each(new JerryFunction() {
				@Override
				public boolean onNode(Jerry $this, int index) {
					try {
						urls.add(new URL($this.attr("href")));
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					return true;
				}
			});	
			
			System.out.println("Catched "+urls.size()+" links");
			
		} else {
			urls.add(url);
		}
		
		ArticleTemplate template = null;
		int count = 1;
		boolean template_found;
		for(URL current_url: urls){
			template_found = false;
			System.out.println("Getting #" + count++ +": "+current_url.toString());
			String context = current_url.getPath().substring(1)+"/";
			context = context.substring(0, context.indexOf("/"));			
			
			String host = current_url.getHost();
			if(host.startsWith("www.")){
				host = host.substring(4);
			}
			if(host.equals(website.getAddress())){
				for(ArticleTemplate current_template: website.getArticleTemplates()){
					if(current_template.getContext().equals(context)){
						template = current_template;
						template_found = true;
						break;
					}
				}
			}
			if(template_found){
				articles.add(getArticle(template, current_url));
			} else {
				System.out.println("Template not found, skipping...\n");
			}
		}
		
		return articles;
	}
}
