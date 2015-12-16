import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class Test {

	public static void main(String[] args) throws JauntException {
		UserAgent userAgent = new UserAgent();      								
	    userAgent.settings.autoSaveAsHTML = true;
	    userAgent.visit("http://www.marcodetoma.altervista.org");   
	    Elements asd;
	    for(Element node: userAgent.doc.findFirst("<head>").findEvery("<meta>")){
	    	System.out.println(node.getAttx("name")+": "+ node.getAttx("content"));

	    }
	    System.out.println(userAgent.doc.innerHTML());
	    System.out.println("end");
	}
	
	//Prova push Gianluca

}
