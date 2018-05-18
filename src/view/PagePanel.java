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
import javax.swing.tree.DefaultMutableTreeNode;

public class PagePanel extends BasePanel implements InputListener, TreeSelectionListener {

	private static final long serialVersionUID = 1L;

	private JTree tree;
	private DefaultMutableTreeNode treeModel;
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

		tree = new JTree();
		tree.addTreeSelectionListener(this);
		treeModel = new DefaultMutableTreeNode("root");
		tree.setModel(new DefaultTreeModel(treeModel));
		add(tree, BorderLayout.CENTER);
	}

	public void setCurrent(int index) {
		current = (TreeNode) treeModel.getChildAt(index);
	}

	public TreeNode getCurrent() {
		return current;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int[] indices = tree.getSelectionRows();
		
		if (arg0.getSource() == btnAdd) {
			if (indices != null) {
				for (int i : indices) {
//					treeModel.getChildAt(i).add(new TreeNode());
				}
			} else {
				treeModel.add(new TreeNode());
			}
		} else if (arg0.getSource() == btnRemove) {

		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
