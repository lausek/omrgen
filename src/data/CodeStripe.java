package data;

import java.io.Serializable;

public class CodeStripe implements Serializable {
	
	private static final long serialVersionUID = 8915768258051523330L;
	
	public Size width, height;
	public Size pitch;
	public Size paddingLeft, paddingRight; //, paddingTop, paddingBottom;
	public boolean active;
	
	public CodeStripe(Size width, Size height) {
		this.width = width;
		this.height = height;
	}
	
	public Size getWidth() {
		return paddingLeft.add(paddingRight).add(width);
	}
	
	public Size getHeight() {
		return height.add(pitch);
	}
	
}
