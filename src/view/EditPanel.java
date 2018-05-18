package view;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.InputListener;
import data.CodeInfoSet;
import data.CodeStripe;
import data.LayoutInfoSet;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;

import java.awt.Image;

public class EditPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private LayoutPanel layoutPanel;
	private PagePanel pagePanel;
	private ColorPanel colorPanel;
	
	private JScrollPane scrollPane;
	private JLabel preview;

	public EditPanel(InputListener listener) {
		super(listener);
		setLayout(new BorderLayout(0, 0));
		
		layoutPanel = new LayoutPanel(listener);
		pagePanel = new PagePanel(listener);
		colorPanel = new ColorPanel(listener);
		
		JSplitPane splitPane = new JSplitPane();		
		JTabbedPane tabbedPane = new JTabbedPane();
		add(splitPane);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setRightComponent(scrollPane);

		preview = new JLabel("");
		scrollPane.setViewportView(preview);
		
		splitPane.setLeftComponent(tabbedPane);
		tabbedPane.addTab("Layout", layoutPanel);
		tabbedPane.addTab("Color", colorPanel);
		tabbedPane.addTab("Pages", pagePanel);
	}
	
	public void setInfoSet(CodeInfoSet next) {
		layoutPanel.setInfoSet(next);
		colorPanel.setInfoSet(next);
	}
	
	public CodeInfoSet getInfoSet() {
		CodeInfoSet c = new CodeInfoSet();
		
		LayoutInfoSet layout = layoutPanel.getInfoSet();
		
		c.foreground = colorPanel.getForegroundColor();
		c.background = colorPanel.getBackgroundColor();

		c.marginLeft = layout.marginLeft;
		c.marginRight = layout.marginRight;
		c.marginTop = layout.marginTop;
		c.marginBottom = layout.marginBottom;

		c.stripes = new CodeStripe[layout.stripes];
		for (int i = 0; i < layout.stripes; i++) {
			c.stripes[i] = new CodeStripe(layout.width, layout.height);

			c.stripes[i].paddingLeft = layout.paddingLeft;
			c.stripes[i].paddingRight = layout.paddingRight;
			c.stripes[i].paddingTop = layout.paddingTop;
			c.stripes[i].paddingBottom = layout.paddingBottom;
		}

		return c;
	}

	public void setPreview(Image img) {
		preview.setIcon(new ImageIcon(img));
		preview.revalidate();
		scrollPane.revalidate();
		scrollPane.repaint();
	}

}
