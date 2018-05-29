package control.handler;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

import control.LayoutException;
import control.Visualizer;
import data.CodeInfoSet;
import view.BasePanel;
import view.BasePanel.MessageType;
import view.EditPanel;

public class EditHandler extends BaseHandler {

	private EditPanel editPanel;
	private CodeInfoSet lastInfoSet;
	private BufferedImage standardPreview;

	public EditHandler() {
		this.editPanel = new EditPanel(this);

		standardPreview = new BufferedImage(100, 200, BufferedImage.TYPE_INT_ARGB);

		editPanel.setPreview(standardPreview);
	}

	public boolean loadState(File fp) {
		try (FileInputStream stream = new FileInputStream(fp)) {
			try (ObjectInputStream ostream = new ObjectInputStream(stream)) {
				CodeInfoSet loaded = (CodeInfoSet) ostream.readObject();
				editPanel.setInfoSet(loaded);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveState(File fp) {
		try (FileOutputStream stream = new FileOutputStream(fp)) {
			try (ObjectOutputStream istream = new ObjectOutputStream(stream)) {
				istream.writeObject(lastInfoSet);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void revalidatePreview() {
		if (editPanel == null) {
			return;
		}

		editPanel.pagePanel.adjustAll((int)editPanel.layoutPanel.sp_stripes.getValue());
		
		CodeInfoSet next = editPanel.getInfoSet();
		if (lastInfoSet == null || !lastInfoSet.equals(next)) {
			try {
				standardPreview = Visualizer.toImage(next);
				editPanel.setPreview(standardPreview);
				lastInfoSet = next;
			} catch (LayoutException e) {
				editPanel.displayMessage(MessageType.ERROR, e.getMessage());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() instanceof JTextField) {
			JTextField t = (JTextField) evt.getSource();
			try {
				Float.parseFloat(t.getText());
				revalidatePreview();
			} catch (NumberFormatException e) {
				t.setText("0");
			}
		} else if (evt.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) evt.getSource();

			// TODO: this is ugly
			if (item.getText().equals("Export...")) {
				revalidatePreview();

				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (chooser.showSaveDialog(editPanel) == JFileChooser.APPROVE_OPTION) {
					// TODO: add try for exportToImage
					if (Visualizer.exportToImage(chooser.getSelectedFile(), lastInfoSet)) {
						editPanel.displayMessage(MessageType.SUCCESS, "Export finished");
					}
				}
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent evt) {
		if (evt.getSource() instanceof JSpinner) {
			revalidatePreview();
		}
	}

	@Override
	public BasePanel getPanel() {
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
	public void setPanel(BasePanel panel) {
		
	}

}
