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
	
	
	public static final int ERROR_DATABASE=1;
	public static final int ERROR_UNKNOWN=2;
	public static final int SUCCESSFUL_INSERT=3;
	public static final int ERROR_INSERT=4;


	private int mode = ERROR_DATABASE;
	
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
		
		case ERROR_DATABASE:
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
			message_database.setBounds(37, 25, 330, 20);
			message_database.setText("Si è verificato un errore durante la connessione al database.");	
			
			break;
			
		case ERROR_UNKNOWN:
			shell.setText("ERRORE");
			
			//BUTTON CLOSE
			Button close_unknown = new Button(shell, SWT.NONE);
			close_unknown.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_unknown.setBounds(155, 51, 94, 28);
			close_unknown.setText("Close");
			
			//LABEL MESSAGE
			Label message_unknown = new Label(shell, SWT.NONE);
			message_unknown.setBounds(125, 25, 154, 14);
			message_unknown.setText("Si è verificato sconosciuto.");
			break;
			
		case SUCCESSFUL_INSERT:
			shell.setText("SUCCESSO");
			
			//BUTTON CLOSE
			Button close_succ_insert = new Button(shell, SWT.NONE);
			close_succ_insert.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_succ_insert.setBounds(155, 51, 94, 28);
			close_succ_insert.setText("Close");
			
			//LABEL MESSAGE
			Label message_succ_insert = new Label(shell, SWT.NONE);
			message_succ_insert.setBounds(98, 24, 208, 14);
			message_succ_insert.setText("Inserimento avvenuto con successo.");
			break;
			
		case ERROR_INSERT:
			shell.setText("ERRORE");
			
			//BUTTON CLOSE
			Button close_error_insert = new Button(shell, SWT.NONE);
			close_error_insert.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent arg0) {
					shell.close();
				}
			});
			close_error_insert.setBounds(155, 51, 94, 28);
			close_error_insert.setText("Close");
			
			//LABEL MESSAGE
			Label message_error_insert = new Label(shell, SWT.NONE);
			message_error_insert.setBounds(148, 24, 109, 14);
			message_error_insert.setText("Inserimento fallito.");
			break;
			
		
		}
		
		
		
		

	}

}
