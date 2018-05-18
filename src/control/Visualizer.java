package control;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.CodeInfoSet;
import data.CodeStripe;
import data.Size;
import data.Size.Unit;

public class Visualizer {

	public static BufferedImage toImage(CodeInfoSet codeInfoSet) throws LayoutException {
		int w = codeInfoSet.getWidth().geti(Unit.pixel);
		int h = codeInfoSet.getHeight().geti(Unit.pixel);

		if (w <= 0 || h <= 0) {
			if (0 < codeInfoSet.stripes.length) {
				throw new LayoutException("Please specify width and height");
			} else {
				throw new LayoutException("Please set at least one stripe");
			}
		}

		BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = b.getGraphics();
		g.setColor(codeInfoSet.background);
		g.fillRect(0, 0, w, h);

		if (0 < codeInfoSet.stripes.length) {
			int offx = codeInfoSet.marginLeft.geti(Unit.pixel);
			int offy = codeInfoSet.marginTop.geti(Unit.pixel);

			g.setColor(codeInfoSet.foreground);
			for (int i = 0; i < codeInfoSet.stripes.length; i++) {
				CodeStripe s = codeInfoSet.stripes[i];
				g.fillRect(offx + s.paddingLeft.geti(Unit.pixel), offy + s.paddingTop.geti(Unit.pixel), s.width.geti(Unit.pixel), s.height.geti(Unit.pixel));

				offy += s.getHeight().geti(Unit.pixel);
			}
		}

		return b;
	}

	// TODO: add throws
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
