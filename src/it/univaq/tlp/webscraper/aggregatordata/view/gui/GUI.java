package it.univaq.tlp.webscraper.aggregatordata.view.gui;

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
	    shell.setSize(600, 280);
	    shell.setText("SWT Application");
	    shell.setLayout(null);
	    
	    //BARRA TAB
	    CTabFolder tabBar = new CTabFolder(shell, SWT.BORDER);
	    tabBar.setBounds(0, 0, 600, 258);
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
				url.setBounds(71, 22, 437, 24);
				url.setText("Write url here");
				
				//ARTICOLI AGGIUNTI
				Label lblAggiuntiArticoli = new Label(grpCrawler, SWT.NONE);
				lblAggiuntiArticoli.setBounds(237, 161, 106, 25);
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
				addArticle.setBounds(214, 81, 152, 60);
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
			    frame1.setBounds(1, 0, 196, 203);
			    
				    //SORGENTE
				    Label lblSorgente = new Label(frame1, SWT.NONE);
				    lblSorgente.setBounds(16, 32, 59, 18);
				    lblSorgente.setText("Sorgente");
				    
				    //CONTESTO
				    Label lblContesto = new Label(frame1, SWT.NONE);
				    lblContesto.setBounds(16, 74, 59, 18);
				    lblContesto.setText("Contesto");
				    
				    //SORGENTE
				    Combo sorgente = new Combo(frame1, SWT.NONE);
				    sorgente.setBounds(81, 28, 94, 22);
				    
				    //SELECT CONTESTO
				    Combo contesto = new Combo(frame1, SWT.NONE);
				    contesto.setBounds(81, 70, 94, 22);
				    
				    //BOTTONE CERCA
				    Button btnSearch = new Button(frame1, SWT.NONE);
				    btnSearch.setBounds(36, 122, 124, 45);
				    btnSearch.setText("Cerca");
				    
					//SEPARATORE
					Label label = new Label(frame1, SWT.SEPARATOR | SWT.VERTICAL);
					label.setBounds(194, 10, 2, 183);
			    
					
			    //SECONDA SEZIONE
			    Composite frame2 = new Composite(grpArticoli, SWT.NONE);
			    frame2.setBounds(197, 0, 196, 203);
			    
				    //LISTA ARTICOLI
				    List list = new List(frame2, SWT.BORDER | SWT.V_SCROLL);
				    list.setBounds(10, 10, 176, 183);
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
			    frame3.setBounds(392, 0, 196, 203);
			    
				    //INFO ARTICOLO
				    Group grpInfoArticle = new Group(frame3, SWT.NONE);
				    grpInfoArticle.setText("Info Articolo");
				    grpInfoArticle.setBounds(0, 0, 196, 196);
	    
	    //TAB GESTIONE
	    CTabItem tabGestione = new CTabItem(tabBar, SWT.NONE);
	    tabGestione.setText("Gestione");
	    
		    //FRAME CONTAINER
		    Composite container = new Composite(tabBar, SWT.NONE);
		    tabGestione.setControl(container);
		    
			    //FRAME 1
			    Composite frameTemplate = new Composite(container, SWT.NONE);
			    frameTemplate.setBounds(0, 0, 298, 223);
		
				    //GRUPPO INSERIMENTO TEMPLATE
				    Group grpInserimentoTemplate = new Group(frameTemplate, SWT.NONE);
				    grpInserimentoTemplate.setText("Inserimento Template");
				    grpInserimentoTemplate.setBounds(0, 0, 298, 223);
			    
			    //FRAME 2
			    Composite frameWebSite = new Composite(container, SWT.NONE);
			    frameWebSite.setBounds(298, 0, 296, 223);
			    
				    //GRUPPO INSERIMENTO WEBSITE
				    Group grpInserimentoWebsite = new Group(frameWebSite, SWT.NONE);
				    grpInserimentoWebsite.setText("Inserimento Website");
				    grpInserimentoWebsite.setBounds(0, 0, 296, 223);
				    
				    //INDIRIZZO
				    Label lblIndirizzo = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblIndirizzo.setBounds(10, 13, 59, 14);
				    lblIndirizzo.setText("Indirizzo:");
				    indirizzo = new Text(grpInserimentoWebsite, SWT.BORDER);
				    indirizzo.setBounds(96, 10, 186, 19);
				    
				    //NOME
				    Label lblNome = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblNome.setBounds(10, 38, 59, 14);
				    lblNome.setText("Nome:");
				    nome = new Text(grpInserimentoWebsite, SWT.BORDER);
				    nome.setBounds(96, 35, 186, 19);
				    
				    //DESCRIZIONE
				    Label lblDescrizione = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblDescrizione.setBounds(10, 63, 80, 14);
				    lblDescrizione.setText("Descrizione:");
				    descrizione = new Text(grpInserimentoWebsite, SWT.BORDER);
				    descrizione.setBounds(96, 60, 186, 78);
				    
				    //BOTTONE AGGIUNGI WEBSITE
				    Button addWebSite = new Button(grpInserimentoWebsite, SWT.NONE);
				    addWebSite.addMouseListener(new MouseAdapter() {
				    	@Override
				    	public void mouseDown(MouseEvent arg0) {
				    		
				    		Website website = new Website(nome.getText(), indirizzo.getText(), descrizione.getText());
				    		
				    		try {
								insertWebsite(website);
								//da implementare un dialog di avvenuto inserimento
							} catch (MalformedURLException | StorageException | WebsiteAlreadyExistsException e) {
								ErrorDialog dialog = new ErrorDialog();
								dialog.open();
								e.printStackTrace();
							}
				    	}
				    });
				    addWebSite.setBounds(156, 157, 126, 39);
				    addWebSite.setText("Aggiungi");
	   
	}
}
