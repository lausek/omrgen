package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import control.Control;
import control.handler.BaseHandler;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class View extends JFrame implements ActionListener {

	public enum FileStatus {
		INITIAL, PENDING, SAVED
	};

	private static final long serialVersionUID = 1L;
	private static final int ICON_SIZE = 16;

	private static ImageIcon iconInfo, iconWarn, iconGood;
	private static JLabel lblFileStatus;

	private Control control;
	private BaseHandler currentHandler;
	private JMenuItem mntmNew, mntmOpen, mntmSaveToOpen, mntmSave, mntmExit;
	private JSeparator separator_1;
	private JMenuItem mntmExport;
	private JPanel orderPanel;

	static {
		iconInfo = getAndRescale("OptionPane.questionIcon", ICON_SIZE, ICON_SIZE);
		iconWarn = getAndRescale("OptionPane.warningIcon", ICON_SIZE, ICON_SIZE);
		iconGood = getAndRescale("OptionPane.informationIcon", ICON_SIZE, ICON_SIZE);
	}

	public View(Control control) {
		this.control = control;
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(50, 50, 500, 380);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("assets/img/icon.png"));

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

		mntmSaveToOpen = new JMenuItem("Save");
		mntmSaveToOpen.addActionListener(this);
		mnFile.add(mntmSaveToOpen);

		mntmSave = new JMenuItem("Save...");
		mntmSave.addActionListener(this);
		mnFile.add(mntmSave);

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

		orderPanel = new JPanel();
		FlowLayout fl_orderPanel = (FlowLayout) orderPanel.getLayout();
		fl_orderPanel.setAlignment(FlowLayout.RIGHT);
		menuBar.add(orderPanel);

		lblFileStatus = new JLabel();
		orderPanel.add(lblFileStatus);
	}

	private static ImageIcon getAndRescale(String name, int width, int height) {
		ImageIcon icon = (ImageIcon) UIManager.getIcon(name);
		if (icon == null) {
			return icon;
		}
		java.awt.Image img = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	public static void setFileStatus(FileStatus f) {
		switch (f) {
		case INITIAL:
			lblFileStatus.setText("initial");
			lblFileStatus.setIcon(iconInfo);
			break;

		case PENDING:
			lblFileStatus.setText("pending");
			lblFileStatus.setIcon(iconWarn);
			break;

		case SAVED:
			lblFileStatus.setText("saved");
			lblFileStatus.setIcon(iconGood);
			break;

		}
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

		if (arg0.getSource() == mntmOpen) {
			control.loadState(null);
		} else if (arg0.getSource() == mntmSave || arg0.getSource() == mntmSaveToOpen) {
			control.saveState(null);
		} else if (arg0.getSource() == mntmExit) {
			control.exitApplication();
		} else {
			currentHandler.actionPerformed(arg0);
		}
	}

}
