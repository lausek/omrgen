package view;

import control.InputListener;
import javax.swing.JList;
import javax.swing.JTextField;

import javax.swing.JCheckBox;

public class CodePanel extends BasePanel {

	private static final long serialVersionUID = -9071790078803808293L;

	private JList<JCheckBox> lsStripes;
	private JTextField tfError;

	public CodePanel(InputListener listener) {
		super(listener);

		tfError = new JTextField("Please select one node.");

		lsStripes = new JList<>();
		add(lsStripes);
	}

	public void load(boolean[] actives) {
		tfError.setVisible(0 == actives.length);
		if (0 < actives.length) {
			// TODO: refill lsStripes
			JCheckBox[] listData = new JCheckBox[actives.length];
			for (int i = 0; i < actives.length; i++) {
				listData[i] = new JCheckBox("Stripe " + i);
				listData[i].setSelected(actives[i]);
			}
			lsStripes.setListData(listData);
			lsStripes.setVisible(true);
		}
	}

}
