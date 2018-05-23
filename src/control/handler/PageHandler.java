package control.handler;

import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;

import data.PageNode;
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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panel.btnAdd) {
			panel.model.addElement(new PageNode("Page "+panel.model.getSize()));
		} else if (e.getSource() == panel.btnRemove) {
			int[] indices = panel.lsPages.getSelectedIndices();
			// process in reverse
			for (int i = indices.length-1; 0 <= i; i--) {
				panel.model.remove(indices[i]);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

}
