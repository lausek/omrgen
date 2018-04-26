package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.InputListener;

public class BasePanel extends JPanel {
	
	public enum MessageType {
		INFO,
		SUCCESS,
		WARNING,
		ERROR,
	}
	
	protected InputListener listener;
	
	private static final long serialVersionUID = 1L;
	
	public BasePanel(InputListener listener) {
		this.listener = listener;
	}
	
	public boolean open() {
		return true;
	}

	public boolean close() {
		return true;
	}
	
	public boolean confirmMessage(MessageType typ, String text) {
		switch(typ) {
		case INFO:
			return JOptionPane.showConfirmDialog(this, text, "Info", JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION;
			
		case SUCCESS:
			return JOptionPane.showConfirmDialog(this, text, "Info", JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION;
			
		case WARNING:
			return JOptionPane.showConfirmDialog(this, text, "Info", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
			
		case ERROR:
			return JOptionPane.showConfirmDialog(this, text, "Info", JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION;
			
		}
		return false;
	}
	
	public void displayMessage(MessageType typ, String text) {
		switch(typ) {
		case INFO:
			JOptionPane.showMessageDialog(this, text, "Info", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case SUCCESS:
			JOptionPane.showMessageDialog(this, text, "Success", JOptionPane.PLAIN_MESSAGE);
			break;
			
		case WARNING:
			JOptionPane.showMessageDialog(this, text, "Warning", JOptionPane.WARNING_MESSAGE);
			break;
			
		case ERROR:
			JOptionPane.showMessageDialog(this, text, "Error", JOptionPane.ERROR_MESSAGE);
			break;
			
		}
	}

}
