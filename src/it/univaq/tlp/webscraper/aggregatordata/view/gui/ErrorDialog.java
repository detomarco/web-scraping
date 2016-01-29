package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;

public class ErrorDialog {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ErrorDialog window = new ErrorDialog();
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
		shell.setSize(405, 110);
		shell.setText("Database Error");
		
		
		//BUTTON CLOSE
		Button btnClose = new Button(shell, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				shell.close();
			}
		});
		btnClose.setBounds(161, 51, 94, 28);
		btnClose.setText("Close");
		
		Label lblSiVerificato = new Label(shell, SWT.NONE);
		lblSiVerificato.setBounds(143, 10, 140, 14);
		lblSiVerificato.setText("Si è verificato un errore");
		
		Label lblDuranteLaConnessione = new Label(shell, SWT.NONE);
		lblDuranteLaConnessione.setBounds(108, 30, 210, 14);
		lblDuranteLaConnessione.setText("durante la connessione al database.");
		

	}

}
