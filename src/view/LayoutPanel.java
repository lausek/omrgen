package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import control.handler.BaseHandler;
import data.CodeInfoSet;
import data.CodeStripe;
import data.LayoutInfoSet;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class LayoutPanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	public JTextField tf_width, tf_height;
	public JSpinner sp_stripes;
	private JTextField tf_pitch;
	// padding
	private JTextField tf_pleft, tf_pright;
	// margin
	private JTextField tf_mleft, tf_mright, tf_mtop, tf_mbottom;
	private JLabel tf_totalWidth, tf_totalHeight;
	
	public LayoutPanel(BaseHandler listener) {
		super(listener);
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		GridBagLayout gbl_general = new GridBagLayout();
		gbl_general.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_general.columnWidths = new int[] { 46, 108, 31, 104 };
		gbl_general.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		settingsPanel.setLayout(gbl_general);

		JLabel lblStripes = new JLabel("Stripes");
		GridBagConstraints gbc_lblStripes = new GridBagConstraints();
		gbc_lblStripes.insets = new Insets(0, 0, 5, 5);
		gbc_lblStripes.gridx = 0;
		gbc_lblStripes.gridy = 0;
		settingsPanel.add(lblStripes, gbc_lblStripes);

		sp_stripes = new JSpinner(new javax.swing.SpinnerNumberModel(1,1,999,1));
	    sp_stripes.addChangeListener(listener);
		GridBagConstraints gbc_sp_stripes = new GridBagConstraints();
		gbc_sp_stripes.anchor = GridBagConstraints.WEST;
		gbc_sp_stripes.insets = new Insets(0, 0, 5, 5);
		gbc_sp_stripes.gridx = 1;
		gbc_sp_stripes.gridy = 0;
		settingsPanel.add(sp_stripes, gbc_sp_stripes);

		tf_width = new JTextField();
		addTextFieldAt(1, 1, "Width", tf_width, settingsPanel);

		tf_height = new JTextField();
		addTextFieldAt(3, 1, "Height", tf_height, settingsPanel);
		
		tf_pitch = new JTextField();
		addTextFieldAt(1, 3, "Pitch", tf_pitch, settingsPanel);
		
		JLabel lblPaddingHeader = new JLabel("Padding");
		GridBagConstraints gbc_lblPaddingHeader = new GridBagConstraints();
		gbc_lblPaddingHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaddingHeader.gridx = 0;
		gbc_lblPaddingHeader.gridy = 4;
		settingsPanel.add(lblPaddingHeader, gbc_lblPaddingHeader);

		tf_pleft = new JTextField();
		addTextFieldAt(1, 5, "Left", tf_pleft, settingsPanel);

		tf_pright = new JTextField();
		addTextFieldAt(3, 5, "Right", tf_pright, settingsPanel);

		JLabel lblMarginHeader = new JLabel("Margin");
		GridBagConstraints gbc_lblMarginHeader = new GridBagConstraints();
		gbc_lblMarginHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarginHeader.gridx = 0;
		gbc_lblMarginHeader.gridy = 7;
		settingsPanel.add(lblMarginHeader, gbc_lblMarginHeader);

		tf_mleft = new JTextField();
		addTextFieldAt(1, 8, "Left", tf_mleft, settingsPanel);

		tf_mright = new JTextField();
		addTextFieldAt(3, 8, "Right", tf_mright, settingsPanel);

		tf_mtop = new JTextField();
		addTextFieldAt(1, 9, "Top", tf_mtop, settingsPanel);

		tf_mbottom = new JTextField();
		addTextFieldAt(3, 9, "Bottom", tf_mbottom, settingsPanel);
		
		// TODO: remove default values
		tf_width.setText("10");
		tf_height.setText("0.3");
		tf_pitch.setText("2.54");
		sp_stripes.setValue(10);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.gridwidth = 2;
		gbc_infoPanel.insets = new Insets(0, 0, 0, 5);
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 11;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{46, 0, 0};
		gbl_infoPanel.rowHeights = new int[]{14, 0, 0};
		gbl_infoPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		infoPanel.setLayout(gbl_infoPanel);
		
		JLabel lblTotalWidth = new JLabel("Total width");
		GridBagConstraints gbc_lblTotalWidth = new GridBagConstraints();
		gbc_lblTotalWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalWidth.gridx = 0;
		gbc_lblTotalWidth.gridy = 0;
		infoPanel.add(lblTotalWidth, gbc_lblTotalWidth);
		
		tf_totalWidth = new JLabel("0");
		tf_totalWidth.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblTotalWidthVal = new GridBagConstraints();
		gbc_lblTotalWidthVal.insets = new Insets(0, 0, 5, 0);
		gbc_lblTotalWidthVal.gridx = 1;
		gbc_lblTotalWidthVal.gridy = 0;
		infoPanel.add(tf_totalWidth, gbc_lblTotalWidthVal);
		
		JLabel lblTotalHeight = new JLabel("Total height");
		GridBagConstraints gbc_lblTotalHeight = new GridBagConstraints();
		gbc_lblTotalHeight.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotalHeight.gridx = 0;
		gbc_lblTotalHeight.gridy = 1;
		infoPanel.add(lblTotalHeight, gbc_lblTotalHeight);
		
		tf_totalHeight = new JLabel("0");
		tf_totalHeight.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblTotalHeightVal = new GridBagConstraints();
		gbc_lblTotalHeightVal.gridx = 1;
		gbc_lblTotalHeightVal.gridy = 1;
		infoPanel.add(tf_totalHeight, gbc_lblTotalHeightVal);
		
		add(infoPanel);
		add(settingsPanel);
	}
	
	public void setInfoSet(CodeInfoSet next) {
		CodeStripe first = next.stripes[0];
		tf_height.setText(first.height.toString());
		tf_width.setText(first.width.toString());
		
		tf_pitch.setText(first.pitch.toString());
		
		tf_pleft.setText(first.paddingLeft.toString());
		tf_pright.setText(first.paddingRight.toString());

		tf_mleft.setText(next.marginLeft.toString());
		tf_mright.setText(next.marginRight.toString());
		tf_mtop.setText(next.marginTop.toString());
		tf_mbottom.setText(next.marginBottom.toString());
		
		updateTotals(next);
		
		// this triggers a revalidate!
		sp_stripes.setValue(next.stripes.length);
	}
	
	public LayoutInfoSet getInfoSet() {
		LayoutInfoSet s = new LayoutInfoSet();
		
		s.stripes = (int) sp_stripes.getValue();
		
		s.height = tryConvert(tf_height);
		s.width = tryConvert(tf_width);
		
		s.pitch = tryConvert(tf_pitch);
		
		s.paddingLeft = tryConvert(tf_pleft);
		s.paddingRight = tryConvert(tf_pright);
		
		s.marginLeft = tryConvert(tf_mleft);
		s.marginRight = tryConvert(tf_mright);
		s.marginTop = tryConvert(tf_mtop);
		s.marginBottom = tryConvert(tf_mbottom);
		
		return s;
	}
	
	public void updateTotals(CodeInfoSet next) {
		tf_totalWidth.setText(""+next.getWidth());
		tf_totalHeight.setText(""+next.getHeight());
	}
	
}
