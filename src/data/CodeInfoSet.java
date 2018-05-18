package data;

import java.awt.Color;
import java.io.Serializable;

public class CodeInfoSet implements Serializable {
	
	private static final long serialVersionUID = 4779624829605069189L;
	
	public static final Color FOREGROUND = Color.BLACK;
	public static final Color BACKGROUND = Color.WHITE;
	
	public CodeStripe[] stripes;
	public Size marginLeft, marginRight, marginTop, marginBottom;
	public Color foreground = FOREGROUND, background = BACKGROUND;
	
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
			total = total.add(new Size(s.getHeight().get() * stripes.length));
		}
		return total;
	}

}
