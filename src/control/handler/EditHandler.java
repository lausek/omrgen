package control.handler;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

import control.InputListener;
import control.LayoutException;
import control.Visualizer;
import data.CodeInfoSet;
import view.BasePanel;
import view.EditPanel;

public class EditHandler extends BaseHandler implements InputListener {
	
	private EditPanel editPanel;
	private CodeInfoSet lastInfoSet;
	private BufferedImage standardPreview;
	
	public EditHandler() {
		this.editPanel = new EditPanel(this);

		standardPreview = new BufferedImage(100, 200, BufferedImage.TYPE_INT_ARGB);
		
		editPanel.setPreview(standardPreview);
	}

	private void revalidate() {
		CodeInfoSet next = editPanel.getInfoSet();
		if (lastInfoSet == null || !lastInfoSet.equals(next)) {
			// TODO: rebuild
			try {
				standardPreview = Visualizer.toImage(next);
				editPanel.setPreview(standardPreview);
				lastInfoSet = next;
			} catch (LayoutException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public BasePanel getViewPanel() {
		return this.editPanel;
	}

	@Override
	public boolean open() {
		return editPanel.open();
	}

	@Override
	public boolean close() {
		return editPanel.close();
	}

	@Override
	public void changeEvent() {

	}

	@Override
	public boolean isValidNumber() {
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() instanceof JTextField) {
			JTextField t = (JTextField) evt.getSource();
			try {
				Integer.parseInt(t.getText());
				revalidate();
			} catch (NumberFormatException e) {
				t.setText("0");
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
//		revalidate();
	}

}
