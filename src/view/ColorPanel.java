package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTextField;

import control.handler.BaseHandler;
import data.CodeInfoSet;

public class ColorPanel extends BasePanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnColorFore, btnColorBack, btnReset;
	private JTextField tf_foreground, tf_background;
	
	public ColorPanel(BaseHandler listener) {
		super(listener);
		
		GridBagLayout gbl_colorPane = new GridBagLayout();
		gbl_colorPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_colorPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_colorPane.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_colorPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_colorPane);

		tf_foreground = new JTextField();
		addTextFieldAt(1, 0, "Foreground", tf_foreground);

		tf_background = new JTextField();
		addTextFieldAt(1, 1, "Background", tf_background);

		btnColorFore = new JButton("...");
		btnColorFore.addActionListener(this);
		GridBagConstraints gbc_btnColorFore = new GridBagConstraints();
		gbc_btnColorFore.insets = new Insets(0, 0, 5, 0);
		gbc_btnColorFore.gridx = 2;
		gbc_btnColorFore.gridy = 0;
		add(btnColorFore, gbc_btnColorFore);

		btnColorBack = new JButton("...");
		btnColorBack.addActionListener(this);
		GridBagConstraints gbc_btnColorBack = new GridBagConstraints();
		gbc_btnColorBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnColorBack.gridx = 2;
		gbc_btnColorBack.gridy = 1;
		add(btnColorBack, gbc_btnColorBack);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 2;
		add(verticalStrut, gbc_verticalStrut);

		btnReset = new JButton("Reset colors");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 3;
		add(btnReset, gbc_btnReset);
		btnReset.addActionListener(this);
		resetColors();
		
	}
	
	public void setInfoSet(CodeInfoSet next) {
		tf_foreground.setText("" + next.foreground.getRGB());
		tf_background.setText("" + next.background.getRGB());
	}
	
	public void resetColors() {
		setColors(null, null);
	}

	public void setColors(java.awt.Color fg, java.awt.Color bg) {
		if (fg != null) {
			tf_foreground.setText("" + fg.getRGB());
		} else {
			tf_foreground.setText("" + CodeInfoSet.FOREGROUND.getRGB());
		}

		if (bg != null) {
			tf_background.setText("" + bg.getRGB());
		} else {
			tf_background.setText("" + CodeInfoSet.BACKGROUND.getRGB());
		}
	}
	
	Color getForegroundColor() {
		try {
			return new java.awt.Color(Integer.parseInt(tf_foreground.getText()));
		} catch (NumberFormatException e) {
			return CodeInfoSet.FOREGROUND;
		}
	}
	
	Color getBackgroundColor() {
		try {
			return new java.awt.Color(Integer.parseInt(tf_background.getText()));
		} catch (NumberFormatException e) {
			return CodeInfoSet.BACKGROUND;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!(arg0.getSource() instanceof JButton)) {
			return;
		}
		
		Color next;
		JButton source = (JButton) arg0.getSource();
		
		if (source == btnColorFore) {
			next = JColorChooser.showDialog(this, "Pick foreground color", getForegroundColor());
			setColors(next, getBackgroundColor());
		} else if (source == btnColorBack) {
			next = JColorChooser.showDialog(this, "Pick background color", getBackgroundColor());
			setColors(getForegroundColor(), next);
		} else if (source == btnReset) {
			if (confirmMessage(MessageType.INFO, "Reset colors?")) {
				resetColors();
			}
		}
	}
	
}
