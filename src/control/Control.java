package control;

import control.handler.EditHandler;
import view.View;

public class Control {
	
	private View view;
	private EditHandler editHandler;
	
	public Control() {
		view = new View(this);
		editHandler = new EditHandler();
		
		view.switchTo(editHandler);
		
		view.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Control();
	}	
	
}
