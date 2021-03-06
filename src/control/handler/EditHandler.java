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
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

import control.Control;
import control.LayoutException;
import control.Visualizer;
import data.CodeInfoSet;
import view.BasePanel;
import view.BasePanel.MessageType;
import view.EditPanel;
import view.View;
import view.View.FileStatus;

public class EditHandler extends BaseHandler {

	private Control control;
	private EditPanel editPanel;
	private CodeInfoSet lastInfoSet;
	private BufferedImage standardPreview;
	private File loadedFile;
	private int lastSerialized = 0;

	public EditHandler(Control control) {
		this.control = control;
		this.editPanel = new EditPanel(this);
		
		View.setFileStatus(FileStatus.INITIAL);
		
		standardPreview = new BufferedImage(100, 200, BufferedImage.TYPE_INT_ARGB);

		editPanel.setPreview(standardPreview);
	}
	
	public File getLoadedFile() {
		return this.loadedFile;
	}
	
	public boolean loadState(File fp) {
		try (FileInputStream stream = new FileInputStream(fp)) {
			try (ObjectInputStream ostream = new ObjectInputStream(stream)) {
				CodeInfoSet loaded = (CodeInfoSet) ostream.readObject();
				editPanel.setInfoSet(loaded);
				
				lastInfoSet = editPanel.getInfoSet();
				lastSerialized = lastInfoSet.hashCode();
				loadedFile = fp;
				View.setFileStatus(FileStatus.SAVED);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveState(File fp) {
		revalidatePreview();
		try (FileOutputStream stream = new FileOutputStream(fp)) {
			try (ObjectOutputStream istream = new ObjectOutputStream(stream)) {
				istream.writeObject(lastInfoSet);
				lastSerialized = lastInfoSet.hashCode();
				View.setFileStatus(FileStatus.SAVED);
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

		editPanel.pagePanel.adjustAll((int) editPanel.layoutPanel.sp_stripes.getValue());

		CodeInfoSet next = editPanel.getInfoSet();
		if (lastInfoSet == null || !lastInfoSet.equals(next)) {
			try {
				standardPreview = Visualizer.toImage(next);
				editPanel.setPreview(standardPreview);
				editPanel.layoutPanel.updateTotals(next);
				View.setFileStatus(FileStatus.PENDING);
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
		if (!editPanel.close()) {
			return false;
		}

		if (lastInfoSet == null || lastSerialized != lastInfoSet.hashCode()) {
			switch (JOptionPane.showConfirmDialog(editPanel, "There are unsaved changes. Do you want to save?")) {
			case JOptionPane.OK_OPTION:
				if (!control.saveState(null)) {
					return false;
				}
			}
		}
		return true;
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
