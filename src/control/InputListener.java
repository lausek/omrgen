package control;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

import view.BasePanel;

public interface InputListener extends ActionListener, ChangeListener {
	public void changeEvent();
	public boolean isValidNumber();
}
