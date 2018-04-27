package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import control.InputListener;
import data.LayoutInfoSet;

public class LayoutPanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	public JTextField tf_width, tf_height;
	public JSpinner sp_stripes;
	// padding
	private JTextField tf_pleft, tf_pright, tf_ptop, tf_pbottom;
	// margin
	private JTextField tf_mleft, tf_mright, tf_mtop, tf_mbottom;
	
	public LayoutPanel(InputListener listener) {
		super(listener);
		
		GridBagLayout gbl_general = new GridBagLayout();
		gbl_general.columnWidths = new int[] { 46, 0, 0, 0, 0 };
		gbl_general.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_general.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_general.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_general);

		JLabel lblStripes = new JLabel("Stripes");
		GridBagConstraints gbc_lblStripes = new GridBagConstraints();
		gbc_lblStripes.insets = new Insets(0, 0, 5, 5);
		gbc_lblStripes.gridx = 0;
		gbc_lblStripes.gridy = 0;
		add(lblStripes, gbc_lblStripes);

		sp_stripes = new JSpinner();
		sp_stripes.addChangeListener(listener);
		GridBagConstraints gbc_sp_stripes = new GridBagConstraints();
		gbc_sp_stripes.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_stripes.insets = new Insets(0, 0, 5, 5);
		gbc_sp_stripes.gridx = 1;
		gbc_sp_stripes.gridy = 0;
		add(sp_stripes, gbc_sp_stripes);

		tf_width = new JTextField();
		addTextFieldAt(1, 1, "Width", tf_width);

		tf_height = new JTextField();
		addTextFieldAt(3, 1, "Height", tf_height);

		JLabel lblPaddingHeader = new JLabel("Padding");
		GridBagConstraints gbc_lblPaddingHeader = new GridBagConstraints();
		gbc_lblPaddingHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaddingHeader.gridx = 0;
		gbc_lblPaddingHeader.gridy = 3;
		add(lblPaddingHeader, gbc_lblPaddingHeader);

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
		add(lblMarginHeader, gbc_lblMarginHeader);

		tf_mleft = new JTextField();
		addTextFieldAt(1, 8, "Left", tf_mleft);

		tf_mright = new JTextField();
		addTextFieldAt(3, 8, "Right", tf_mright);

		tf_mtop = new JTextField();
		addTextFieldAt(1, 9, "Top", tf_mtop);

		tf_mbottom = new JTextField();
		addTextFieldAt(3, 9, "Bottom", tf_mbottom);
	}
	
	public LayoutInfoSet getInfoSet() {
		LayoutInfoSet s = new LayoutInfoSet();
		
		s.stripes = (int) sp_stripes.getValue();
		
		s.height = tryConvert(tf_height);
		s.width = tryConvert(tf_width);
		
		s.paddingLeft = tryConvert(tf_pleft);
		s.paddingRight = tryConvert(tf_pright);
		s.paddingTop = tryConvert(tf_ptop);
		s.paddingBottom = tryConvert(tf_pbottom);
		
		s.marginLeft = tryConvert(tf_mleft);
		s.marginRight = tryConvert(tf_mright);
		s.marginTop = tryConvert(tf_mtop);
		s.marginBottom = tryConvert(tf_mbottom);
		
		return s;
	}
	
}
