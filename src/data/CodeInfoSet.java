package data;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

public class CodeInfoSet implements Serializable {

	private static final long serialVersionUID = 4779624829605069189L;

	public static final Color FOREGROUND = Color.BLACK;
	public static final Color BACKGROUND = Color.WHITE;

	public CodeStripe[] stripes;
	public List<boolean[]> actives;
	public boolean[] selected;
	public Size marginLeft, marginRight, marginTop, marginBottom;
	public Color foreground = FOREGROUND, background = BACKGROUND;

	public CodeInfoSet() {
		actives = new java.util.ArrayList<>();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof CodeInfoSet)) {
			return false;
		}
		CodeInfoSet c = (CodeInfoSet) o;
		return this.hashCode() == c.hashCode();
	}

	public Size getWidth() {
		Size total = marginLeft.add(marginRight);
		if (0 < stripes.length) {
			CodeStripe s = stripes[0];
			total = total.add(s.getWidth());
		}
		return total;
	}

	public Size getHeight() {
		Size total = marginTop.add(marginBottom);
		if (0 < stripes.length) {
			CodeStripe s = stripes[0];
			// -1 = last pitch shouldn't be added
			float totalPitch = s.pitch.get() * (stripes.length - 1);
			total = total.add(new Size(s.getHeight().get() * stripes.length + totalPitch));
		}
		return total;
	}

}
