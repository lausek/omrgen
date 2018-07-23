package view;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.handler.BaseHandler;
import control.handler.PageHandler;
import control.handler.EditHandler;
import data.CodeInfoSet;
import data.CodeStripe;
import data.LayoutInfoSet;
import data.PageNode;

import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;

import java.awt.Image;
import java.util.Enumeration;

public class EditPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	public PagePanel pagePanel;
	public CodePanel codePanel;
	public LayoutPanel layoutPanel;
	
	private ColorPanel colorPanel;

	private JScrollPane scrollPane;
	private JLabel preview;

	public EditPanel(BaseHandler listener) {
		super(listener);
		setLayout(new BorderLayout(0, 0));

		layoutPanel = new LayoutPanel(listener);
		colorPanel = new ColorPanel(listener, this);
		pagePanel = new PagePanel(new PageHandler(), this);
		codePanel = new CodePanel(listener, this);

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
		tabbedPane.addTab("Code", codePanel);

		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				((BasePanel) tabbedPane.getSelectedComponent()).open();
			}
		});
	}
	
	public void revalidatePreview() {
		((EditHandler)handler).revalidatePreview();
	}

	public void setInfoSet(CodeInfoSet next) {
		layoutPanel.setInfoSet(next);
		colorPanel.setInfoSet(next);
		pagePanel.setInfoSet(next);
	}

	public CodeInfoSet getInfoSet() {
		CodeInfoSet c = new CodeInfoSet();

		LayoutInfoSet layout = layoutPanel.getInfoSet();

		c.actives.clear();
		Enumeration<PageNode> pages = pagePanel.model.elements();
		while (pages.hasMoreElements()) {
			c.actives.add(pages.nextElement().actives);
		}
		
		if (0 <= pagePanel.lsPages.getSelectedIndex()) {
			c.selected = pagePanel.lsPages.getSelectedIndex();
		} else {
			c.selected = null;
		}

		c.foreground = colorPanel.getForegroundColor();
		c.background = colorPanel.getBackgroundColor();

		c.marginLeft = layout.marginLeft;
		c.marginRight = layout.marginRight;
		c.marginTop = layout.marginTop;
		c.marginBottom = layout.marginBottom;

		c.stripes = new CodeStripe[layout.stripes];
		for (int i = 0; i < layout.stripes; i++) {
			c.stripes[i] = new CodeStripe(layout.width, layout.height);

			c.stripes[i].pitch = layout.pitch;
			c.stripes[i].paddingLeft = layout.paddingLeft;
			c.stripes[i].paddingRight = layout.paddingRight;
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
