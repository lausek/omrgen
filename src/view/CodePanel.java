package view;

import control.handler.BaseHandler;
import data.PageNode;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

public class CodePanel extends BasePanel implements ActionListener {

	private static final long serialVersionUID = -9071790078803808293L;

	private JTextField tfError;
	private PageNode selectedPage;
	private JPanel mainPanel;

	public CodePanel(BaseHandler listener, EditPanel parent) {
		super(listener, parent);

		tfError = new JTextField("Please select just one page.");

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
	}

	@Override
	public boolean open() {
		int[] selected = parent.pagePanel.lsPages.getSelectedIndices();
		boolean allow = selected.length == 1;

		tfError.setVisible(!allow);

		if (allow) {
			selectedPage = parent.pagePanel.model.get(selected[0]);

			if (selectedPage.actives == null) {
				selectedPage.adjust(parent.getInfoSet().stripes.length);
			}

			mainPanel.removeAll();
			for (int i = 0; i < selectedPage.actives.length; i++) {
				JCheckBox next = new JCheckBox("Stripe " + (i + 1));
				next.setSelected(selectedPage.actives[i]);
				next.addActionListener(this);
				mainPanel.add(next);
			}
		} else {
			selectedPage = null;
		}

		return allow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (selectedPage != null) {
			Component[] children = mainPanel.getComponents();
			for (int i = 0; i < children.length; i++) {
				if(children[i] == e.getSource()) {
					selectedPage.actives[i] = ((JCheckBox) e.getSource()).isSelected();
					parent.revalidate();
				}
			}
		}
	}
}
