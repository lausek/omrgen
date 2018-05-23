package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import control.handler.BaseHandler;
import data.PageNode;

import javax.swing.JList;

public class PagePanel extends BasePanel implements ListCellRenderer<PageNode> {

	private static final long serialVersionUID = 1L;

	public JList<PageNode> lsPages;
	public DefaultListModel<PageNode> model;
	public JButton btnAdd, btnRemove;

	public PagePanel(BaseHandler listener, EditPanel parent) {
		super(listener, parent);

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		btnAdd = new JButton("+");
		btnAdd.addActionListener(handler);
		panel.add(btnAdd);

		btnRemove = new JButton("-");
		btnRemove.addActionListener(handler);
		panel.add(btnRemove);

		model = new DefaultListModel<>();

		lsPages = new JList<>();
		lsPages.setModel(model);
		lsPages.setCellRenderer(this);
		add(lsPages, BorderLayout.CENTER);
	}

	public void adjustAll(int size) {
		Enumeration<PageNode> nodes = model.elements();
		while(nodes.hasMoreElements()) {
			PageNode node = nodes.nextElement();
			node.adjust(size);
		}
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends PageNode> list, PageNode value, int index,
			boolean isSelected, boolean cellHasFocus) {
		value.setBackground(isSelected ? Color.ORANGE : Color.WHITE);
		return value;
	}

}
