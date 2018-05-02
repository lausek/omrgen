package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.InputListener;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class PagePanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	private JTree tree;
	private DefaultMutableTreeNode treeModel;
	
	public PagePanel(InputListener listener) {
		super(listener);
		
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("+");
		panel.add(btnAdd);

		JButton btnRemove = new JButton("-");
		panel.add(btnRemove);
		
		tree = new JTree();
		treeModel = new DefaultMutableTreeNode("root");
		tree.setModel(new DefaultTreeModel(treeModel));
		add(tree, BorderLayout.CENTER);
	}
	
}
