package data;

import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PageNode extends JPanel {

	private static final long serialVersionUID = -8605397392344724895L;
	
	public boolean[] actives;
	
	public PageNode(String label) {
		add(new JLabel(label));
	}
	
	public void adjust(int size) {
		if(this.actives == null) {
			this.actives = new boolean[size];
			Arrays.fill(this.actives, true);
		} else {
			this.actives = Arrays.copyOf(actives, size);
		}
	}
	
}
