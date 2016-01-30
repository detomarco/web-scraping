package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.view.UserInterface;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

public class GUI extends UserInterface{

	protected Shell shell;
	private Text indirizzo;
	private Text nome;
	private Text descrizione;
	private Text home_list;
	private Text header;
	private Text summary;
	private Text eyelet;
	private Text author;
	private Text context;
	private Text date;
	private Text text;
	
	
	/**
	 */
	public GUI(Storable storage) {
		super(storage);
	}


	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void run() {
		this.open();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
	    shell = new Shell();
	    shell.setSize(780, 450);
	    shell.setText("SWT Application");
	    shell.setLayout(null);
	    
	    //BARRA TAB
	    CTabFolder tabBar = new CTabFolder(shell, SWT.BORDER);
	    tabBar.setBounds(0, 0, 780, 428);
	    tabBar.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
	    
	    //TAB CRWALER
	    CTabItem tabCrawler = new CTabItem(tabBar, SWT.NONE);
	    tabCrawler.setText("Crawler");
	    
		    //GRUPPO CRAWLER
		    Group grpCrawler = new Group(tabBar, SWT.NONE);
		    grpCrawler.setText("Crawler");
		    tabCrawler.setControl(grpCrawler);
			
			    //URL
				Text url = new Text(grpCrawler, SWT.BORDER);
				url.setBounds(38, 88, 683, 25);
				url.setText("Write url here");
				
				//ARTICOLI AGGIUNTI
				Label lblAggiuntiArticoli = new Label(grpCrawler, SWT.NONE);
				lblAggiuntiArticoli.setBounds(327, 331, 106, 25);
				lblAggiuntiArticoli.setVisible(false);
			    
				//BUTTON INSERISCI ARTICOLO
				Button addArticle = new Button(grpCrawler, SWT.NONE);
				addArticle.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
						try {
							scrap(url.getText());
						} catch (MalformedURLException | WebsiteNotFoundException | TemplateNotFoundException
								| StorageException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							lblAggiuntiArticoli.setText("Aggiunti: "+last_insert+" articoli");
							lblAggiuntiArticoli.setVisible(true);
						}
						
					}
				});
				addArticle.setBounds(304, 139, 152, 60);
				addArticle.setText("Inserisci articolo");

		
		
		//TAB ARTICOLI
	    CTabItem tabArticles = new CTabItem(tabBar, SWT.NONE);
	    tabArticles.setText("Articoli");
	    
		    //GRUPPO ARTICOLI
		    Group grpArticoli = new Group(tabBar, SWT.NONE);
		    grpArticoli.setText("Articoli");
		    tabArticles.setControl(grpArticoli);
		    grpArticoli.setLayout(null);
		    
			    //PRIMA SEZIONE
			    Composite frame1 = new Composite(grpArticoli, SWT.NONE);
			    frame1.setBounds(1, 0, 196, 381);
			    
				    //SORGENTE
				    Label lblSorgente = new Label(frame1, SWT.NONE);
				    lblSorgente.setBounds(10, 108, 59, 18);
				    lblSorgente.setText("Sorgente");
				    
				    //CONTESTO
				    Label lblContesto = new Label(frame1, SWT.NONE);
				    lblContesto.setBounds(10, 150, 59, 18);
				    lblContesto.setText("Contesto");
				    
				    //SORGENTE
				    Combo sorgente = new Combo(frame1, SWT.NONE);
				    sorgente.setBounds(75, 104, 113, 22);
				    
				    //SELECT CONTESTO
				    Combo contesto = new Combo(frame1, SWT.NONE);
				    contesto.setBounds(75, 146, 111, 22);
				    
				    //BOTTONE CERCA
				    Button btnSearch = new Button(frame1, SWT.NONE);
				    btnSearch.setBounds(36, 244, 124, 45);
				    btnSearch.setText("Cerca");
				    
					//SEPARATORE
					Label label = new Label(frame1, SWT.SEPARATOR | SWT.VERTICAL);
					label.setBounds(194, 10, 2, 361);
			    
					
			    //SECONDA SEZIONE
			    Composite frame2 = new Composite(grpArticoli, SWT.NONE);
			    frame2.setBounds(197, 0, 196, 381);
			    
				    //LISTA ARTICOLI
				    List list = new List(frame2, SWT.BORDER | SWT.V_SCROLL);
				    list.setBounds(10, 10, 176, 361);
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("jknjcdc");
				    list.add("rkmrfeed");
		
			    //TERZA SEZIONE
			    Composite frame3 = new Composite(grpArticoli, SWT.NONE);
			    frame3.setBounds(392, 0, 383, 381);
			    
				    //INFO ARTICOLO
				    Group grpInfoArticle = new Group(frame3, SWT.NONE);
				    grpInfoArticle.setText("Info Articolo");
				    grpInfoArticle.setBounds(0, 0, 383, 375);
	    
	    //TAB GESTIONE
	    CTabItem tabGestione = new CTabItem(tabBar, SWT.NONE);
	    tabGestione.setText("Gestione");
	    
		    //FRAME CONTAINER
		    Composite container = new Composite(tabBar, SWT.NONE);
		    tabGestione.setControl(container);
		    
			    //FRAME 1
			    Composite frameTemplate = new Composite(container, SWT.NONE);
			    frameTemplate.setBounds(0, 0, 388, 393);
		
				    //GRUPPO INSERIMENTO TEMPLATE
				    Group grpInserimentoTemplate = new Group(frameTemplate, SWT.NONE);
				    grpInserimentoTemplate.setText("Inserimento Template");
				    grpInserimentoTemplate.setBounds(0, 0, 388, 393);
				    
					    //LABEL SITOWEB
					    Label lblSelezionaSitoWeb = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelezionaSitoWeb.setBounds(10, 14, 111, 14);
					    lblSelezionaSitoWeb.setText("Seleziona sito web");
					    
					    //LABEL HOMELIST
					    Label lblSelettoreHomeList = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreHomeList.setBounds(10, 42, 124, 14);
					    lblSelettoreHomeList.setText("Selettore home list");
					    
					    //LABEL INTESTAZIONE
					    Label lblSelettoreIntestazione = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreIntestazione.setBounds(10, 70, 136, 14);
					    lblSelettoreIntestazione.setText("Selettore intestazione");
					    
					    //LABEL SOMMARIO
					    Label lblSelettoreSommario = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreSommario.setBounds(10, 98, 124, 14);
					    lblSelettoreSommario.setText("Selettore sommario");
					    
					    //LABEL EYELET
					    Label lblocchiello = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblocchiello.setBounds(10, 126, 124, 14);
					    lblocchiello.setText("Selettore occhiello");
					    
					    //LABEL TESTO
					    Label lblSelettoreTesto = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreTesto.setBounds(10, 154, 100, 14);
					    lblSelettoreTesto.setText("Selettore testo");
					    
					    //LABEL AUTORE
					    Label lblSelettoreAutore = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreAutore.setBounds(10, 182, 111, 14);
					    lblSelettoreAutore.setText("Selettore autore");
					    
					    //LABEL DATA
					    Label lblSelettoreData = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreData.setBounds(10, 210, 111, 14);
					    lblSelettoreData.setText("Selettore data");
					    
					    //LABEL CONTESTO
					    Label lblNomeContesto = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblNomeContesto.setBounds(10, 238, 100, 14);
					    lblNomeContesto.setText("Nome contesto");
					    
					    //SELECT WEBSITE
					    Combo web_site = new Combo(grpInserimentoTemplate, SWT.NONE);
					    web_site.setBounds(188, 10, 186, 22);
					    web_site.add("opzione 1");
					    web_site.add("opzione 2");
					    web_site.add("opzione 3");
					    
					    //TEXTBOX HOMELIST
					    home_list = new Text(grpInserimentoTemplate, SWT.BORDER);
					    home_list.setBounds(188, 39, 186, 19);
					    
					    //TEXTBOX INTESTAZIONE
					    header = new Text(grpInserimentoTemplate, SWT.BORDER);
					    header.setBounds(188, 67, 186, 19);
					    
					    //TEXTBOX SOMMARIO
					    summary = new Text(grpInserimentoTemplate, SWT.BORDER);
					    summary.setBounds(188, 95, 186, 19);
					    
					    //TEXTBOX OCCHIELLO
					    eyelet = new Text(grpInserimentoTemplate, SWT.BORDER);
					    eyelet.setBounds(188, 123, 186, 19);
					    
					    //TEXTBOX TESTO
					    text = new Text(grpInserimentoTemplate, SWT.BORDER);
					    text.setBounds(188, 149, 186, 19);
					    
					    //TEXTBOX AUTORE
					    author = new Text(grpInserimentoTemplate, SWT.BORDER);
					    author.setBounds(188, 177, 186, 19);
					    
					    //TEXTBOX DATA
					    date = new Text(grpInserimentoTemplate, SWT.BORDER);
					    date.setBounds(188, 205, 186, 19);
					    
					    //TEXTBOX CONTESTO
					    context = new Text(grpInserimentoTemplate, SWT.BORDER);
					    context.setBounds(188, 233, 186, 19);
					   
					    //BUTTON ADDTEMPLATE
					    Button addTemplate = new Button(grpInserimentoTemplate, SWT.NONE);
					    addTemplate.setBounds(259, 327, 115, 39);
					    addTemplate.setText("Aggiungi");
					    
					    //BUTTON RESET_TEMPLATE
					    Button reset_template = new Button(grpInserimentoTemplate, SWT.NONE);
					    reset_template.setText("Reset");
					    reset_template.setBounds(10, 338, 71, 28);
					    reset_template.addMouseListener(new MouseAdapter() {
					    	@Override
					    	public void mouseDown(MouseEvent arg0) {
					    		
					    		web_site.setText("");
					    		home_list.setText("");
					    		header.setText("");
					    		summary.setText("");
					    		eyelet.setText("");
					    		text.setText("");
					    		author.setText("");
					    		date.setText("");
					    		context.setText("");
					    		
					    		
					    	}
					    });
			    
			    //FRAME 2
			    Composite frameWebSite = new Composite(container, SWT.NONE);
			    frameWebSite.setBounds(386, 0, 388, 393);
			    
				    //GRUPPO INSERIMENTO WEBSITE
				    Group grpInserimentoWebsite = new Group(frameWebSite, SWT.NONE);
				    grpInserimentoWebsite.setText("Inserimento Website");
				    grpInserimentoWebsite.setBounds(0, 0, 388, 393);
				    
				    //INDIRIZZO
				    Label lblIndirizzo = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblIndirizzo.setBounds(10, 14, 59, 14);
				    lblIndirizzo.setText("Indirizzo:");
				    indirizzo = new Text(grpInserimentoWebsite, SWT.BORDER);
				    indirizzo.setBounds(101, 10, 273, 19);
				    
				    //NOME
				    Label lblNome = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblNome.setBounds(10, 42, 59, 14);
				    lblNome.setText("Nome:");
				    nome = new Text(grpInserimentoWebsite, SWT.BORDER);
				    nome.setBounds(101, 39, 273, 19);
				    
				    //DESCRIZIONE
				    Label lblDescrizione = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblDescrizione.setBounds(10, 70, 80, 14);
				    lblDescrizione.setText("Descrizione:");
				    descrizione = new Text(grpInserimentoWebsite, SWT.BORDER);
				    descrizione.setBounds(101, 67, 273, 78);
				    
				    //BUTTON ADD_WEBSITE
				    Button addWebSite = new Button(grpInserimentoWebsite, SWT.NONE);
				    addWebSite.addMouseListener(new MouseAdapter() {
				    	@Override
				    	public void mouseDown(MouseEvent arg0) {
				    		
				    		Website website = new Website(nome.getText(), indirizzo.getText(), descrizione.getText());
				    		
				    		try {
								insertWebsite(website);
								Dialog dialogtest = new Dialog(Dialog.SUCCESSFUL_INSERT);
								dialogtest.open();
							} catch (MalformedURLException | StorageException | WebsiteAlreadyExistsException e) {
								Dialog dialog = new Dialog(Dialog.ERROR_INSERT);
								dialog.open();
							}
				    	}
				    });
				    addWebSite.setBounds(248, 327, 126, 39);
				    addWebSite.setText("Aggiungi");
				    
				    //BUTTON RESET_WEBSITE
				    Button reset_website = new Button(grpInserimentoWebsite, SWT.NONE);
				    reset_website.addMouseListener(new MouseAdapter() {
				    	@Override
				    	public void mouseDown(MouseEvent arg0) {
				    		
				    		nome.setText("");
				    		indirizzo.setText("");
				    		descrizione.setText("");
				    		
				    	}
				    });
				    reset_website.setBounds(10, 332, 71, 28);
				    reset_website.setText("Reset");
	   
	}
}
