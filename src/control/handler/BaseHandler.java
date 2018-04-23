package control.handler;

import view.BasePanel;

public abstract class BaseHandler {
	
	public BasePanel getViewPanel() {
		return null;
	}
	
	public abstract boolean open();
	public abstract boolean close();
	
}
