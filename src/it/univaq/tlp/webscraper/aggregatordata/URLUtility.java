package it.univaq.tlp.webscraper.aggregatordata;

import java.net.URL;

public class URLUtility {

	public static String getHost(URL url){
		
		String host = url.getHost();
		
		if(host.startsWith("www.")){
			host = host.substring(4);
		}
		
		return host;
	}
	
	public static String getContext(URL url){
		
		String path = url.getPath();
		String context;
		if(path.length()>0){
			context = path.substring(1)+"/";
			context = context.substring(0, context.indexOf("/"));
		} else {
			context = "";
		}
		
		return context;
			
	}
	
	public static String conformURL(String url){
		
		// L'url deve iniziare con 'http://'
		if(!(url.startsWith("http"))) {
			url = "http://" + url;
		}
		
		// Elimina tutto ciò che c'è dopo '?'
		if(url.contains("?")){
			url = url.substring(0, url.indexOf("?"));
		}
		
		// Nel caso l'url finisce per '/', eliminalo
		if(url.charAt(url.length()-1) == '/'){
			url = url.substring(0, url.length()-1);
		}
		
		return url;
		
	}
	
	public static boolean isList(URL url){
		System.out.println(url.toString());
		if(URLUtility.getContext(url).equals("")) return true;
		
		if(url.toString().equals("http://www." + URLUtility.getHost(url) + "/" + URLUtility.getContext(url))) return true;
		
		return false;
		
	}
		
	
}
