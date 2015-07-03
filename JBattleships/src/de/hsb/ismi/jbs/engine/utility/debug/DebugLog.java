/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;

import de.hsb.ismi.jbs.core.JBSCoreGame;

/**
 * Simple customizable debugging-class. Logs 3 different types of messages:<br>
 * - Simple Info-messages<br>
 * - Warnings<br>
 * - Exceptions.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class DebugLog {

	// DebugLog booleans
	private static boolean logInfos    = false;
	private static boolean logWarnings = false;
	private static boolean logErrors   = false;
	
	// Log Indicators
	private static final String DEBUG_LINE   = "DEBUGLOG: ";
	private static final String INFO_LINE    = ">>>>>>>>>>INFO<<<<<<<<<<";
	private static final String WARNING_LINE = ">>>>>>>>>>WARNING<<<<<<<<<<";
	private static final String ERROR_LINE   = ">>>>>>>>>>ERROR<<<<<<<<<<";
	
	// File Infos
	private static String logExtension      = ".log";
	private static String infoLogName       = "info";
	private static String warningLogName    = "warning";
	private static String errorLogName      = "error";
	
	// Outputstream
	private static PrintWriter outputStream = new PrintWriter(System.out);
	
	private static int bufferLimit = 255;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Y-M-d-H-m-s-S");
	
	// Logs
	private static ArrayList<String> infoLog             = new ArrayList<>(bufferLimit);
	private static ArrayList<String> warningLog          = new ArrayList<>(bufferLimit);
	private static ArrayList<Exception> errorLog         = new ArrayList<>(bufferLimit);
	
	// Excluded exceptions
	private static ArrayList<Exception> excludedExceptions = new ArrayList<>();
	
	// Debugger Info
	public static final String VERSION = "1.00";
	public static final String NAME    = "Debug-Logger";
	
	// DebugLog GUI
	private static DebugListener debugFrame = null;
	
	
	
	/**
	 * Empty constructor, we don't want objects for this class!
	 */
	private DebugLog(){
		
	}
	
	/**
	 * Sets the state of the infologging.
	 * @param toggle If true, logs infos, otherwise not.
	 */
	public static void setLogInfos(boolean toggle){
		logInfos = toggle;
	}
	
	/**
	 * Sets the state of the warninglogging.
	 * @param toggle If true, logs warnings, otherwise not.
	 */
	public static void setLogWarnings(boolean toggle){
		logWarnings = toggle;
	}
	
	/**
	 * Sets the state of the errorlogging.
	 * @param toggle If true, logs errors, otherwise not.
	 */
	public static void setLogErrors(boolean toggle){
		logErrors = toggle;
	}
	
	/**
	 * Returns the current state of the infologging.
	 * @return True if infos get logged, false if not.
	 */
	public static boolean getLogInfos(){
		return logInfos;
	}
	
	/**
	 * Returns the current state of the warninglogging.
	 * @return True if warnings get logged, false if not.
	 */
	public static boolean getLogWarnings(){
		return logWarnings;
	}
	
	/**
	 * Returns the current state of the errorlogging.
	 * @return True if errors get logged, false if not.
	 */
	public static boolean getLogErrors(){
		return logErrors;
	}
	
	/**
	 * Returns the current outputWriter.
	 * @return The current outputWriter.
	 */
	public static PrintWriter getOutputWriter(){
		return outputStream;
	}
	
	/**
	 * Sets the outputWriter to the given one.
	 * @param out The new outputWriter.
	 */
	public static void setOutputWriter(PrintWriter out){
		outputStream = out;
	}
	
	/**
	 * Sets the bufferlimit to the given one.
	 * @param limit The new bufferlimit.
	 * @throws IllegalArgumentException If limit <= 0.
	 */
	public static void setBufferlimit(int limit) throws IllegalArgumentException{
		if(bufferLimit <= 0){
			throw new IllegalArgumentException("Bufferlimit must be greater than 0!");
		}else{
			bufferLimit = limit;
		}
	}
	
	/**
	 * Returns the current bufferlimit.
	 * @return The bufferlimit.
	 */
	public static int getBufferlimit(){
		return bufferLimit;
	}
	
	/**
	 * Returns the current fileExtension.
	 * @return The fileextension.
	 */
	public static String getFileExtension(){
		return logExtension;
	}
	
	/**
	 * Sets the fileExtension.
	 * @param extension The fileextension to add.
	 */
	public static void setFileExtension(String extension){
		logExtension = extension;
	}
	
	/**
	 * Sets the debugFrame.
	 * @param frame The debugFrame to set.
	 */
	public static void setDebugFrame(JFrame frame){
		debugFrame = (DebugListener) frame;
	}
	
	/**
	 * @return the excludedExceptions
	 */
	public static final ArrayList<Exception> getExcludedExceptions() {
		return excludedExceptions;
	}

	/**
	 * @param excludedExceptions the excludedExceptions to set
	 */
	public static final void setExcludedExceptions(ArrayList<Exception> excludedExceptions) {
		DebugLog.excludedExceptions = excludedExceptions;
	}

	/**
	 * Adds the given exception to the excluded exceptions.
	 * @param e The exception to add.
	 */
	public static void addExcludedException(Exception e){
		if(!excludedExceptions.contains(e)){
			excludedExceptions.add(e);
		}
	}
	
	/**
	 * Removes the given exception from the excluded exceptions-list.
	 * @param eThe exception to remove.
	 */
	public static void removeExcludedException(Exception e){
		excludedExceptions.remove(e);
	}
	
	
	/**
	 * Logs the given info to the outputstream and buffers it. Also prints it to the gui.
	 * @param info The info to print and add.
	 */
	public static void logInfo(String info){
		if(logInfos){
			outputStream.println(DEBUG_LINE + INFO_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			outputStream.flush();
			addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			if(debugFrame != null){
				debugFrame.addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			}
		}
	}
	
	/**
	 * Logs the given info to the outputstream and buffers it. Also prints it to the gui.
	 * @param info The info to print and add.
	 */
	public static void logInfo(boolean info){
		if(logInfos){
			outputStream.println(DEBUG_LINE + INFO_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			outputStream.flush();
			addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			if(debugFrame != null){
				debugFrame.addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			}
		}
	}
	
	/**
	 * Logs the given info to the outputstream and buffers it. Also prints it to the gui.
	 * @param info The info to print and add.
	 */
	public static void logInfo(int info){
		if(logInfos){
			outputStream.println(DEBUG_LINE + INFO_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			outputStream.flush();
			addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			if(debugFrame != null){
				debugFrame.addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			}
		}
	}
	
	/**
	 * Logs the given info to the outputstream and buffers it. Also prints it to the gui.
	 * @param info The info to print and add.
	 */
	public static void logInfo(float info){
		if(logInfos){
			outputStream.println(DEBUG_LINE + INFO_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			outputStream.flush();
			addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			if(debugFrame != null){
				debugFrame.addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			}
		}
	}
	
	/**
	 * Logs the given info to the outputstream and buffers it. Also prints it to the gui.
	 * @param info The info to print and add.
	 */
	public static void logInfo(double info){
		if(logInfos){
			outputStream.println(DEBUG_LINE + INFO_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			outputStream.flush();
			addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			if(debugFrame != null){
				debugFrame.addInfo(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + info);
			}
		}
	}
	
	/**
	 * Logs the given warning to the outputstream and buffers it. Also prints it to the gui.
	 * @param warning The warning to print and add.
	 */
	public static void logWarning(String warning){
		if(logWarnings){
			outputStream.println(DEBUG_LINE + WARNING_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			outputStream.flush();
			addWarning(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			if(debugFrame != null){
				debugFrame.addWarning(DEBUG_LINE  + LocalDateTime.now().format(formatter) + " " + warning);
			}
		}
	}
	
	/**
	 * Logs the given warning to the outputstream and buffers it. Also prints it to the gui.
	 * @param warning The warning to print and add.
	 */
	public static void logWarning(boolean warning){
		if(logWarnings){
			outputStream.println(DEBUG_LINE + WARNING_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			outputStream.flush();
			addWarning(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			if(debugFrame != null){
				debugFrame.addWarning(DEBUG_LINE  + LocalDateTime.now().format(formatter) + " " + warning);
			}
		}
	}
	
	/**
	 * Logs the given warning to the outputstream and buffers it. Also prints it to the gui.
	 * @param warning The warning to print and add.
	 */
	public static void logWarning(int warning){
		if(logWarnings){
			outputStream.println(DEBUG_LINE + WARNING_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			outputStream.flush();
			addWarning(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			if(debugFrame != null){
				debugFrame.addWarning(DEBUG_LINE  + LocalDateTime.now().format(formatter) + " " + warning);
			}
		}
	}
	
	/**
	 * Logs the given warning to the outputstream and buffers it. Also prints it to the gui.
	 * @param warning The warning to print and add.
	 */
	public static void logWarning(float warning){
		if(logWarnings){
			outputStream.println(DEBUG_LINE + WARNING_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			outputStream.flush();
			addWarning(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			if(debugFrame != null){
				debugFrame.addWarning(DEBUG_LINE  + LocalDateTime.now().format(formatter) + " " + warning);
			}
		}
	}
	
	/**
	 * Logs the given warning to the outputstream and buffers it. Also prints it to the gui.
	 * @param warning The warning to print and add.
	 */
	public static void logWarning(double warning){
		if(logWarnings){
			outputStream.println(DEBUG_LINE + WARNING_LINE);
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			outputStream.flush();
			addWarning(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + warning);
			if(debugFrame != null){
				debugFrame.addWarning(DEBUG_LINE  + LocalDateTime.now().format(formatter) + " " + warning);
			}
		}
	}
	
	/**
	 * Logs the given error to the outputstream and buffers it. Also prints it to the gui.
	 * @param e The exception to print and add.
	 */
	public static void logError(Exception e){
		if(logErrors && !excludedExceptions.contains(e)){
			outputStream.println(DEBUG_LINE + LocalDateTime.now().format(formatter) + " " + ERROR_LINE);
			outputStream.print(DEBUG_LINE);
			e.printStackTrace(outputStream);
			outputStream.flush();
			addError(e);
			if(debugFrame != null){
				debugFrame.addError(e);
			}
		}
	}
	
	/**
	 * Adds an info the the buffer.
	 * @param info The info to add.
	 */
	private static void addInfo(String info){
		if(infoLog.size() < bufferLimit){
			infoLog.add(info);
		}
	}
	
	/**
	 * Adds a warning to the buffer.
	 * @param warning The warning to add.
	 */
	private static void addWarning(String warning){
		if(warningLog.size() < bufferLimit){
			warningLog.add(warning);
		}
	}
	
	/**
	 * Adds an error to the buffer.
	 * @param error The exception to add.
	 */
	private static void addError(Exception error){
		if(errorLog.size() < bufferLimit){
			errorLog.add(error);
		}
	}
	
	/**
	 * Clears the infoLog.
	 */
	public static void flushInfoLog(){
		infoLog.clear();
	}
	
	/**
	 * Clears the warningLog.
	 */
	public static void flushWarningLog(){
		warningLog.clear();
	}
	
	/**
	 * Clears the errorLog.
	 */
	public static void flushErrorLog(){
		errorLog.clear();
	}
	
	/**
	 * Writes the buffered infos to the specified file.
	 * @param path The filepath. If null or empty, a new name will be generated.
	 * @param flushBuffer Flushes the exception-buffer after writing.
	 * @throws IOException Indicates an IO error during writing the file.
	 */
	public static void writeInfolog(String path, boolean flushBuffer) throws IOException{
		if(path == null || path.isEmpty()){
			path = JBSCoreGame.LOG_PATH + LocalDateTime.now().format(formatter) + "_" + infoLogName + logExtension;
		}
		if(infoLog.size() == 0){
			return;
		}
		File log = new File(path);
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(log)), flushBuffer);
		for(String s : infoLog){
			writer.println(s);
		}
		if(flushBuffer){
			infoLog.clear();
		}
		writer.close();
	}
	
	/**
	 * Writes the buffered warnings to the specified file.
	 * @param path The filepath. If null or empty, a new name will be generated.
	 * @param flushBuffer Flushes the exception-buffer after writing.
	 * @throws IOException Indicates an IO error during writing the file.
	 */
	public static void writeWarninglog(String path, boolean flushBuffer) throws IOException{
		if(path == null || path.isEmpty()){
			path = JBSCoreGame.LOG_PATH + LocalDateTime.now().format(formatter) + "_"  + warningLogName + logExtension;
		}
		if(warningLog.size() == 0){
			return;
		}
		File log = new File(path);
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(log)), flushBuffer);
		for(String s : warningLog){
			writer.println(s);
		}
		if(flushBuffer){
			warningLog.clear();
		}
		writer.close();
	}
	
	/**
	 * Writes the buffered exceptions to the specified file.
	 * TODO: Add timestamp!
	 * @param path The filepath. If null or empty, a new name will be generated.
	 * @param flushBuffer Flushes the exception-buffer after writing.
	 * @throws IOException Indicates an IO error during writing the file.
	 */
	public static void writeErrorlog(String path, boolean flushBuffer) throws IOException{
		if(path == null || path.isEmpty()){
			path = JBSCoreGame.LOG_PATH + LocalDateTime.now().format(formatter) + "_"  + errorLogName + logExtension;
		}
		if(errorLog.size() == 0){
			return;
		}
		File log = new File(path);
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(log)), flushBuffer);
		for(Exception e : errorLog){
			e.printStackTrace(writer);
		}
		if(flushBuffer){
			errorLog.clear();
		}
		writer.close();
	}
}
