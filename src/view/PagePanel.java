package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.InputListener;
import data.TreeNode;

import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;

public class PagePanel extends BasePanel implements InputListener, TreeSelectionListener {

	private static final long serialVersionUID = 1L;

	private JTree tree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private JButton btnAdd, btnRemove;
	private TreeNode current;

	public PagePanel(InputListener listener) {
		super(listener);

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		btnAdd = new JButton("+");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);

		btnRemove = new JButton("-");
		btnRemove.addActionListener(this);
		panel.add(btnRemove);

		root = new DefaultMutableTreeNode("root");

		tree = new JTree(root);
		model = (DefaultTreeModel) tree.getModel();
		tree.addTreeSelectionListener(this);
		add(tree, BorderLayout.CENTER);
	}

	public void setCurrent(int index) {
		current = (TreeNode) root.getChildAt(index);
	}

	public TreeNode getCurrent() {
		return current;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TreePath[] paths = tree.getSelectionPaths();
		
		if (arg0.getSource() == btnAdd) {
			if (paths != null) {
				for (TreePath path : paths) {
					MutableTreeNode n = (MutableTreeNode) path.getLastPathComponent();
					model.insertNodeInto(new TreeNode(), n, n.getChildCount());
				}
			} else {
				root.add(new TreeNode());
			}
		} else if (arg0.getSource() == btnRemove) {
			// TODO: add check if node is empty
			if (paths != null) {
				for (TreePath path : paths) {
					MutableTreeNode n = (MutableTreeNode) path.getLastPathComponent();
					if (n.getParent() != null) {
						model.removeNodeFromParent(n);
					}
				}
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

	}

	@Override
	public void changeEvent() {
	}

	@Override
	public boolean isValidNumber() {
		return false;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

	}

}
