package view;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import control.InputListener;
import data.CodeInfoSet;
import data.CodeStripe;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class EditPanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	public JTextField tf_width, tf_height;
	public JSpinner sp_stripes;
	
	private JPanel general;
	private JScrollPane scrollPane;
	private JLabel preview;
	private JTextField tf_pleft, tf_pright, tf_ptop, tf_pbottom;
	private JTextField tf_mleft, tf_mright, tf_mtop, tf_mbottom;
	
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
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		splitPane.setLeftComponent(panel);
		
		general = new JPanel();
		general.setBorder(new TitledBorder(null, "General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(general);
		GridBagLayout gbl_general = new GridBagLayout();
		gbl_general.columnWidths = new int[]{46, 0, 0, 0, 0};
		gbl_general.rowHeights = new int[]{0, 20, 21, 0, 0, 0};
		gbl_general.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_general.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		general.setLayout(gbl_general);
		
		JLabel lblStripes = new JLabel("Stripes");
		GridBagConstraints gbc_lblStripes = new GridBagConstraints();
		gbc_lblStripes.insets = new Insets(0, 0, 5, 5);
		gbc_lblStripes.gridx = 0;
		gbc_lblStripes.gridy = 0;
		general.add(lblStripes, gbc_lblStripes);
		
		sp_stripes = new JSpinner();
		sp_stripes.addChangeListener(listener);
		GridBagConstraints gbc_sp_stripes = new GridBagConstraints();
		gbc_sp_stripes.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_stripes.insets = new Insets(0, 0, 5, 5);
		gbc_sp_stripes.gridx = 1;
		gbc_sp_stripes.gridy = 0;
		general.add(sp_stripes, gbc_sp_stripes);
		
		tf_width = new JTextField();
		addTextFieldAt(1, 1, "Width", tf_width);
		
		tf_height = new JTextField();
		addTextFieldAt(3, 1, "Height", tf_height);
		
		JLabel lblPaddingHeader = new JLabel("Padding");
		GridBagConstraints gbc_lblPaddingHeader = new GridBagConstraints();
		gbc_lblPaddingHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaddingHeader.gridx = 0;
		gbc_lblPaddingHeader.gridy = 3;
		general.add(lblPaddingHeader, gbc_lblPaddingHeader);
		
		tf_pleft = new JTextField();
		addTextFieldAt(1, 4, "Left", tf_pleft);
		
		tf_pright = new JTextField();
		addTextFieldAt(3, 4, "Right", tf_pright);
		
		tf_ptop = new JTextField();
		addTextFieldAt(1, 5, "Top", tf_ptop);
		
		tf_pbottom = new JTextField();
		addTextFieldAt(3, 5, "Bottom", tf_pbottom);
		
		JLabel lblMarginHeader = new JLabel("Margin");
		GridBagConstraints gbc_lblMarginHeader = new GridBagConstraints();
		gbc_lblMarginHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarginHeader.gridx = 0;
		gbc_lblMarginHeader.gridy = 7;
		general.add(lblMarginHeader, gbc_lblMarginHeader);
		
		tf_mleft = new JTextField();
		addTextFieldAt(1, 8, "Left", tf_mleft);
		
		tf_mright = new JTextField();
		addTextFieldAt(3, 8, "Right", tf_mright);
		
		tf_mtop = new JTextField();
		addTextFieldAt(1, 9, "Top", tf_mtop);
		
		tf_mbottom = new JTextField();
		addTextFieldAt(3, 9, "Bottom", tf_mbottom);
		
	}
	
	private void addTextFieldAt(int x, int y, String label, JTextField tf) {
		JLabel lbl = new JLabel(label);
		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.anchor = GridBagConstraints.EAST;
		gbc_lbl.insets = new Insets(0, 0, 0, 5);
		gbc_lbl.gridx = x-1;
		gbc_lbl.gridy = y;
		general.add(lbl, gbc_lbl);
		
		GridBagConstraints gbc_tf_cons = new GridBagConstraints();
		gbc_tf_cons.insets = new Insets(0, 0, 0, 5);
		gbc_tf_cons.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cons.gridx = x;
		gbc_tf_cons.gridy = y;
		general.add(tf, gbc_tf_cons);
		
		tf.addActionListener(listener);
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		tf.setColumns(6);
		tf.setText("0");
	}
	
	public CodeInfoSet getInfoSet() {
		CodeInfoSet c = new CodeInfoSet();
		
		int amount = (int) sp_stripes.getValue();
		int width = Integer.parseInt(tf_width.getText());
		int height = Integer.parseInt(tf_height.getText());
		
		try {
			c.marginLeft = Integer.parseInt(tf_mleft.getText());
		} catch(NumberFormatException e) {
		}
		
		try {
			c.marginRight = Integer.parseInt(tf_mright.getText());
		} catch(NumberFormatException e) {
		}
		
		try {
			c.marginTop = Integer.parseInt(tf_mtop.getText());
		} catch(NumberFormatException e) {
		}
		
		try {
			c.marginBottom = Integer.parseInt(tf_mbottom.getText());
		} catch(NumberFormatException e) {
		}
		
		c.stripes = new CodeStripe[amount];
		for(int i = 0; i < amount; i++) {
			c.stripes[i] = new CodeStripe(width, height);
			
			try {
				c.stripes[i].paddingLeft = Integer.parseInt(tf_pleft.getText());
			} catch(NumberFormatException e) {
			}
			
			try {
				c.stripes[i].paddingRight 	= Integer.parseInt(tf_pright.getText());
			} catch(NumberFormatException e) {
			}
			
			try {
				c.stripes[i].paddingTop 	= Integer.parseInt(tf_ptop.getText());
			} catch(NumberFormatException e) {
			}
			
			try {
				c.stripes[i].paddingBottom 	= Integer.parseInt(tf_pbottom.getText());
			} catch(NumberFormatException e) {
			}
			
		}
		
		return c;
	}
	
	public void setPreview(Image img) {
		preview.setIcon(new ImageIcon(img));
		preview.revalidate();
		scrollPane.revalidate();
		scrollPane.repaint();
	}

}
