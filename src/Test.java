import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class Test {

	public static void main(String[] args) throws JauntException {
		UserAgent userAgent = new UserAgent();      								
	    userAgent.settings.autoSaveAsHTML = true;
	    userAgent.visit("http://www.marcodetoma.altervista.org");       				
	    for(Element ad: userAgent.doc.findFirst("<head>").findEvery("<meta>")){
	    	System.out.println(ad);
	    	
	    }
	    System.out.println("end");
	}

}
