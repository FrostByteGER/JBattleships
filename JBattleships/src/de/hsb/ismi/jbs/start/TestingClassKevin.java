/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class TestingClassKevin {

	/**
	 * 
	 */
	public TestingClassKevin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Game g = new Game();
			JAXBContext jaxb = JAXBContext.newInstance(Game.class);
			Marshaller m = jaxb.createMarshaller();
			m.marshal(g, new File("Data/testsave.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
