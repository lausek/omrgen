package data;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PageNode extends JPanel implements KeyListener {

	private static final long serialVersionUID = -8605397392344724895L;

	public String name;
	public boolean[] actives;

	private JLabel label;

	public PageNode(String name) throws IllegalArgumentException {
		this.name = name.replace(" ", "-");
		if (!isValidName(this.name)) {
			throw new IllegalArgumentException();
		}

		this.name = name;
		this.label = new JLabel(this.name);

		add(this.label);
	}
	
	private boolean isValidName(String name) {
		return Pattern.matches("^[\\dA-z-]+$", this.name);
	}

	public void adjust(int size) {
		if (this.actives == null) {
			this.actives = new boolean[size];
			Arrays.fill(this.actives, true);
		} else {
			this.actives = Arrays.copyOf(actives, size);
		}
	}

	public void rename() {
		JTextField newName = new JTextField(this.name);
		newName.addKeyListener(this);
		add(newName);
		this.label.setVisible(false);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() != 27) {
			return;
		}
		JTextField ref = (JTextField) e.getSource();
		
		if (isValidName(ref.getText())) {
			name = ref.getText();
			label.setText(name);
		}
		
		label.setVisible(true);
		remove(ref);
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}


	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}
