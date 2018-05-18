package data;

public class CodeStripe {
	
	public Size width, height;
	public Size paddingLeft, paddingRight, paddingTop, paddingBottom;
	public boolean active;
	
	public CodeStripe(Size width, Size height) {
		this.width = width;
		this.height = height;
	}
	
	public Size getWidth() {
		return paddingLeft.add(paddingRight).add(width);
	}
	
	public Size getHeight() {
		return paddingTop.add(paddingBottom).add(height);
	}
	
}
