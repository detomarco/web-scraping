package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import swing2swt.layout.BorderLayout;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;

public class gui {

	protected Shell shell;
	private Text text;
	private Button btnAddWebsite;
	private Text url;
	private Button btnSearch;
	private Button btnReadArticles;
	private Label lblSeparator;
	private Button btnAddTemplate;
	private Button btnAddWebsite_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			gui window = new gui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		btnAddWebsite_1 = new Button(shell, SWT.NONE);
		btnAddWebsite_1.setBounds(280, 129, 105, 28);
		btnAddWebsite_1.setText("Add WebSite");
		
		
	}
}
