package it.univaq.tlp.webscraper.view.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Dialog {

	protected Shell shell;
	
	
	public static final int ERROR_DB_CONNECTION=1;
	public static final int SUCCESS_INSERT=2;
	public static final int ERROR_INSERT_MALFORMED_URL_EXCEPTION=3;
	public static final int ERROR_INSERT_STORAGE_EXCEPTION=4;
	public static final int ERROR_INSERT_WEBSITE_NOT_FOUND_EXCEPTION=5;
	public static final int ERROR_INSERT_TEMPLATE_NOT_FOUND_EXCEPTION=6;
	public static final int ERROR_INSERT_RESPONSE_EXCEPTION=7;
	public static final int ERROR_INSERT_CONTEXT_ALREADY_EXISTS_EXCEPTION=8;
	public static final int ERROR_INSERT_DATA_OMITTED_EXCEPTION=9;
	public static final int ERROR_INSERT_WEBSITE_ALREADY_EXISTS_EXCEPTION=10;
	public static final int ERROR_SEARCH_MALFORMED_URL_EXCEPTION=11;
	public static final int ERROR_SEARCH_STORAGE_EXCEPTION=12;
	public static final int ERROR_SEARCH_WEBSITE_NOT_FOUND_EXCEPTION=13;
	public static final int ERROR_COMBO_STORAGE_EXCEPTION=14;
	public static final int ERROR_COMBO_WEBSITE_NOT_FOUND_EXCEPTION=15;




	private int mode = ERROR_DB_CONNECTION;
	
	public Dialog(){}
	
	public Dialog(int mode){
		this.mode = mode;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Dialog window = new Dialog();
			window.open();
			System.out.println("Main");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		System.out.println("Open");
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
		shell.setSize(405, 120);
		switch(mode){
		
		case ERROR_DB_CONNECTION:
			shell.setText("ERRORE");
			
			//BUTTON CLOSE
			Button close_database = new Button(shell, SWT.NONE);
			close_database.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_database.setBounds(155, 51, 94, 28);
			close_database.setText("Close");
			
			//LABEL MESSAGE
			Label message_database = new Label(shell, SWT.NONE);
			message_database.setBounds(37, 20, 330, 17);
			message_database.setText("Si è verificato un errore durante la connessione al database.");	
			
			Label lblStorageexception = new Label(shell, SWT.NONE);
			lblStorageexception.setBounds(150, 36, 104, 14);
			lblStorageexception.setText("StorageException");
			
			break;
			
		case SUCCESS_INSERT:
			shell.setText("SUCCESSO");
			
			//BUTTON CLOSE
			Button close_success = new Button(shell, SWT.NONE);
			close_success.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_success.setBounds(155, 51, 94, 28);
			close_success.setText("Close");
			
			//LABEL MESSAGE
			Label message_success = new Label(shell, SWT.NONE);
			message_success.setBounds(100, 25, 204, 14);
			message_success.setText("Inserimento avvenuto con successo.");
			break;
			
		case ERROR_INSERT_MALFORMED_URL_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
			//BUTTON CLOSE
			Button close_eimue = new Button(shell, SWT.NONE);
			close_eimue.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eimue.setBounds(155, 48, 94, 28);
			close_eimue.setText("Close");
			//LABEL MESSAGE
			Label message_eimue = new Label(shell, SWT.NONE);
			message_eimue.setBounds(133, 25, 139, 17);
			message_eimue.setText("MalformedURLException");
			break;
			
		case ERROR_INSERT_STORAGE_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
			//BUTTON CLOSE
			Button close_eise = new Button(shell, SWT.NONE);
			close_eise.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eise.setBounds(155, 48, 94, 28);
			close_eise.setText("Close");
			//LABEL MESSAGE
			Label message_eise = new Label(shell, SWT.NONE);
			message_eise.setBounds(148, 25, 108, 17);
			message_eise.setText("StorageException");
			break;
			
		case ERROR_INSERT_WEBSITE_NOT_FOUND_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
			//BUTTON CLOSE
			Button close_eiwnfe = new Button(shell, SWT.NONE);
			close_eiwnfe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eiwnfe.setBounds(155, 48, 94, 28);
			close_eiwnfe.setText("Close");
			//LABEL MESSAGE
			Label message_eiwnfe = new Label(shell, SWT.NONE);
			message_eiwnfe.setBounds(123, 25, 158, 17);
			message_eiwnfe.setText("WebsiteNotFoundException");
			break;
			
		case ERROR_INSERT_TEMPLATE_NOT_FOUND_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
		
			//BUTTON CLOSE
			Button close_eitnfe = new Button(shell, SWT.NONE);
			close_eitnfe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eitnfe.setBounds(155, 48, 94, 28);
			close_eitnfe.setText("Close");
			//LABEL MESSAGE
			Label message_eitnfe = new Label(shell, SWT.NONE);
			message_eitnfe.setBounds(120, 25, 165, 17);
			message_eitnfe.setText("TemplateNotFoundException");
			break;
			
		case ERROR_INSERT_RESPONSE_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
			//BUTTON CLOSE
			Button close_eire = new Button(shell, SWT.NONE);
			close_eire.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eire.setBounds(155, 48, 94, 28);
			close_eire.setText("Close");
			//LABEL MESSAGE
			Label message_eire = new Label(shell, SWT.NONE);
			message_eire.setBounds(143, 25, 118, 17);
			message_eire.setText("ResponseException");
			break;
			
		case ERROR_INSERT_CONTEXT_ALREADY_EXISTS_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
			//BUTTON CLOSE
			Button close_eicaee = new Button(shell, SWT.NONE);
			close_eicaee.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eicaee.setBounds(155, 48, 94, 28);
			close_eicaee.setText("Close");
			//LABEL MESSAGE
			Label message_eicaee = new Label(shell, SWT.NONE);
			message_eicaee.setBounds(115, 25, 175, 17);
			message_eicaee.setText("ContextAlreadyExistsException");
			break;
			
		case ERROR_INSERT_DATA_OMITTED_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
			//BUTTON CLOSE
			Button close_eidoe = new Button(shell, SWT.NONE);
			close_eidoe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eidoe.setBounds(155, 48, 94, 28);
			close_eidoe.setText("Close");
			//LABEL MESSAGE
			Label message_eidoe = new Label(shell, SWT.NONE);
			message_eidoe.setBounds(136, 25, 132, 17);
			message_eidoe.setText("DataOmittedException");
			break;
			
		case ERROR_INSERT_WEBSITE_ALREADY_EXISTS_EXCEPTION:
			shell.setText("INSERIMENTO FALLITO");
			
			//BUTTON CLOSE
			Button close_eiwaee = new Button(shell, SWT.NONE);
			close_eiwaee.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eiwaee.setBounds(155, 48, 94, 28);
			close_eiwaee.setText("Close");
			//LABEL MESSAGE
			Label message_eiwaee = new Label(shell, SWT.NONE);
			message_eiwaee.setBounds(115, 25, 175, 17);
			message_eiwaee.setText("AlreadyExistsException");
			break;
		
		case ERROR_SEARCH_MALFORMED_URL_EXCEPTION:
			shell.setText("RICERCA FALLITA");
			
			//BUTTON CLOSE
			Button close_esmue = new Button(shell, SWT.NONE);
			close_esmue.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_esmue.setBounds(155, 48, 94, 28);
			close_esmue.setText("Close");
			//LABEL MESSAGE
			Label message_esmue = new Label(shell, SWT.NONE);
			message_esmue.setBounds(133, 25, 139, 17);
			message_esmue.setText("MalformedURLException");
			break;
		
		case ERROR_SEARCH_STORAGE_EXCEPTION:
			shell.setText("RICERCA FALLITA");
			
			//BUTTON CLOSE
			Button close_esse = new Button(shell, SWT.NONE);
			close_esse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_esse.setBounds(155, 48, 94, 28);
			close_esse.setText("Close");
			//LABEL MESSAGE
			Label message_esse = new Label(shell, SWT.NONE);
			message_esse.setBounds(148, 25, 108, 17);
			message_esse.setText("StorageException");
			break;
		
		case ERROR_SEARCH_WEBSITE_NOT_FOUND_EXCEPTION:
			shell.setText("RICERCA FALLITA");
			
			//BUTTON CLOSE
			Button close_eswnfe = new Button(shell, SWT.NONE);
			close_eswnfe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_eswnfe.setBounds(155, 48, 94, 28);
			close_eswnfe.setText("Close");
			//LABEL MESSAGE
			Label message_eswnfe = new Label(shell, SWT.NONE);
			message_eswnfe.setBounds(123, 25, 158, 17);
			message_eswnfe.setText("WebsiteNotFoundException");
			break;
			
		case ERROR_COMBO_STORAGE_EXCEPTION:
			shell.setText("RICERCA FALLITA");
			
			//BUTTON CLOSE
			Button close_ecse = new Button(shell, SWT.NONE);
			close_ecse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_ecse.setBounds(155, 48, 94, 28);
			close_ecse.setText("Close");
			//LABEL MESSAGE
			Label message_ecse = new Label(shell, SWT.NONE);
			message_ecse.setBounds(148, 25, 108, 17);
			message_ecse.setText("StorageException");
			break;
		
		case ERROR_COMBO_WEBSITE_NOT_FOUND_EXCEPTION:
			shell.setText("RICERCA FALLITA");
			
			//BUTTON CLOSE
			Button close_ecwnfe = new Button(shell, SWT.NONE);
			close_ecwnfe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_ecwnfe.setBounds(155, 48, 94, 28);
			close_ecwnfe.setText("Close");
			//LABEL MESSAGE
			Label message_ecwnfe = new Label(shell, SWT.NONE);
			message_ecwnfe.setBounds(123, 25, 158, 17);
			message_ecwnfe.setText("WebsiteNotFoundException");
			break;
			
			
		}
		
		
		
		

	}

}
