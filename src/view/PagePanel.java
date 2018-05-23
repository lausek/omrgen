package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.handler.BaseHandler;
import data.PageNode;

import javax.swing.JList;

public class PagePanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	private JList<PageNode> lsPages;
	
	public PagePanel(BaseHandler listener) {
		super(listener);
		
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("+");
		panel.add(btnAdd);

		JButton btnRemove = new JButton("-");
		panel.add(btnRemove);
		
		lsPages = new JList<>();
		add(lsPages, BorderLayout.CENTER);
	}
	
}
