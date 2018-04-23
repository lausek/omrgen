package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import control.Control;
import control.handler.BaseHandler;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private Control control;
	private BaseHandler currentHandler;

	public View(Control control) {
		this.control = control;
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(50, 50, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setHandlerPanel(BaseHandler handler) {
		currentHandler = handler;
		setContentPane(handler.getViewPanel());
		revalidate();
		repaint();
	}

	public void switchTo(BaseHandler next) {

		if (next == null) {
			return;
		}

		if (currentHandler != null && currentHandler.getViewPanel() != null) {
			if (!currentHandler.close()) {
				return;
			}
		}

		if (next.open()) {
			setHandlerPanel(next);
		}

	}

}
