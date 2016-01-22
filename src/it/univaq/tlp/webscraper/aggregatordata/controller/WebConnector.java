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
import it.univaq.tlp.webscraper.aggregatordata.URLUtility;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;

/**
 * Questa classe rappresenta il connettore web, fornisce l'implementazione della funzione di interfaccia collect
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
	 * Metodo che si occupa di collezionare gli articoli referenziati sul sito web da quell'url
	 * @param website
	 * @param url
	 * @param is_list
	 * @return List<AggregatedData>
	 * @throws TemplateNotFoundException
	 */
	@Override
	public List<AggregatedData> collect(Website website, URL url, boolean is_list) throws TemplateNotFoundException{
		
		List <URL> urls = new LinkedList<>();
	
		if (is_list){
			
			String context = URLUtility.getWebsiteContext(url);
						
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
		
		return getAllArticles(website, urls); // Può rilanciare l'eccezione TemplateNotFound, deve essere gestita dal chiamante
	}
	
	
	/**
	 * Metodo che recupera gli aricoli referenziati da una lista di url
	 * (Nota: gli url devono puntare direttamente ad articoli, e non ad elenchi)
	 * @param website
	 * @param urls
	 * @return List<AggregatedData>
	 * @throws TemplateNotFoundException
	 */
	public List<AggregatedData> getAllArticles(Website website, List<URL> urls) throws TemplateNotFoundException{
		
		List<AggregatedData> articles = new LinkedList<>();
		ArticleTemplate template = null;
		boolean template_found;
		boolean supposed_to_be_article;
		
		for(URL current_url: urls){
			template_found = false;
			
			String context = URLUtility.getHostFromURL(current_url);
			String host = URLUtility.getHostFromURL(current_url);
			
			String path = current_url.getPath();
			if(path.contains("/foto/") || path.contains("/video/") || path.contains("/gallery/")){
				supposed_to_be_article = false;
			} else {
				supposed_to_be_article = true;
			}
			
			if(host.equals(website.getAddress()) && supposed_to_be_article){
				for(ArticleTemplate current_template: website.getArticleTemplates()){
					if(current_template.getContext().equals(context)){
						template = current_template;
						template_found = true;
						break;
					}
				}
			}
			try{
				if(template_found){
					articles.add(getArticle(template, current_url));
				} else {
					throw new TemplateNotFoundException();
				}
			} catch (TemplateNotFoundException e){
				// Se non è stato trovato il template, nel caso in cui si stava cercando un solo articolo
				// viene rilanciata l'eccezione a collect
				if(urls.size()==1){
					throw e;
				}
			}
		}
		return articles;
	}
}
