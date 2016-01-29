package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import java.net.MalformedURLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import it.univaq.tlp.webscraper.aggregatordata.TemplateNotFoundException;
import it.univaq.tlp.webscraper.aggregatordata.WebsiteNotFoundException;
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
	    
	    CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
	    tabFolder.setBounds(0, 0, 600, 258);
	    tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
	    
	    CTabItem tabCrawler = new CTabItem(tabFolder, SWT.NONE);
	    tabCrawler.setText("Crawler");
	    
	    //CRWALER
	    Group grpCrawler = new Group(tabFolder, SWT.NONE);
	    grpCrawler.setText("Crawler");
	    tabCrawler.setControl(grpCrawler);
		
	    //URL
		Text url = new Text(grpCrawler, SWT.BORDER);
		url.setBounds(71, 22, 437, 24);
		url.setText("Write url here");
		
		
		
		Label lblAggiuntiArticoli = new Label(grpCrawler, SWT.NONE);
		lblAggiuntiArticoli.setBounds(237, 161, 106, 14);
		lblAggiuntiArticoli.setVisible(false);
	    
		
		//BUTTON INSERISCI ARTICOLO
				Button addArticle = new Button(grpCrawler, SWT.NONE);
				addArticle.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
						
						try {
							scrap(url.getText(), true);
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
		
		
		//ARTICOLI
	    CTabItem tabArticles = new CTabItem(tabFolder, SWT.NONE);
	    tabArticles.setText("Articoli");
	    
	    Group grpArticoli = new Group(tabFolder, SWT.NONE);
	    grpArticoli.setText("Articoli");
	    tabArticles.setControl(grpArticoli);
	    grpArticoli.setLayout(null);
	    
	    
	    //PRIMA SEZIONE
	    Composite frame1 = new Composite(grpArticoli, SWT.NONE);
	    frame1.setBounds(1, 0, 196, 203);
	    
	    Combo sorgente = new Combo(frame1, SWT.NONE);
	    sorgente.setBounds(81, 28, 94, 22);
	    
	    Button btnSearch = new Button(frame1, SWT.NONE);
	    btnSearch.setBounds(36, 122, 124, 45);
	    btnSearch.setText("Cerca");
	    
	    Label lblSorgente = new Label(frame1, SWT.NONE);
	    lblSorgente.setBounds(16, 32, 59, 14);
	    lblSorgente.setText("Sorgente");
	    
	    Label lblContesto = new Label(frame1, SWT.NONE);
	    lblContesto.setBounds(16, 74, 59, 14);
	    lblContesto.setText("Contesto");
	    
	    Combo contesto = new Combo(frame1, SWT.NONE);
	    contesto.setBounds(81, 70, 94, 22);
	    
	    
	    //SECONDA SEZIONE
	    Composite frame2 = new Composite(grpArticoli, SWT.NONE);
	    frame2.setBounds(197, 0, 196, 203);
	    
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
	    
	    Label label = new Label(frame2, SWT.SEPARATOR | SWT.VERTICAL);
	    label.setBounds(0, 10, 2, 183);


	    //TERZA SEZIONE
	    Composite frame3 = new Composite(grpArticoli, SWT.NONE);
	    frame3.setBounds(392, 0, 196, 203);
	    
	    Group group = new Group(frame3, SWT.NONE);
	    group.setText("Info Articolo");
	    group.setBounds(0, 0, 196, 196);
	    
	    

	}
}
