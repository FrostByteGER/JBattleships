/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGamePanel extends JPanel {

	private JBSGUI parent;
	private JPanel header;
	private JPanel centerPanel;
	private JPanel panel;
	private JButton btnCancel;
	private JButton btnContinue;
	
	
	/**
	 * Create the panel.
	 */
	public PreGamePanel(JBSGUI parent) {
		this.parent = parent;
		header = parent.generateHeader();
		setLayout(new BorderLayout(0, 0));
		add(header, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(null, "Main Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		centerPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("cancel")){
					
				}
			}
		});
		panel.add(btnCancel);
		
		btnContinue = new JButton("Continue");
		btnContinue.setActionCommand("continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("continue")){
					
				}
			}
		});
		panel.add(btnContinue);
		
	}

}
