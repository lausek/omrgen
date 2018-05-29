package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.handler.BaseHandler;
import data.CodeInfoSet;
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
		lsPages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsPages.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					parent.revalidatePreview();
				}
			}
		});
		lsPages.setCellRenderer(this);
		add(lsPages, BorderLayout.CENTER);
	}

	public PageNode addPage(int number) {
		PageNode n = new PageNode("Page " + number);
		model.addElement(n);
		return n;
	}

	public void removePage(int index) {
		model.removeElementAt(index);
	}

	public void removePage(int[] indices) {
		// process in reverse
		for (int i = indices.length - 1; 0 <= i; i--) {
			removePage(indices[i]);
		}
	}

	public void setInfoSet(CodeInfoSet next) {
		for (boolean[] actives : next.actives) {
			addPage(model.getSize() + 1).actives = actives;
		}
	}

	public void adjustAll(int size) {
		Enumeration<PageNode> nodes = model.elements();
		while (nodes.hasMoreElements()) {
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
