/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Kevin Kuegler
 * @version 1.00
 */
public class SHA256Generator {

	private MessageDigest md;

	/**
	 * 
	 */
	public SHA256Generator() {
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates a SHA-256 hash from the given file.
	 * @param file The filepath
	 * @return The SHA-256 hash
	 */
	public String generateSHA256(String file) {
		try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
			final byte[] buffer = new byte[1024];
			for (int read = 0; (read = is.read(buffer)) != -1;) {
				md.update(buffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	    byte[] mdbytes = md.digest();
	    
	    //convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();
	}
	
	/**
	 * Generates a SHA-256 hash from the given file.
	 * @param file The file
	 * @return The SHA-256 hash
	 */
	public String generateSHA256(File file) {
		try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
			final byte[] buffer = new byte[1024];
			for (int read = 0; (read = is.read(buffer)) != -1;) {
				md.update(buffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	    byte[] mdbytes = md.digest();
	    
	    //convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();
	}
	
	public static void main(String[] args) {
		SHA256Generator gen = new SHA256Generator();
		String s = gen.generateSHA256("Data/Resources.cfg");
		System.out.println(s);
	}

}
