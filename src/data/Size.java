package data;

public class Size {

//	mm -> pixel
	public static final double PIXEL_FACTOR = 3.779528;
	
	public static enum Unit {
		points,
		pixel,
		mm,
		cm,
	};
	
	private float value;
	
	public Size(float value) {
		this.value = value;
	}
	
	public Size add(Size o) {
		return new Size(this.value + o.get());
	}
	
	public float get() {
		return value;
	}
	
	public float get(Unit u) {
		switch (u) {
		case pixel:
			return (float) (get() * PIXEL_FACTOR);
		default:
			return get();
		}
	}
	
	public int geti(Unit u) {
		return (int) get(u);
	}
	
	public float set() {
		return value;
	}
	
}
