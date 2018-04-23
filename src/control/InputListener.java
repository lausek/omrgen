package control;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

public interface InputListener extends ActionListener, ChangeListener {
	public void changeEvent();
	public boolean isValidNumber();
}
