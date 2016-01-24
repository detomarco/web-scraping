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
		
		if(!(url.startsWith("http"))) {
			url = "http://" + url;
		}
		
		return url;
		
	}
		
}
