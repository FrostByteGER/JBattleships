/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.miginfocom.swing.MigLayout;

import java.awt.SystemColor;
import java.io.File;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGameFieldsPanel extends JPanel {
	
	private JBSGUI parent;
	private JPanel header;
	private JPanel buttonPanel;
	private JButton btnCancel;
	private JButton btnContinue;
	private GameFieldPanel fieldPanel;
	private JPanel shipPanel;
	private JButton btnDestroyer;
	private JButton btnFrigate;
	private JButton btnCorvette;
	private JButton btnSubmarine;
	private JPanel centerPanel;
	private JTextArea textArea;
	
	private int destroyersLeft = 0;
	private int frigatesLeft = 0;
	private int corvettesLeft = 0;
	private int subsLeft = 0;
	private JButton btnSave;
	
	/**
	 * 
	 * @param parent
	 */
	public PreGameFieldsPanel(JBSGUI parent) {
		
		GameManager gm = JBattleships.game.getGameManager();
		destroyersLeft = gm.getDestroyerCount();
		frigatesLeft = gm.getFrigateCount();
		corvettesLeft = gm.getCorvetteCount();
		subsLeft = gm.getSubmarineCount();
		fieldPanel = new GameFieldPanel(new JBSGameField(gm.getFieldSize()), 400, gm.getFieldSize());
		
		this.parent = parent;
		this.header = parent.generateHeader();
		setLayout(new BorderLayout(0, 0));
		add(header, BorderLayout.NORTH);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(buttonPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("cancel")){
					JBSCore.msgLogger.addMessage("Called Command: \"" + e.getActionCommand() + "\" on " + PreGameFieldsPanel.this.getClass());
					PreGameFieldsPanel.this.parent.swapContainer(PreGameFieldsPanel.this.parent.getMainPanel());
				}
			}
		});
		buttonPanel.add(btnCancel);
		
		btnContinue = new JButton("Continue");
		btnContinue.setActionCommand("continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("continue")){
				}
			}
		});
		buttonPanel.add(btnContinue);
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc_fieldPanel = new GridBagConstraints();
		gbc_fieldPanel.weightx = 0.9;
		gbc_fieldPanel.weighty = 1.0;
		gbc_fieldPanel.fill = GridBagConstraints.BOTH;
		gbc_fieldPanel.insets = new Insets(0, 0, 0, 5);
		gbc_fieldPanel.gridx = 0;
		gbc_fieldPanel.gridy = 0;
		centerPanel.setLayout(new MigLayout("", "[grow][561px]", "[412px,grow]"));
		
		centerPanel.add(fieldPanel, "cell 0 0,width :90%:,grow");

		
		shipPanel = new JPanel();
		centerPanel.add(shipPanel, "cell 1 0,grow");
		shipPanel.setBorder(new TitledBorder(null, "Ship List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_shipPanel = new GridBagLayout();
		gbl_shipPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_shipPanel.columnWeights = new double[]{1.0};
		gbl_shipPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		shipPanel.setLayout(gbl_shipPanel);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setWrapStyleWord(true);
		textArea.setText("JESUS FUCKING CHRIST ICH BRING DEN ERFINDER DES DRECKS GRIDBAGLAYOUTS UM, ICH SCHW\u00D6RS BEI FUCKING GOTT");
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.menu);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		shipPanel.add(textArea, gbc_textArea);
		
		btnDestroyer = new JButton("Destroyers Left: " + destroyersLeft);
		btnDestroyer.setActionCommand("placedest");
		btnDestroyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("placedest")){
					if(destroyersLeft > 0){
						destroyersLeft--;
						btnDestroyer.setText("Destroyers Left: " + destroyersLeft);						
					}
				}
			}
		});
		GridBagConstraints gbc_btnDestroyer = new GridBagConstraints();
		gbc_btnDestroyer.ipady = 20;
		gbc_btnDestroyer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDestroyer.insets = new Insets(0, 5, 5, 0);
		gbc_btnDestroyer.gridx = 0;
		gbc_btnDestroyer.gridy = 1;
		shipPanel.add(btnDestroyer, gbc_btnDestroyer);
		
		btnFrigate = new JButton("Frigates Left: " + frigatesLeft);
		GridBagConstraints gbc_btnFrigate = new GridBagConstraints();
		gbc_btnFrigate.ipady = 20;
		gbc_btnFrigate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFrigate.insets = new Insets(0, 5, 5, 0);
		gbc_btnFrigate.gridx = 0;
		gbc_btnFrigate.gridy = 2;
		shipPanel.add(btnFrigate, gbc_btnFrigate);
		
		btnCorvette = new JButton("Corvettes Left: " + corvettesLeft);
		GridBagConstraints gbc_btnCorvette = new GridBagConstraints();
		gbc_btnCorvette.ipady = 20;
		gbc_btnCorvette.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCorvette.insets = new Insets(0, 5, 5, 0);
		gbc_btnCorvette.gridx = 0;
		gbc_btnCorvette.gridy = 3;
		shipPanel.add(btnCorvette, gbc_btnCorvette);
		
		btnSubmarine = new JButton("Submarines Left: " + subsLeft);
		GridBagConstraints gbc_btnSubmarine = new GridBagConstraints();
		gbc_btnSubmarine.ipady = 20;
		gbc_btnSubmarine.insets = new Insets(0, 5, 5, 0);
		gbc_btnSubmarine.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSubmarine.gridx = 0;
		gbc_btnSubmarine.gridy = 4;
		shipPanel.add(btnSubmarine, gbc_btnSubmarine);
		
		btnSave = new JButton("Save Game");
		btnSave.setActionCommand("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("save")){
					try {
						JAXBContext jaxb = JAXBContext.newInstance(Game.class);
						Marshaller m = jaxb.createMarshaller();
						m.marshal(JBattleships.game.getGameManager().getGame(), new File("Data/testsave.xml"));
					} catch (JAXBException jaxbe) {
						// TODO Auto-generated catch block
						jaxbe.printStackTrace();
					}

				}
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 5, 0, 0);
		gbc_btnSave.ipady = 20;
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 5;
		shipPanel.add(btnSave, gbc_btnSave);
		
		
		
		
	}

}
