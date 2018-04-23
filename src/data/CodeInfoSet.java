package data;

public class CodeInfoSet {

	public CodeStripe[] stripes;
	public int marginLeft, marginRight, marginTop, marginBottom;

	public boolean equals(Object o) {
		if (!(o instanceof CodeInfoSet)) {
			return false;
		}
		CodeInfoSet c = (CodeInfoSet) o;
		return this.hashCode() == c.hashCode();
	}

	public int getWidth() {
		int total = marginLeft + marginRight;
		if (0 < stripes.length) {
			CodeStripe s = stripes[0];
			total += s.getWidth();
		}
		return total;
	}

	public int getHeight() {
		int total = marginTop + marginBottom;
		if (0 < stripes.length) {
			CodeStripe s = stripes[0];
			total += s.getHeight() * stripes.length;
		}
		return total;
	}

}
