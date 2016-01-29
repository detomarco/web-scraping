package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import it.univaq.tlp.webscraper.aggregatordata.repository.Storable;
import it.univaq.tlp.webscraper.aggregatordata.repository.StorageException;
import it.univaq.tlp.webscraper.aggregatordata.repository.database.MySQLDatabase;
import it.univaq.tlp.webscraper.aggregatordata.view.UserInterface;

public class GUI extends UserInterface{

	protected Shell shell;
	private Text url;
	private Button btnSearch;
	private Button btnReadArticles;
	private Label lblSeparator;
	private Button btnAddTemplate;
	private Button btnAddWebsite;

	private static UserInterface user;
	
	public GUI(Storable storage) {
		super(storage);
	}


	/**
	 * Launch the application.
	 * @param args
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
		shell.setSize(497, 185);
		shell.setText("WebScraping");
		shell.setLayout(null);
		
		//URL
		url = new Text(shell, SWT.BORDER);
		url.setBounds(10, 10, 381, 24);
		url.setText("Write url here");
		
		//BUTTON SEARCH
		btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				
				
				
				
			}
		});
		btnSearch.setBounds(396, 6, 95, 60);
		btnSearch.setText("Search");
		
		//BUTTON READ ARTICLES
		btnReadArticles = new Button(shell, SWT.NONE);
		btnReadArticles.setBounds(2, 87, 116, 28);
		btnReadArticles.setText("Read Articles");
		
		//SEPARATOR
		lblSeparator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblSeparator.setText("separator");
		lblSeparator.setBounds(8, 121, 482, 2);
		
		//BUTTON ADD TEMPLATE
		btnAddTemplate = new Button(shell, SWT.NONE);
		btnAddTemplate.setBounds(380, 129, 116, 28);
		btnAddTemplate.setText("Add Template");
		
		//BUTTON ADD WEBSITE
		btnAddWebsite = new Button(shell, SWT.NONE);
		btnAddWebsite.setBounds(280, 129, 105, 28);
		btnAddWebsite.setText("Add WebSite");
		
		//CREDIT
		Label lblWebscrapingDevelopedBy = new Label(shell, SWT.NONE);
		lblWebscrapingDevelopedBy.setBounds(8, 136, 229, 14);
		lblWebscrapingDevelopedBy.setText("WebScraping Developed By Jamal");
		
		//OPTION
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(296, 40, 95, 22);
		combo.add("Opzione 1");
		combo.add("Opzione 2");
		
		
	}
}
