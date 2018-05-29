package control.handler;

import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;

import view.BasePanel;
import view.PagePanel;

public class PageHandler extends BaseHandler {

	private PagePanel panel;

	@Override
	public void setPanel(BasePanel panel) {
		this.panel = (PagePanel) panel;
	}

	@Override
	public void changeEvent() {
		
	}

	@Override
	public boolean isValidNumber() {
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panel.btnAdd) {
			panel.addPage(panel.model.getSize() + 1);
		} else if (e.getSource() == panel.btnRemove) {
			int[] indices = panel.lsPages.getSelectedIndices();
			panel.removePage(indices);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}

	@Override
	public boolean open() {
		return false;
	}

	@Override
	public boolean close() {
		return false;
	}

}
