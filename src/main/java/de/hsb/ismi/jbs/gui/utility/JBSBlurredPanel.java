/**
 * 
 */
package de.hsb.ismi.jbs.gui.utility;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import de.hsb.ismi.jbs.engine.utility.Utility;

/**
 * Simple JPanel with blurred background.
 * <br><b> Be careful, quite performance hungry!</b>
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSBlurredPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2752252082960696166L;
	
	private BufferedImage blurredBackground = null;
	private int extraWidth   = 0;
	private int extraHeight  = 0;
	private int widthOffset  = 0;
	private int heightOffset = 0;
	
	/**
	 * 
	 * @param extraWidth
	 * @param extraHeight
	 */
	public JBSBlurredPanel(int extraWidth, int extraHeight) {
		setLayout(new BorderLayout());
		this.extraWidth = extraWidth;
		this.extraHeight = extraHeight;
		this.widthOffset = -extraWidth/2;
		this.heightOffset = -extraHeight/2;
	}
	
	/**
	 * 
	 * @param extraWidth
	 * @param extraHeight
	 * @param layout
	 */
	public JBSBlurredPanel(int extraWidth, int extraHeight, LayoutManager layout) {
		setLayout(layout);
		this.extraWidth = extraWidth;
		this.extraHeight = extraHeight;
		this.widthOffset = -extraWidth/2;
		this.heightOffset = -extraHeight/2;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
        if (visible) {
            Container parent = SwingUtilities.getAncestorOfClass(JRootPane.class, this);
            if (parent != null) {
                JRootPane rootPane = (JRootPane) parent;
                BufferedImage img = new BufferedImage(rootPane.getWidth(), rootPane.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = img.createGraphics();
                rootPane.printAll(g2d);
                g2d.dispose();

                blurredBackground = Utility.gaussianBlur(img, img.getWidth() + extraWidth, img.getHeight() + extraHeight, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            }
        }		
		super.setVisible(visible);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(blurredBackground, widthOffset, heightOffset, this);
	}

}
