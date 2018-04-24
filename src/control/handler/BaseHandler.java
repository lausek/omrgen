package control.handler;

import control.InputListener;
import view.BasePanel;

public abstract class BaseHandler implements InputListener {
	
	public BasePanel getViewPanel() {
		return null;
	}
	
	public abstract boolean open();
	public abstract boolean close();
	
}
