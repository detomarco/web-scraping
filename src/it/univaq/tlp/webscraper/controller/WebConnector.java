package it.univaq.tlp.webscraper.controller;

import java.net.MalformedURLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import it.univaq.tlp.webscraper.controller.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.model.webdata.AggregatedData;
import it.univaq.tlp.webscraper.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.model.website.Website;
import it.univaq.tlp.webscraper.utility.URL;
import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;

/**
 * This class represents the Web Connector element, and provides the collect method implementing ConnectorInterface
 * @author Gianluca Filippone
 * @author Marco De Toma
 * @author Alessandro D'Errico
 */
class WebConnector implements ConnectorInterface{
	
	private UserAgent agent;
	
	WebConnector(){
		this.agent = new UserAgent();
		agent.settings.autoSaveAsHTML = true;
		agent.settings.autoRedirect = false;
	}
	
	@Override
	public Set<AggregatedData> collect(Website website, URL url) throws TemplateNotFoundException, ResponseException, NotFound{
		
		Set <URL> urls = new LinkedHashSet<>();
		
		// Se l'url è di un articolo
		if (!url.isList()){
				// Aggiungi direttamente l'articolo
				urls.add(url);
				
		// Altrimenti, cerca nella pagina gli articoli da aggiungere
		}else {
			
			// Recupera il contesto dell'articolo
			String context = url.getContext();
						
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

			agent.visit(url.getSource());		    

			String HTML = agent.doc.getFirst("<html>").innerHTML();
			
			Jerry doc = Jerry.jerry(HTML);

			doc.$(link_selector).each(new JerryFunction() {
				@Override
				public boolean onNode(Jerry $this, int index) {
					try {
						
						urls.add(new URL($this.attr("href")));
					} catch (MalformedURLException e) { }
					return true;
				}
			});	
						
		} 
		
		return getAllArticles(website, urls); // Può rilanciare l'eccezione TemplateNotFound, deve essere gestita dal chiamante
	}
	
	
	/**
	 * This method collets articles from set of urls of given website
	 * @param website
	 * @param urls
	 * @return Set of AggregatedData
	 * @throws TemplateNotFoundException
	 * @throws ResponseException
	 */
	public Set<AggregatedData> getAllArticles(Website website, Set<URL> urls) throws TemplateNotFoundException, ResponseException{
		
		Set<AggregatedData> articles = new LinkedHashSet<>();
		ArticleTemplate template = null;
		boolean template_found;
		
		// Per ogni url
		for(URL current_url: urls){
			template_found = false;
			
			// Recupera host e context
			String host = current_url.getHost();
			String context = current_url.getContext();
			
			
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
	 * This method collets articles from set of url of given website
	 * @param template
	 * @param url
	 * @return article found
	 * @throws ResponseException
	 */
	public AggregatedData getArticle(ArticleTemplate template, URL url) throws ResponseException{
		
		AggregatedData article = new AggregatedData();
		
		agent.visit(url.getSource());		    
		
		// PULIZIA CODICE SORGENTE
		
			// lista di nodi da eliminare
			String[] nodes = {"link", "style", "script"};
			
			for(String e: nodes){
				for(Element node: agent.doc.findEvery("<"+e+">")){
					node.removeChildren();
					node.erase();
			    }
			}
		
		String HTML = "";
		try{
			HTML = agent.doc.getFirst("<html>").innerHTML();
		} catch (NotFound e){ }
		
		Jerry doc = Jerry.jerry(HTML);
		
		// Estrazione informazioni ed inserimento nell'oggetto
		article.putTitle((doc.$("head title").html()));
		
		if(!(template.getHeadingSelector().equals("")) && (template.getHeadingSelector()!=null)){
			article.putHeading((doc.$(template.getHeadingSelector()).text()));
		}  
		
		if(!(template.getEyeletSelector().equals("")) && (template.getEyeletSelector()!=null)){
			article.putEyelet((doc.$(template.getEyeletSelector()).text()));	
		} 
		
		if(!(template.getSummarySelector().equals("")) && (template.getSummarySelector()!=null)){
			article.putSummary((doc.$(template.getSummarySelector()).text()));
		}  
		
		if(!(template.getTextSelector().equals("")) && (template.getTextSelector()!=null)){
			article.putText((doc.$(template.getTextSelector()).text()));
		}  
		
		if(!(template.getAuthorSelector().equals("")) && (template.getAuthorSelector()!=null)){
			article.putAuthor((doc.$(template.getAuthorSelector()).text()));
		}  
				
		if(!(template.getDateSelector().equals("")) && (template.getDateSelector()!=null)){
			article.putDate((doc.$(template.getDateSelector()).text()));
		}  
		
		article.putContext(url.getContext());
		
		article.putSource(url.getSource());
		
		// Inserimento metadati		
		try{
		    for(Element node: agent.doc.findFirst("<head>").findEvery("<meta>")){
		    	
		    	// Se è presente un contenuto
		    	if(!node.getAt("content").equals("") && !node.getAt("name").equals("")){
		    		article.addMetadata(node.getAt("name"), node.getAt("content"));
		    	}
		    	
		    }
	    } catch (NotFound e){ }
	    
	    return article;
	}
	

}
