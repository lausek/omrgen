package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import control.Control;
import control.handler.BaseHandler;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Control control;
	private BaseHandler currentHandler;
	private JMenuItem mntmNew, mntmOpen, mntmExit;
	private JSeparator separator_1;
	private JMenuItem mntmExport;

	public View(Control control) {
		this.control = control;
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(50, 50, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(this);
		mnFile.add(mntmNew);

		mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(this);
		mnFile.add(mntmOpen);

		separator_1 = new JSeparator();
		mnFile.add(separator_1);

		mntmExport = new JMenuItem("Export...");
		mntmExport.addActionListener(this);
		mnFile.add(mntmExport);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);
	}

	public boolean close() {
		return currentHandler.close();
	}

	private void setHandlerPanel(BaseHandler handler) {
		currentHandler = handler;
		setContentPane(handler.getPanel());
		revalidate();
		repaint();
	}

	public void switchTo(BaseHandler next) {

		if (next == null) {
			return;
		}

		if (currentHandler != null && currentHandler.getPanel() != null) {
			if (!currentHandler.close()) {
				return;
			}
		}

		if (next.open()) {
			setHandlerPanel(next);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!(arg0.getSource() instanceof JMenuItem)) {
			return;
		}

		if (arg0.getSource() == mntmExit) {
			control.exitApplication();
		} else {
			currentHandler.actionPerformed(arg0);
		}
	}

}
