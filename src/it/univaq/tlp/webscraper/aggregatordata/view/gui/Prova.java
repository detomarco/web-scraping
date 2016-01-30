package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Prova {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Prova window = new Prova();
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
		shell.setSize(405, 120);
		shell.setText("Database Error");
		
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
	}

}
