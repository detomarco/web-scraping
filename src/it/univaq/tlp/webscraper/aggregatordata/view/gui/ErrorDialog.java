package it.univaq.tlp.webscraper.aggregatordata.view.gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;

public class ErrorDialog extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ErrorDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(320, 109);
		shell.setText(getText());
		
		Button btnClose = new Button(shell, SWT.NONE);
		btnClose.setBounds(110, 60, 94, 28);
		btnClose.setText("Close");
		
		Label lblError = new Label(shell, SWT.NONE);
		lblError.setBounds(93, 20, 136, 14);
		lblError.setText("Si è verificato un errore");
		
		Label lblDuranteLaConnessione = new Label(shell, SWT.NONE);
		lblDuranteLaConnessione.setBounds(61, 40, 190, 14);
		lblDuranteLaConnessione.setText("durante la connessione al database");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				shell.close();
			}
		});
	}
}
