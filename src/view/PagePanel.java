package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import control.handler.BaseHandler;
import data.PageNode;

import javax.swing.JList;
import control.InputListener;

public class PagePanel extends BasePanel implements InputListener {

	private static final long serialVersionUID = 1L;
	
	private JList<PageNode> lsPages;
	private JButton btnAdd, btnRemove;
	
	public PagePanel(BaseHandler listener) {
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
		
		lsPages = new JList<>();
		add(lsPages, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
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

}
