package control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import data.CodeInfoSet;
import data.CodeStripe;

public class Visualizer {

	public static final Color FOREGROUND = Color.BLACK;
	public static final Color BACKGROUND = Color.WHITE;

	public static BufferedImage toImage(CodeInfoSet codeInfoSet) throws LayoutException {
		int w = codeInfoSet.getWidth();
		int h = codeInfoSet.getHeight();

		if (w <= 0 || h <= 0) {
			throw new LayoutException();
		}

		BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = b.getGraphics();
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, w, h);

		if (0 < codeInfoSet.stripes.length) {
			int offx = codeInfoSet.marginLeft;
			int offy = codeInfoSet.marginTop;

			g.setColor(FOREGROUND);
			for (int i = 0; i < codeInfoSet.stripes.length; i++) {
				CodeStripe s = codeInfoSet.stripes[i];
				g.fillRect(offx + s.paddingLeft, offy + s.paddingTop, s.width, s.height);
				
				offy += s.getHeight();
				
//				int off = i + 1;
//				g.fillRect((ml + s.paddingLeft) * off, (mt + s.paddingTop) * off, s.width, s.height);
			}
		}

		return b;
	}

}
