/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OnlineConnectionPanel extends JPanel {
	
	private JBSGUI parent;
	private JPanel target = null;
	private JLabel lblMessage = new JLabel();
	/** Timer to change visibility of the Panel. */
	private Timer t = new Timer(true);

	private boolean asGlassPane = false;
	
	/**
	 * Create the panel.
	 */
	public OnlineConnectionPanel(JBSGUI parent, JPanel target, String message, boolean visible, boolean asGlassPane) {
		this.parent = parent;
		this.asGlassPane = asGlassPane;
		this.target = target;
		GridBagLayout gbl_glass = new GridBagLayout();
		gbl_glass.columnWidths = new int[] {200};
		gbl_glass.columnWeights = new double[]{0.0};
		gbl_glass.rowWeights = new double[]{0.0};
		this.setLayout(gbl_glass);
		
		lblMessage.setText(message);
		lblMessage.setFont(JBSGUI.SERVER_FONT);
		GridBagConstraints gbc_lblServerNotFound = new GridBagConstraints();
		gbc_lblServerNotFound.insets = new Insets(0, 0, 5, 0);
		gbc_lblServerNotFound.gridx = 0;
		gbc_lblServerNotFound.gridy = 0;
		this.add(lblMessage, gbc_lblServerNotFound);
		this.setOpaque(false);
		if(asGlassPane){
			parent.getMainFrame().setGlassPane(this);
			toggleVisibility(visible);
		}
		
	}

	/**
	 * 
	 * @param time
	 */
	public void setTimer(Consumer<Boolean> pointer, Boolean clearStack, long time){
		t = new Timer(true);
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if(asGlassPane){
					OnlineConnectionPanel.this.setVisible(false);
					target.setVisible(true);	
				}else{
					pointer.accept(clearStack);
				}

			}
		}, time);
	}
	
	/**
	 * 
	 */
	public void stopTimer(){
		t.cancel();
	}
	
	/**
	 * 
	 */
	public void toggleVisibility(boolean visible){
		if(visible){
			target.setVisible(false);
			this.setVisible(true);
		}else{
			target.setVisible(true);
			this.setVisible(false);
		}

	}

}
