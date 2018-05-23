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

public class LayoutPanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	public JTextField tf_width, tf_height;
	public JSpinner sp_stripes;
	private JTextField tf_pitch;
	// padding
	private JTextField tf_pleft, tf_pright;
	// margin
	private JTextField tf_mleft, tf_mright, tf_mtop, tf_mbottom;
	
	public LayoutPanel(BaseHandler listener) {
		super(listener);
		
		GridBagLayout gbl_general = new GridBagLayout();
		gbl_general.columnWidths = new int[] { 46, 0, 0, 0, 0 };
		gbl_general.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		setLayout(gbl_general);

		JLabel lblStripes = new JLabel("Stripes");
		GridBagConstraints gbc_lblStripes = new GridBagConstraints();
		gbc_lblStripes.insets = new Insets(0, 0, 5, 5);
		gbc_lblStripes.gridx = 0;
		gbc_lblStripes.gridy = 0;
		add(lblStripes, gbc_lblStripes);

		sp_stripes = new JSpinner(new javax.swing.SpinnerNumberModel(1,1,999,1));
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
		
		tf_pitch = new JTextField();
		addTextFieldAt(1, 3, "Pitch", tf_pitch);
		
		JLabel lblPaddingHeader = new JLabel("Padding");
		GridBagConstraints gbc_lblPaddingHeader = new GridBagConstraints();
		gbc_lblPaddingHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaddingHeader.gridx = 0;
		gbc_lblPaddingHeader.gridy = 4;
		add(lblPaddingHeader, gbc_lblPaddingHeader);

		tf_pleft = new JTextField();
		addTextFieldAt(1, 5, "Left", tf_pleft);

		tf_pright = new JTextField();
		addTextFieldAt(3, 5, "Right", tf_pright);

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
		
		// TODO: remove default values
		tf_width.setText("10");
		tf_height.setText("0.3");
		tf_pitch.setText("2.54");
		sp_stripes.setValue(10);
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
	
}
