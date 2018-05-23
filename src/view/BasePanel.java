package view;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.handler.BaseHandler;

public class BasePanel extends JPanel {
	
	public enum MessageType {
		INFO,
		SUCCESS,
		WARNING,
		ERROR,
	}
	
	protected BaseHandler handler;
	
	private static final long serialVersionUID = 1L;
	
	public BasePanel(BaseHandler listener) {
		this.handler = listener;
		this.handler.setPanel(this);
	}
	
	public boolean open() {
		return true;
	}

	public boolean close() {
		return true;
	}
	
	protected static int tryConvert(JTextField textField) {
		try {
			return Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	protected void addTextFieldAt(int x, int y, String label, JTextField tf) {
		JLabel lbl = new JLabel(label);
		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.anchor = GridBagConstraints.EAST;
		gbc_lbl.insets = new Insets(0, 0, 0, 5);
		gbc_lbl.gridx = x - 1;
		gbc_lbl.gridy = y;
		this.add(lbl, gbc_lbl);

		GridBagConstraints gbc_tf_cons = new GridBagConstraints();
		gbc_tf_cons.insets = new Insets(0, 0, 0, 5);
		gbc_tf_cons.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cons.gridx = x;
		gbc_tf_cons.gridy = y;
		this.add(tf, gbc_tf_cons);

		tf.addActionListener(handler);
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		tf.setColumns(6);
		tf.setText("0");
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
