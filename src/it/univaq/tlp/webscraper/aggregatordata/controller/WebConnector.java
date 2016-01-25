package it.univaq.tlp.webscraper.aggregatordata.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
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
		// Se l'url è di un articolo
		if (!is_list){
				// Aggiungi direttamente l'articolo
				urls.add(url);
				
		// Altrimenti, cerca nella pagina gli articoli da aggiungere
		}else {
			
			// Recupera il contesto dell'articolo
			String context = URLUtility.getContext(url);
						
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
			
			System.out.println("Sono stati trovati "+urls.size()+" articoli");
			
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
		
		// Per ogni url
		for(URL current_url: urls){
			template_found = false;
			
			// Recupera host e context
			String host = URLUtility.getHost(current_url);
			String context = URLUtility.getContext(current_url);
			
			
			// Se l'host dell'url corrente corrisponde all'host del sito
			if(host.equals(website.getAddress())){
				
				String path = current_url.getPath();
				// Filtro per non recuperare dati non desidarati
				if(!(path.contains("/foto/") || path.contains("/video/") || path.contains("/gallery/"))){
					
					// Verifica se si conosce il template dell'articolo
					for(ArticleTemplate current_template: website.getArticleTemplates()){
						if(current_template.getContext().equals(context)){
							template = current_template;
							template_found = true;
							break;
						}
					}
				}
			}
			
			// Se il template è stato trovato
			if(template_found){
				// Aggiungi l'articolo
				articles.add(getArticle(template, current_url));
			} else {
				// Se non è stato trovato il template, nel caso in cui si stava cercando un solo articolo
				if(urls.size()==1){
					// viene sollevata l'eccezione a collect
					throw new TemplateNotFoundException("Spiacente, non è stato trovato il template di questo articolo: " + current_url);
				}
				
				// Se gli articoli sono più di uno, non viene sollevata alcuna eccezione per permmetere l'aggiunta degli articoli successivi
			}
				
		}
		
		return articles;
		
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
		
		if(!(template.getHeadingSelector().equals("")) && (template.getHeadingSelector()!=null)){
			data.putHeading((doc.$(template.getHeadingSelector()).html()));
		} else {
			data.putHeading("");
		}
		
		if(!(template.getEyeletSelector().equals("")) && (template.getEyeletSelector()!=null)){
			data.putEyelet((doc.$(template.getEyeletSelector()).html()));	
		} else {
			data.putEyelet("");
		}
		
		if(!(template.getSummarySelector().equals("")) && (template.getSummarySelector()!=null)){
			data.putSummary((doc.$(template.getSummarySelector()).html()));
		} else {
			data.putSummary("");
		}
		
		if(!(template.getTextSelector().equals("")) && (template.getTextSelector()!=null)){
			data.putText((doc.$(template.getTextSelector()).text()));
		} else {
			data.putText("");
		}
		
		if(!(template.getAuthorSelector().equals("")) && (template.getAuthorSelector()!=null)){
			data.putAuthor((doc.$(template.getAuthorSelector()).html()));
		} else {
			data.putAuthor("");
		}
				
		if(!(template.getDateSelector().equals("")) && (template.getDateSelector()!=null)){
			data.putDate((doc.$(template.getDateSelector()).html()), template.getDateFormat());
		} else {
			data.putDate(new Date());
		}
		
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
	

}
