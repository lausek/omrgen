package control;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.CodeInfoSet;
import data.CodeStripe;

public class Visualizer {

	public static BufferedImage toImage(CodeInfoSet codeInfoSet) throws LayoutException {
		int w = codeInfoSet.getWidth();
		int h = codeInfoSet.getHeight();

		if (w <= 0 || h <= 0) {
			throw new LayoutException();
		}

		BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = b.getGraphics();
		g.setColor(codeInfoSet.background);
		g.fillRect(0, 0, w, h);

		if (0 < codeInfoSet.stripes.length) {
			int offx = codeInfoSet.marginLeft;
			int offy = codeInfoSet.marginTop;

			g.setColor(codeInfoSet.foreground);
			for (int i = 0; i < codeInfoSet.stripes.length; i++) {
				CodeStripe s = codeInfoSet.stripes[i];
				g.fillRect(offx + s.paddingLeft, offy + s.paddingTop, s.width, s.height);

				offy += s.getHeight();
			}
		}

		return b;
	}

	public static boolean exportToImage(File target, CodeInfoSet codeInfoSet) {

		try {
			File outFile = new File(target.getAbsolutePath() + "/testfile.jpg");
			ImageIO.write(toImage(codeInfoSet), "JPG", outFile);
		} catch (IOException | LayoutException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
