package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.InputListener;

public class PagePanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	public PagePanel(InputListener listener) {
		super(listener);
		
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("+");
		panel.add(btnAdd);

		JButton btnRemove = new JButton("-");
		panel.add(btnRemove);
	}
	
}
