/**
 *
 */
package de.hsb.ismi.jbs.gui.game;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.game.JBSGameType;
import de.hsb.ismi.jbs.engine.game.managers.GameManager;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSButtonGroup;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGamePanel extends JPanel {


	/**
	 *
	 */
	private static final long serialVersionUID = -8550769174904673444L;
	private JPanel header;
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JPanel settingsPanel;
	private JPanel mixedPanel;
	private JPanel shipPanel;
	private JPanel playerPanel;

	private JBSButton btnCancel;
	private JBSButton btnContinue;

	private PlayerSlotPanel[] playerPanels;
	private JLabel lblDestroyer;
	private JSpinner destroyerSpinner;
	private JLabel lblFrigate;
	private JSpinner frigateSpinner;
	private JLabel lblCorvette;
	private JSpinner corvetteSpinner;
	private JLabel lblSubmarines;
	private JSpinner subSpinner;
	private JPanel otherPanel;
	private JLabel lblFieldSize;
	private JSpinner fieldSizeSpinner;
	private JCheckBox chckbxUseCannon;
	private JCheckBox chckbxUseNavalMines;
	private JBSButtonGroup btnGroup;

	/** The GameType of this Panel */
	private JBSGameType gameType;
	private ArrayList<JBSPlayer> players = new ArrayList<>(0);


	/**
	 * Create the panel.
	 */
	public PreGamePanel(JBSGUI parent, JBSGameType type) {
		setOpaque(false);

		btnGroup = new JBSButtonGroup();
		playerPanels = new PlayerSlotPanel[8];
		for(int i = 0; i < playerPanels.length; i++){
			PlayerSlotPanel pp = new PlayerSlotPanel(type);
			if(i == 0){
				pp.setName(JBattleships.game.getDataManager().getProfileManager().getActiveProfile().getName());
			}else{
				pp.setName(JBattleships.game.getLocalization("GAME_PLAYER") + " #" + (i + 1));
			}
			btnGroup.add(pp.getCheckboxActive());
			playerPanels[i] = pp;
		}
		playerPanels[0].setActiveSelected(true);
		playerPanels[0].getNameField().setEditable(false);
		playerPanels[1].setActiveSelected(true);

		gameType = type;
		header = parent.generateHeader();
		setLayout(new BorderLayout(0, 0));
		add(header, BorderLayout.NORTH);



		centerPanel = new JPanel();
		centerPanel.setOpaque(true);
		centerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		centerPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), JBattleships.game.getLocalization("GAME_TITLE_MAIN_SETTINGS"), TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 25), new Color(0, 0, 0)));
		add(new AlphaContainer(centerPanel), BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		centerPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCancel = new JBSButton(JBattleships.game.getLocalization("GAME_CANCEL"));
		btnCancel.addActionListener(e -> {
			DebugLog.logInfo("Called Command: \"" + e.getActionCommand() + "\" on " +  PreGamePanel.this.getClass());
			parent.restorePrevContainer();
		});
		buttonPanel.add(new AlphaContainer(btnCancel) );

		btnContinue = new JBSButton(JBattleships.game.getLocalization("GAME_CONTINUE"));
		btnContinue.addActionListener(e -> {
					if(((Integer)fieldSizeSpinner.getValue())*((Integer)fieldSizeSpinner.getValue())-30 >= ((Integer)destroyerSpinner.getValue())*15+((Integer)subSpinner.getValue())*9+((Integer)frigateSpinner.getValue())*9+((Integer)corvetteSpinner.getValue())*6){
						GameManager gm = JBattleships.game.generateGame();
						for(int i = 0; i <playerPanels.length; i++){
							PlayerSlotPanel pregpp = playerPanels[i];
							boolean active = pregpp.isActiveSelected();
							if(active){
								boolean ai = pregpp.isAISelected();
								String name = pregpp.getName();
								if(ai){
									players.add(new JBSAIPlayer(name));
								}else{
									if(i == 0){
										players.add(new JBSPlayer(JBattleships.game.getDataManager().getProfileManager().getActiveProfile()));
									}else{
										players.add(new JBSPlayer(name));
									}
								}
							}
						}
						int[] count = new int[4];

						try{
							count[0] = ((Integer)destroyerSpinner.getValue());
						}catch(ClassCastException cce){
							DebugLog.logError(cce);
						}
						try{
							count[1] = ((Integer)frigateSpinner.getValue());
						}catch(ClassCastException cce){
							DebugLog.logError(cce);
						}
						try{
							count[2] = ((Integer)corvetteSpinner.getValue());
						}catch(ClassCastException cce){
							DebugLog.logError(cce);
						}
						try{
							count[3] = ((Integer)subSpinner.getValue());
						}catch(ClassCastException cce){
							DebugLog.logError(cce);
						}

						int fieldSize = 16;
						try{
							fieldSize = ((Integer)fieldSizeSpinner.getValue());
						}catch(ClassCastException cce){
							DebugLog.logError(cce);
						}
						gm.createGame(gameType,players.toArray(new JBSPlayer[players.size()]), fieldSize, count);
						DebugLog.logInfo("Created Game!");
						DebugLog.logInfo(gm.toString());

						parent.swapContainer(new PreGamePlacingPanel(parent));
					}

		});
		buttonPanel.add(new AlphaContainer(btnContinue) );

		settingsPanel = new JPanel();
		settingsPanel.setOpaque(false);
		settingsPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		centerPanel.add(settingsPanel, BorderLayout.CENTER);
		settingsPanel.setLayout(new GridLayout(1, 0, 0, 0));

		mixedPanel = new JPanel();
		mixedPanel.setOpaque(false);
		mixedPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		mixedPanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_TITLE_OTHER_SETTINGS"), TitledBorder.CENTER, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		settingsPanel.add(mixedPanel);
		mixedPanel.setLayout(new MigLayout("", "[grow,fill]", "[5%:n][60%:n,grow,fill]"));

		otherPanel = new JPanel();
		otherPanel.setOpaque(false);
		otherPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		mixedPanel.add(otherPanel, "cell 0 0,grow");
		GridBagLayout gbl_otherPanel = new GridBagLayout();
		gbl_otherPanel.columnWidths = new int[]{0, 0, 0};
		gbl_otherPanel.rowHeights = new int[]{0};
		gbl_otherPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_otherPanel.rowWeights = new double[]{0.0};
		otherPanel.setLayout(gbl_otherPanel);

		lblFieldSize = new JLabel(JBattleships.game.getLocalization("GAME_FIELD_SIZE"));
		lblFieldSize.setFont(JBSGUI.MAIN_FONT);
		lblFieldSize.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblFieldSize = new GridBagConstraints();
		gbc_lblFieldSize.fill = GridBagConstraints.BOTH;
		gbc_lblFieldSize.weighty = 1.0;
		gbc_lblFieldSize.weightx = 1.0;
		gbc_lblFieldSize.insets = new Insets(0, 0, 0, 0);
		gbc_lblFieldSize.gridx = 0;
		gbc_lblFieldSize.gridy = 0;
		otherPanel.add(lblFieldSize, gbc_lblFieldSize);

		fieldSizeSpinner = new JSpinner();
		fieldSizeSpinner.setFont(JBSGUI.MAIN_FONT);
		fieldSizeSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(16), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(1)));
		GridBagConstraints gbc_fieldSizeSpinner = new GridBagConstraints();
		gbc_fieldSizeSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldSizeSpinner.weighty = 1.0;
		gbc_fieldSizeSpinner.weightx = 1.0;
		gbc_fieldSizeSpinner.gridx = 1;
		gbc_fieldSizeSpinner.gridy = 0;
		otherPanel.add(fieldSizeSpinner, gbc_fieldSizeSpinner);

		chckbxUseNavalMines = new JCheckBox(JBattleships.game.getLocalization("GAME_USE_NAVAL_MINES"));
		chckbxUseNavalMines.setOpaque(false);
		chckbxUseNavalMines.setVisible(false);
		GridBagConstraints gbc_chckbxUseNavalMines = new GridBagConstraints();
		gbc_chckbxUseNavalMines.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxUseNavalMines.gridx = 0;
		gbc_chckbxUseNavalMines.gridy = 1;

		chckbxUseCannon = new JCheckBox(JBattleships.game.getLocalization("GAME_USE_COASTAL_ARTILLERY"));
		chckbxUseCannon.setOpaque(false);
		chckbxUseCannon.setVisible(false);
		GridBagConstraints gbc_chckbxUseCannon = new GridBagConstraints();
		gbc_chckbxUseCannon.weighty = 1.0;
		gbc_chckbxUseCannon.weightx = 1.0;
		gbc_chckbxUseCannon.gridx = 1;
		gbc_chckbxUseCannon.gridy = 1;

		playerPanel = new JPanel();
		playerPanel.setOpaque(false);
		playerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		playerPanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_TITLE_PLAYER_SETTINGS"), TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		mixedPanel.add(playerPanel, "cell 0 1,grow");
		playerPanel.setLayout(new GridLayout(8, 1, 0, 0));
		for(PlayerSlotPanel pgpp : playerPanels){
			pgpp.setOpaque(false);
			playerPanel.add(pgpp);
		}

		shipPanel = new JPanel();
		shipPanel.setOpaque(false);
		shipPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		shipPanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_TITLE_SHIP_SETTINGS"), TitledBorder.CENTER, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		settingsPanel.add(shipPanel);
		GridBagLayout gbl_shipPanel = new GridBagLayout();
		gbl_shipPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_shipPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		shipPanel.setLayout(gbl_shipPanel);

		lblDestroyer = new JLabel(JBattleships.game.getLocalization("GAME_DESTROYERS"));
		lblDestroyer.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblDestroyer = new GridBagConstraints();
		gbc_lblDestroyer.weighty = 1.0;
		gbc_lblDestroyer.weightx = 1.0;
		gbc_lblDestroyer.fill = GridBagConstraints.BOTH;
		gbc_lblDestroyer.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestroyer.gridx = 0;
		gbc_lblDestroyer.gridy = 0;
		shipPanel.add(lblDestroyer, gbc_lblDestroyer);

		destroyerSpinner = new JSpinner();
		destroyerSpinner.setFont(JBSGUI.MAIN_FONT);
		destroyerSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(3), Integer.valueOf(1)));
		GridBagConstraints gbc_destroyerSpinner = new GridBagConstraints();
		gbc_destroyerSpinner.weighty = 1.0;
		gbc_destroyerSpinner.weightx = 1.0;
		gbc_destroyerSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_destroyerSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_destroyerSpinner.gridx = 1;
		gbc_destroyerSpinner.gridy = 0;
		shipPanel.add(destroyerSpinner, gbc_destroyerSpinner);

		lblFrigate = new JLabel(JBattleships.game.getLocalization("GAME_FRIGATES"));
		lblFrigate.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblFrigate = new GridBagConstraints();
		gbc_lblFrigate.weighty = 1.0;
		gbc_lblFrigate.weightx = 1.0;
		gbc_lblFrigate.fill = GridBagConstraints.BOTH;
		gbc_lblFrigate.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrigate.gridx = 0;
		gbc_lblFrigate.gridy = 1;
		shipPanel.add(lblFrigate, gbc_lblFrigate);

		frigateSpinner = new JSpinner();
		frigateSpinner.setFont(JBSGUI.MAIN_FONT);
		frigateSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(3), Integer.valueOf(1)));
		GridBagConstraints gbc_frigateSpinner = new GridBagConstraints();
		gbc_frigateSpinner.weighty = 1.0;
		gbc_frigateSpinner.weightx = 1.0;
		gbc_frigateSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_frigateSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_frigateSpinner.gridx = 1;
		gbc_frigateSpinner.gridy = 1;
		shipPanel.add(frigateSpinner, gbc_frigateSpinner);

		lblCorvette = new JLabel(JBattleships.game.getLocalization("GAME_SUBMARINES"));
		lblCorvette.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblCorvette = new GridBagConstraints();
		gbc_lblCorvette.weighty = 1.0;
		gbc_lblCorvette.weightx = 1.0;
		gbc_lblCorvette.fill = GridBagConstraints.BOTH;
		gbc_lblCorvette.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorvette.gridx = 0;
		gbc_lblCorvette.gridy = 2;
		shipPanel.add(lblCorvette, gbc_lblCorvette);

		corvetteSpinner = new JSpinner();
		corvetteSpinner.setFont(JBSGUI.MAIN_FONT);
		corvetteSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(3), Integer.valueOf(1)));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.weighty = 1.0;
		gbc_spinner_2.weightx = 1.0;
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 2;
		shipPanel.add(corvetteSpinner, gbc_spinner_2);

		lblSubmarines = new JLabel(JBattleships.game.getLocalization("GAME_CORVETTES"));
		lblSubmarines.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblSubmarines = new GridBagConstraints();
		gbc_lblSubmarines.weighty = 1.0;
		gbc_lblSubmarines.weightx = 1.0;
		gbc_lblSubmarines.fill = GridBagConstraints.BOTH;
		gbc_lblSubmarines.insets = new Insets(0, 0, 0, 5);
		gbc_lblSubmarines.gridx = 0;
		gbc_lblSubmarines.gridy = 3;
		shipPanel.add(lblSubmarines, gbc_lblSubmarines);

		subSpinner = new JSpinner();
		subSpinner.setFont(JBSGUI.MAIN_FONT);
		subSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(3), Integer.valueOf(1)));
		GridBagConstraints gbc_subSpinner = new GridBagConstraints();
		gbc_subSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_subSpinner.weighty = 1.0;
		gbc_subSpinner.weightx = 1.0;
		gbc_subSpinner.gridx = 1;
		gbc_subSpinner.gridy = 3;
		shipPanel.add(subSpinner, gbc_subSpinner);
	}

}
