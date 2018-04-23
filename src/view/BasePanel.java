package view;

import javax.swing.JPanel;

import control.InputListener;

public class BasePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected InputListener listener;
	
	public BasePanel(InputListener listener) {
		this.listener = listener;
	}
	
	public boolean open() {
		return true;
	}

	public boolean close() {
		return true;
	}

}
