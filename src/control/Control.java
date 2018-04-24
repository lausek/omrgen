package control;

import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import control.handler.EditHandler;
import view.View;

public class Control {

	private View view;
	private EditHandler editHandler;

	public Control() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new java.awt.Insets(10,10,10,10));
		} catch (Exception e) {
			System.out.println(e);
		}

		view = new View(this);
		editHandler = new EditHandler();

		view.switchTo(editHandler);

		view.setVisible(true);
	}

	public boolean exitApplication() {
		if (view.close()) {
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		new Control();
	}

}
