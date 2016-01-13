import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

import jodd.jerry.Jerry;

//import jodd.io.FileUtil;
//import jodd.io.NetUtil;
//import jodd.jerry.Jerry;
//import jodd.util.SystemUtil;

//import static jodd.jerry.Jerry.jerry;

public class Test {

	public static void main(String[] args) throws JauntException {
		UserAgent userAgent = new UserAgent();      								
	    userAgent.settings.autoSaveAsHTML = true;
	    userAgent.visit("http://www.marcodetoma.altervista.org");   
//	    for(Element node: userAgent.doc.findFirst("<head>").findEvery("<meta>")){
//	    	System.out.println(node.getAttx("name")+": "+ node.getAttx("content"));
//
//	    }
	    
	    String HTML = userAgent.doc.getFirst("<html>").innerHTML();
	    
	    System.out.println(HTML);
	    
		Jerry doc = Jerry.jerry(HTML);
	}
	
}
