package control.handler;

import control.InputListener;
import view.BasePanel;

public abstract class BaseHandler implements InputListener {	
	public BasePanel getPanel() {
		return null;
	}
	public abstract void setPanel(BasePanel panel);
	public abstract boolean open();
	public abstract boolean close();
}
