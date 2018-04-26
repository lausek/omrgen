package view;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.InputListener;
import data.CodeInfoSet;
import data.CodeStripe;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Component;
import javax.swing.Box;

public class EditPanel extends BasePanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public JTextField tf_width, tf_height;
	public JSpinner sp_stripes;

	private JPanel layoutPane;
	private JScrollPane scrollPane;
	private JButton btnColorFore, btnColorBack;
	private JLabel preview;
	// padding
	private JTextField tf_pleft, tf_pright, tf_ptop, tf_pbottom;
	// margin
	private JTextField tf_mleft, tf_mright, tf_mtop, tf_mbottom;
	// color
	private JTextField tf_foreground, tf_background;

	public EditPanel(InputListener listener) {
		super(listener);
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		add(splitPane);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setRightComponent(scrollPane);

		preview = new JLabel("");
		scrollPane.setViewportView(preview);

		layoutPane = new JPanel();
		GridBagLayout gbl_general = new GridBagLayout();
		gbl_general.columnWidths = new int[] { 46, 0, 0, 0, 0 };
		gbl_general.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_general.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_general.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		layoutPane.setLayout(gbl_general);

		JLabel lblStripes = new JLabel("Stripes");
		GridBagConstraints gbc_lblStripes = new GridBagConstraints();
		gbc_lblStripes.insets = new Insets(0, 0, 5, 5);
		gbc_lblStripes.gridx = 0;
		gbc_lblStripes.gridy = 0;
		layoutPane.add(lblStripes, gbc_lblStripes);

		sp_stripes = new JSpinner();
		sp_stripes.addChangeListener(listener);
		GridBagConstraints gbc_sp_stripes = new GridBagConstraints();
		gbc_sp_stripes.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_stripes.insets = new Insets(0, 0, 5, 5);
		gbc_sp_stripes.gridx = 1;
		gbc_sp_stripes.gridy = 0;
		layoutPane.add(sp_stripes, gbc_sp_stripes);

		tf_width = new JTextField();
		addTextFieldAt(1, 1, "Width", tf_width, layoutPane);

		tf_height = new JTextField();
		addTextFieldAt(3, 1, "Height", tf_height, layoutPane);

		JLabel lblPaddingHeader = new JLabel("Padding");
		GridBagConstraints gbc_lblPaddingHeader = new GridBagConstraints();
		gbc_lblPaddingHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaddingHeader.gridx = 0;
		gbc_lblPaddingHeader.gridy = 3;
		layoutPane.add(lblPaddingHeader, gbc_lblPaddingHeader);

		tf_pleft = new JTextField();
		addTextFieldAt(1, 4, "Left", tf_pleft, layoutPane);

		tf_pright = new JTextField();
		addTextFieldAt(3, 4, "Right", tf_pright, layoutPane);

		tf_ptop = new JTextField();
		addTextFieldAt(1, 5, "Top", tf_ptop, layoutPane);

		tf_pbottom = new JTextField();
		addTextFieldAt(3, 5, "Bottom", tf_pbottom, layoutPane);

		JLabel lblMarginHeader = new JLabel("Margin");
		GridBagConstraints gbc_lblMarginHeader = new GridBagConstraints();
		gbc_lblMarginHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarginHeader.gridx = 0;
		gbc_lblMarginHeader.gridy = 7;
		layoutPane.add(lblMarginHeader, gbc_lblMarginHeader);

		tf_mleft = new JTextField();
		addTextFieldAt(1, 8, "Left", tf_mleft, layoutPane);

		tf_mright = new JTextField();
		addTextFieldAt(3, 8, "Right", tf_mright, layoutPane);

		tf_mtop = new JTextField();
		addTextFieldAt(1, 9, "Top", tf_mtop, layoutPane);

		tf_mbottom = new JTextField();
		addTextFieldAt(3, 9, "Bottom", tf_mbottom, layoutPane);

		JTabbedPane tabbedPane = new JTabbedPane();
		splitPane.setLeftComponent(tabbedPane);
		tabbedPane.add("Layout", layoutPane);

		JPanel colorPane = new JPanel();
		tabbedPane.addTab("Color", null, colorPane, null);
		GridBagLayout gbl_colorPane = new GridBagLayout();
		gbl_colorPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_colorPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_colorPane.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_colorPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		colorPane.setLayout(gbl_colorPane);

		tf_foreground = new JTextField();
		addTextFieldAt(1, 0, "Foreground", tf_foreground, colorPane);

		tf_background = new JTextField();
		addTextFieldAt(1, 1, "Background", tf_background, colorPane);

		btnColorFore = new JButton("...");
		btnColorFore.addActionListener(this);
		GridBagConstraints gbc_btnColorFore = new GridBagConstraints();
		gbc_btnColorFore.insets = new Insets(0, 0, 5, 0);
		gbc_btnColorFore.gridx = 2;
		gbc_btnColorFore.gridy = 0;
		colorPane.add(btnColorFore, gbc_btnColorFore);

		btnColorBack = new JButton("...");
		btnColorBack.addActionListener(this);
		GridBagConstraints gbc_btnColorBack = new GridBagConstraints();
		gbc_btnColorBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnColorBack.gridx = 2;
		gbc_btnColorBack.gridy = 1;
		colorPane.add(btnColorBack, gbc_btnColorBack);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 2;
		colorPane.add(verticalStrut, gbc_verticalStrut);

		JButton btnReset = new JButton("Reset colors");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 3;
		colorPane.add(btnReset, gbc_btnReset);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (confirmMessage(MessageType.INFO, "Reset colors?")) {
					resetColors();
				}
			}
		});
		resetColors();

		JPanel pagesPane = new JPanel();
		tabbedPane.addTab("Pages", null, pagesPane, null);
		pagesPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		pagesPane.add(panel, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("+");
		panel.add(btnAdd);

		JButton btnRemove = new JButton("-");
		panel.add(btnRemove);
	}

	private void addTextFieldAt(int x, int y, String label, JTextField tf, JPanel pane) {
		JLabel lbl = new JLabel(label);
		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.anchor = GridBagConstraints.EAST;
		gbc_lbl.insets = new Insets(0, 0, 0, 5);
		gbc_lbl.gridx = x - 1;
		gbc_lbl.gridy = y;
		pane.add(lbl, gbc_lbl);

		GridBagConstraints gbc_tf_cons = new GridBagConstraints();
		gbc_tf_cons.insets = new Insets(0, 0, 0, 5);
		gbc_tf_cons.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cons.gridx = x;
		gbc_tf_cons.gridy = y;
		pane.add(tf, gbc_tf_cons);

		tf.addActionListener(listener);
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		tf.setColumns(6);
		tf.setText("0");
	}

	private int tryConvert(JTextField textField) {
		try {
			return Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public CodeInfoSet getInfoSet() {
		CodeInfoSet c = new CodeInfoSet();

		int amount = (int) sp_stripes.getValue();
		int width = tryConvert(tf_width);
		int height = tryConvert(tf_height);

		
		c.foreground = getForegroundColor();
		c.background = getBackgroundColor();

		c.marginLeft = tryConvert(tf_mleft);
		c.marginRight = tryConvert(tf_mright);
		c.marginTop = tryConvert(tf_mtop);
		c.marginBottom = tryConvert(tf_mbottom);

		c.stripes = new CodeStripe[amount];
		for (int i = 0; i < amount; i++) {
			c.stripes[i] = new CodeStripe(width, height);

			c.stripes[i].paddingLeft = tryConvert(tf_pleft);
			c.stripes[i].paddingRight = tryConvert(tf_pright);
			c.stripes[i].paddingTop = tryConvert(tf_ptop);
			c.stripes[i].paddingBottom = tryConvert(tf_pbottom);
		}

		return c;
	}

	public void setPreview(Image img) {
		preview.setIcon(new ImageIcon(img));
		preview.revalidate();
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	public void resetColors() {
		setColors(null, null);
	}

	public void setColors(java.awt.Color fg, java.awt.Color bg) {
		if (fg != null) {
			tf_foreground.setText("" + fg.getRGB());
		} else {
			tf_foreground.setText("" + CodeInfoSet.FOREGROUND.getRGB());
		}

		if (bg != null) {
			tf_background.setText("" + bg.getRGB());
		} else {
			tf_background.setText("" + CodeInfoSet.BACKGROUND.getRGB());
		}
	}
	
	private Color getForegroundColor() {
		try {
			return new java.awt.Color(Integer.parseInt(tf_foreground.getText()));
		} catch (NumberFormatException e) {
			return CodeInfoSet.FOREGROUND;
		}
	}
	
	private Color getBackgroundColor() {
		try {
			return new java.awt.Color(Integer.parseInt(tf_background.getText()));
		} catch (NumberFormatException e) {
			return CodeInfoSet.BACKGROUND;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!(arg0.getSource() instanceof JButton)) {
			return;
		}
		
		java.awt.Color next;
		JButton source = (JButton) arg0.getSource();
		
		if (source == btnColorFore) {
			next = JColorChooser.showDialog(this, "Pick foreground color", getForegroundColor());
			setColors(next, getBackgroundColor());
		} else if (source == btnColorBack) {
			next = JColorChooser.showDialog(this, "Pick background color", getBackgroundColor());
			setColors(getForegroundColor(), next);
		}
	}

}
