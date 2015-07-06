/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class BattleLogPanel extends JPanel {
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel logPanel = new JPanel();
	private JTextArea logArea = new JTextArea();

	/**
	 * Create the panel.
	 */
	public BattleLogPanel() {
		setLayout(new BorderLayout(0, 0));
		
		add(tabbedPane);
		
		tabbedPane.addTab(JBattleships.game.getLocalization("GAME_TITLE_BATTLE_LOG"), null, logPanel, null);
		logPanel.setLayout(new BorderLayout(0, 0));
		
		logArea.setEditable(false);
		logPanel.add(logArea);

	}
	
	/**
	 * Adds the given text to the battlelog
	 * @param text Text to add
	 */
	public void addTextToBattleLog(String text){
		logArea.append(text + System.lineSeparator());
	}

}
