/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.hsb.ismi.jbs.engine.utility.DebugListener;
import de.hsb.ismi.jbs.engine.utility.DebugLog;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class DebugFrame extends JFrame implements DebugListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4181512919100558622L;
	
	private JPanel contentPane     = new JPanel(new BorderLayout());
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JTextArea infoArea     = new JTextArea();
	private JTextArea warningArea  = new JTextArea();
	private JTextArea errorArea    = new JTextArea();
	private JPanel buttonPanel     = new JPanel(new FlowLayout());
	private JButton btnClear       = new JButton("Clear Log");
	private JButton btnSave        = new JButton("Save Log");
	
	private static final Color infoColor    = Color.BLACK;
	private static final Color warningColor = Color.ORANGE;
	private static final Color errorColor   = Color.RED;
	
	public DebugFrame(boolean visible){
		super(DebugLog.NAME + " " + DebugLog.VERSION);
		setContentPane(contentPane);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 500, 300);
		setLocationRelativeTo(null);
		initGUI();
		setVisible(visible);
	}
	
	private void initGUI(){
		infoArea.setForeground(infoColor);
		infoArea.setEditable(false);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		
		warningArea.setForeground(warningColor);
		warningArea.setEditable(false);
		warningArea.setLineWrap(true);
		warningArea.setWrapStyleWord(true);
		
		errorArea.setForeground(errorColor);
		errorArea.setEditable(false);
		errorArea.setLineWrap(true);
		errorArea.setWrapStyleWord(true);
		
		tabbedPane.add("Info-Log", infoArea);
		tabbedPane.add("Warning-Log", warningArea);
		tabbedPane.add("Error-Log", errorArea);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		btnClear.addActionListener(e -> {
			int index = tabbedPane.getSelectedIndex();
			if(index == 0){
				infoArea.setText("");
			}else if(index == 1){
				warningArea.setText("");
			}else if(index == 2){
				errorArea.setText("");
			}
		});
		
		btnSave.addActionListener(e -> {
			JFileChooser jfc = new JFileChooser();
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.addChoosableFileFilter(new FileNameExtensionFilter(DebugLog.getFileExtension(), DebugLog.getFileExtension().substring(1)));
			int choice = jfc.showSaveDialog(null);
			if(jfc.getSelectedFile() != null && choice == JFileChooser.APPROVE_OPTION){
				boolean securitycheck = approveSelection(jfc);
				String savepath = jfc.getSelectedFile().toString();
				if(!savepath.endsWith(DebugLog.getFileExtension())){
					savepath += DebugLog.getFileExtension();
				}
				if(securitycheck == true){
					try {
						int index = tabbedPane.getSelectedIndex();
						if(index == 0){
							DebugLog.writeInfolog(savepath, true);
							infoArea.setText("");
						}else if(index == 1){
							DebugLog.writeWarninglog(savepath, true);
							warningArea.setText("");
						}else if(index == 2){
							DebugLog.writeErrorlog(savepath, true);
							errorArea.setText("");
						}
						JOptionPane.showMessageDialog(DebugFrame.this,"Log Saved!");
					} catch (IOException ioe) {
						JOptionPane.showMessageDialog(DebugFrame.this,"Error while saving!");
					}
				}else{
					return;
				}
			}
		});
		
		buttonPanel.add(btnClear);
		buttonPanel.add(btnSave);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Creates a new approve selection screen to prevent unauthorized or mistaken file overwriting.
	 * @param jfc The JFileChooser.
	 * @return returns the choice made by the user.
	 */
	private boolean approveSelection(JFileChooser jfc){
        File f = jfc.getSelectedFile();
        if(f.exists() && jfc.getDialogType() == JFileChooser.SAVE_DIALOG){
            int result = JOptionPane.showConfirmDialog(DebugFrame.this,"The file already exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
                case JOptionPane.YES_OPTION:
                	jfc.approveSelection();
                    return true;
                case JOptionPane.NO_OPTION:
                    return false;
                case JOptionPane.CLOSED_OPTION:
                    return false;
                case JOptionPane.CANCEL_OPTION:
                	jfc.cancelSelection();
                    return false;
            }
        }else{
        	return true;
        }
		return false;
    }

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.gui.DebugListener#addInfo(java.lang.String)
	 */
	@Override
	public void addInfo(String info) {
		if(infoArea.getLineCount() < DebugLog.getBufferlimit()){
			infoArea.append(info + System.lineSeparator());
		}else{
			infoArea.setText("");
			infoArea.append(info + System.lineSeparator());
		}
	}

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.gui.DebugListener#addWarning(java.lang.String)
	 */
	@Override
	public void addWarning(String warning) {
		if(warningArea.getLineCount() < DebugLog.getBufferlimit()){
			warningArea.append(warning + System.lineSeparator());
		}else{
			warningArea.setText("");
			warningArea.append(warning + System.lineSeparator());
		}
	}

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.gui.DebugListener#addError(java.lang.StackTraceElement)
	 */
	@Override
	public void addError(Exception error) {
		if(errorArea.getLineCount() < DebugLog.getBufferlimit()){
			errorArea.append(error.getMessage() + System.lineSeparator());
		}else{
			errorArea.setText("");
			errorArea.append(error.getMessage() + System.lineSeparator());
		}
	}
}