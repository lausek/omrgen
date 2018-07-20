package control;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

import data.CodeInfoSet;
import data.CodeStripe;
import data.Size.Unit;

public class Visualizer {
	
	public static final String FORMAT = "png";
	public static final float INCH_2_CM = 2.54f;
	public static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;
	public static final int DPI = 300;

	public static BufferedImage toImage(CodeInfoSet codeInfoSet, boolean[] actives) throws LayoutException {
		int w = codeInfoSet.getWidth().geti(Unit.pixel);
		int h = codeInfoSet.getHeight().geti(Unit.pixel);

		if (w <= 0 || h <= 0) {
			if (0 < codeInfoSet.stripes.length) {
				throw new LayoutException("Please specify width and height");
			} else {
				throw new LayoutException("Please set at least one stripe");
			}
		}

		BufferedImage b = new BufferedImage(w, h, IMAGE_TYPE);
		Graphics g = b.getGraphics();
		g.setColor(codeInfoSet.background);
		g.fillRect(0, 0, w, h);

		if (0 < codeInfoSet.stripes.length) {
			int offx = codeInfoSet.marginLeft.geti(Unit.pixel);
			int offy = codeInfoSet.marginTop.geti(Unit.pixel);

			g.setColor(codeInfoSet.foreground);
			for (int i = 0; i < codeInfoSet.stripes.length; i++) {
				CodeStripe s = codeInfoSet.stripes[i];

				if (actives == null || actives[i]) {
					g.fillRect(offx + s.paddingLeft.geti(Unit.pixel), offy, s.width.geti(Unit.pixel),
							s.height.geti(Unit.pixel));
				}

				offy += s.height.add(s.pitch).geti(Unit.pixel);
			}
		}

		return b;
	}

	public static BufferedImage toImage(CodeInfoSet codeInfoSet) throws LayoutException {
		return codeInfoSet.selected != null ? toImage(codeInfoSet, codeInfoSet.actives.get(codeInfoSet.selected))
				: toImage(codeInfoSet, null);
	}

	// TODO: add throws
	public static boolean exportToImage(File target, CodeInfoSet codeInfoSet) {
		ImageWriter iw = null;
		IIOMetadata metadata = null;
		ImageWriteParam iwParam = null;
		int i = 0;

		try {
			for (Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(FORMAT); iter.hasNext();) {
				iw = iter.next();
				iwParam = iw.getDefaultWriteParam();
				ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(IMAGE_TYPE);
				metadata = iw.getDefaultImageMetadata(typeSpecifier, iwParam);
				// setDPI(metadata);
				break;
			}

			for (boolean[] page : codeInfoSet.actives) {
				i++;
				File outFile = new File(target.getAbsolutePath() + String.format("/page-%d.%s", i, FORMAT));

				try (ImageOutputStream stream = ImageIO.createImageOutputStream(outFile)) {
					BufferedImage img = toImage(codeInfoSet, page);
					iw.setOutput(stream);
					iw.write(metadata, new IIOImage(img, null, metadata), iwParam);
				}
			}
		} catch (IOException | LayoutException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private static void setDPI(IIOMetadata metadata) {
		double dotsPerMilli = 1.0 * DPI / 10 / INCH_2_CM;

		IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
		horiz.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
		vert.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode dim = new IIOMetadataNode("Dimension");
		dim.appendChild(horiz);
		dim.appendChild(vert);

		IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
		root.appendChild(dim);

		try {
			metadata.mergeTree("javax_imageio_1.0", root);
		} catch (IIOInvalidTreeException e) {
			e.printStackTrace();
		}
	}

}
