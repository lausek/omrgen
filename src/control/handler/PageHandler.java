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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
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
