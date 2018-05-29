package control;

import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import control.handler.EditHandler;
import view.View;

public class Control {

	private View view;
	private EditHandler editHandler;

	public Control() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new java.awt.Insets(10, 10, 10, 10));
		} catch (Exception e) {
			System.out.println(e);
		}

		view = new View(this);
		editHandler = new EditHandler(this);

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

	public boolean loadState(File fp) {
		if (!editHandler.close()) {
			return false;
		}
		
		if (fp == null) {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
				fp = chooser.getSelectedFile();
			}
		}
		
		return editHandler.loadState(fp);
	}

	public boolean saveState(File fp) {
		if (fp == null) {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
				fp = chooser.getSelectedFile();
			}
		}
		return editHandler.saveState(fp);
	}

	public static void main(String[] args) {
		new Control();
	}

}
