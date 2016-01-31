package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaunt.ResponseException;

import it.univaq.tlp.webscraper.aggregatordata.controller.ArticleManaging;
import it.univaq.tlp.webscraper.aggregatordata.controller.UserInterface;
import it.univaq.tlp.webscraper.aggregatordata.controller.WebsiteManaging;
import it.univaq.tlp.webscraper.aggregatordata.exception.ContextAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.DataOmittedException;
import it.univaq.tlp.webscraper.aggregatordata.exception.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteAlreadyExistsException;
import it.univaq.tlp.webscraper.aggregatordata.exception.WebsiteNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.model.webdata.Article;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleListTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.ArticleTemplate;
import it.univaq.tlp.webscraper.aggregatordata.model.website.Website;
import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
	private List list;
	private Combo sorgente, contesto;
	private String source;

	
	private Map<String, Website> mMap = new HashMap<>();
    WebsiteManaging website = new WebsiteManaging(storage);
    

	
	
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
							Dialog dialog = new Dialog(Dialog.ERROR_INSERT);
							dialog.open();
							e.printStackTrace();
						} catch (ResponseException e) {
							Dialog dialog = new Dialog(Dialog.ERROR_INSERT);
							dialog.open();
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
				    lblSorgente.setBounds(10, 112, 59, 18);
				    lblSorgente.setText("Sorgente");
				    
				    //CONTESTO
				    Label lblContesto = new Label(frame1, SWT.NONE);
				    lblContesto.setBounds(10, 150, 59, 18);
				    lblContesto.setText("Contesto");
				    
				    //SORGENTE
				    sorgente = new Combo(frame1, SWT.NONE);
				    sorgente.addSelectionListener(new SelectionAdapter() {
				    	@Override
				    	public void widgetSelected(SelectionEvent arg0) {
				    		
				    		String name = sorgente.getText();
				    		if(name == null) return;
				    		
				    		Website website = mMap.get(name);
				    		if(website == null) return;
				    		
				    		System.out.println(website.getName() + " | " + website.getAddress());
				    		ArticleManaging article = new ArticleManaging(storage);
				    		try {
				    			contesto.removeAll();
					    		System.out.println("remove contest");
								Set<String> contesti = article.getWebsiteContexts(website);
					    		System.out.println("getWebSite Context");
								if(contesti != null){
									for(String context : contesti){
							    		System.out.println(" wei");
										System.out.println(context);
										contesto.add(context);
									}
								}
								
							} catch (StorageException e) {
								e.printStackTrace();
							}
				    		System.out.println("-------------");
				    	}
				    });
				    sorgente.setBounds(75, 108, 113, 24);
				    try {
						Set<Website> hostlist = website.getAllWebsite();
						for(Website host:hostlist){
							mMap.put(host.getName(), host);
							sorgente.add(host.getName());
					    }
					} catch (StorageException e2) {
						Dialog dialog = new Dialog(Dialog.ERROR_UNKNOWN);
						dialog.open();
						e2.printStackTrace();
					}
				    
				    //CONTESTO
					contesto = new Combo(frame1, SWT.NONE);
					contesto.setBounds(75, 146, 113, 22);
					
				    
				    
					//BOTTONE CERCA
				    Button btnSearch = new Button(frame1, SWT.NONE);
				    btnSearch.addMouseListener(new MouseAdapter() {
				    	@Override
				    	public void mouseDown(MouseEvent arg0) {
						    	
				    		list.removeAll();
								Set<Article> articles;
								try {
									String source = "";
									Set<Website> hostlist = website.getAllWebsite();
									for(Website host:hostlist){
								    	if(sorgente.getText().equals(host.getName())){
								    		source = host.getAddress();
								    		break;
								    	};
								    }
									
									articles = viewWebsiteArticles(source, contesto.getText());
									for(Article article:articles){
								    	list.add(article.getHeading());
								    }
								} catch (MalformedURLException | StorageException | WebsiteNotFoundException e) {
									Dialog dialog = new Dialog(Dialog.ERROR_UNKNOWN);
									dialog.open();
									e.printStackTrace();
								}   
				    	}
				    });
							    
				    btnSearch.setBounds(36, 244, 124, 45);
				    btnSearch.setText("Cerca");

				    
					//SEPARATORE
					Label label = new Label(frame1, SWT.SEPARATOR | SWT.VERTICAL);
					label.setBounds(194, 10, 2, 361);
			    
					
			    //SECONDA SEZIONE
			    Composite frame2 = new Composite(grpArticoli, SWT.NONE);
			    frame2.setBounds(197, 0, 196, 381);
			    
				    //LISTA ARTICOLI
				    list = new List(frame2, SWT.BORDER | SWT.V_SCROLL);
				    list.setBounds(10, 10, 176, 361);
				    
				    

		
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
					    lblSelettoreHomeList.setBounds(10, 44, 124, 14);
					    lblSelettoreHomeList.setText("Selettore home list");
					    
					    //LABEL INTESTAZIONE
					    Label lblSelettoreIntestazione = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreIntestazione.setBounds(10, 74, 136, 14);
					    lblSelettoreIntestazione.setText("Selettore intestazione");
					    
					    //LABEL SOMMARIO
					    Label lblSelettoreSommario = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreSommario.setBounds(10, 104, 124, 14);
					    lblSelettoreSommario.setText("Selettore sommario");
					    
					    //LABEL EYELET
					    Label lblocchiello = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblocchiello.setBounds(10, 134, 124, 14);
					    lblocchiello.setText("Selettore occhiello");
					    
					    //LABEL TESTO
					    Label lblSelettoreTesto = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreTesto.setBounds(10, 164, 100, 14);
					    lblSelettoreTesto.setText("Selettore testo");
					    
					    //LABEL AUTORE
					    Label lblSelettoreAutore = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreAutore.setBounds(10, 194, 111, 14);
					    lblSelettoreAutore.setText("Selettore autore");
					    
					    //LABEL DATA
					    Label lblSelettoreData = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblSelettoreData.setBounds(10, 224, 111, 14);
					    lblSelettoreData.setText("Selettore data");
					    
					    //LABEL CONTESTO
					    Label lblNomeContesto = new Label(grpInserimentoTemplate, SWT.NONE);
					    lblNomeContesto.setBounds(10, 254, 100, 14);
					    lblNomeContesto.setText("Nome contesto");
					    
					    //SELECT WEBSITE
					    Combo web_site = new Combo(grpInserimentoTemplate, SWT.NONE);
					    web_site.addSelectionListener(new SelectionAdapter() {
					    	@Override
					    	public void widgetSelected(SelectionEvent arg0) {
					    		
								try {
						    		Set<Website> hostlist;
									hostlist = website.getAllWebsite();
									for(Website host:hostlist){
								    	if(web_site.getText().equals(host.getName())){
								    		 source = host.getAddress();
								    		 System.out.println(source);
								    		break;
								    	};
								    }
								} catch (StorageException e) {
									Dialog dialog = new Dialog(Dialog.ERROR_INSERT);
									dialog.open();
									e.printStackTrace();
								}
					    		
					    	}
					    		
					    	
					    });
					    web_site.setBounds(188, 10, 186, 24);
					    try {
							Set<Website> hostlist = website.getAllWebsite();
							for(Website host:hostlist){
								mMap.put(host.getName(), host);
								web_site.add(host.getName());
						    }
						} catch (StorageException e1) {
							Dialog dialog = new Dialog(Dialog.ERROR_INSERT);
							dialog.open();
							e1.printStackTrace();
						}

					    
					    //TEXTBOX HOMELIST
					    home_list = new Text(grpInserimentoTemplate, SWT.BORDER);
					    home_list.setBounds(188, 40, 186, 22);
					    
					    //TEXTBOX INTESTAZIONE
					    header = new Text(grpInserimentoTemplate, SWT.BORDER);
					    header.setBounds(188, 70, 186, 22);
					    
					    //TEXTBOX SOMMARIO
					    summary = new Text(grpInserimentoTemplate, SWT.BORDER);
					    summary.setBounds(188, 100, 186, 22);
					    
					    //TEXTBOX OCCHIELLO
					    eyelet = new Text(grpInserimentoTemplate, SWT.BORDER);
					    eyelet.setBounds(188, 130, 186, 22);
					    
					    //TEXTBOX TESTO
					    text = new Text(grpInserimentoTemplate, SWT.BORDER);
					    text.setBounds(188, 160, 186, 22);
					    
					    //TEXTBOX AUTORE
					    author = new Text(grpInserimentoTemplate, SWT.BORDER);
					    author.setBounds(188, 190, 186, 22);
					    
					    //TEXTBOX DATA
					    date = new Text(grpInserimentoTemplate, SWT.BORDER);
					    date.setBounds(188, 220, 186, 22);
					    
					    //TEXTBOX CONTESTO
					    context = new Text(grpInserimentoTemplate, SWT.BORDER);
					    context.setBounds(188, 250, 186, 22);
					   
					    //BUTTON ADDTEMPLATE
					    Button addTemplate = new Button(grpInserimentoTemplate, SWT.NONE);
					    addTemplate.addMouseListener(new MouseAdapter() {
					    	@Override
					    	public void mouseDown(MouseEvent arg0) {
					    		ArticleListTemplate listTemplate = new ArticleListTemplate(context.getText(), home_list.getText());
					    		ArticleTemplate template = ArticleTemplate(context.getText(), header.getText(), eyelet.getText(), summary.getText(), text.getText(), author.getText(), date.getText());
					    		try {
									insertTemplate(template, listTemplate, source);
									Dialog dialog = new Dialog(Dialog.SUCCESSFUL_INSERT);
									dialog.open();
								} catch (MalformedURLException | StorageException | WebsiteNotFoundException
										| ContextAlreadyExistsException e) {
									Dialog dialog = new Dialog(Dialog.ERROR_INSERT);
									dialog.open();
									e.printStackTrace();
								} catch (DataOmittedException e) {
									Dialog dialog = new Dialog(Dialog.ERROR_UNKNOWN);
									dialog.open();
									e.printStackTrace();
								}

					    	}
					    });
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
				    indirizzo.setBounds(101, 10, 273, 22);
				    
				    //NOME
				    Label lblNome = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblNome.setBounds(10, 44, 59, 14);
				    lblNome.setText("Nome:");
				    nome = new Text(grpInserimentoWebsite, SWT.BORDER);
				    nome.setBounds(101, 40, 273, 22);
				    
				    //DESCRIZIONE
				    Label lblDescrizione = new Label(grpInserimentoWebsite, SWT.NONE);
				    lblDescrizione.setBounds(10, 74, 80, 14);
				    lblDescrizione.setText("Descrizione:");
				    descrizione = new Text(grpInserimentoWebsite, SWT.BORDER);
				    descrizione.setBounds(101, 70, 273, 78);
				    
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


	protected ArticleTemplate ArticleTemplate(String text2, String text3, String text4, String text5, String text6,
			String text7, String text8) {
		// TODO Auto-generated method stub
		return null;
	}
}
