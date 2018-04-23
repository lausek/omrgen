package data;

public class CodeStripe {
	
	public int width, height;
	public int paddingLeft, paddingRight, paddingTop, paddingBottom;
	public boolean active;
	
	public CodeStripe(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return paddingLeft + paddingRight + width;
	}
	
	public int getHeight() {
		return paddingTop + paddingBottom + height;
	}
	
}
