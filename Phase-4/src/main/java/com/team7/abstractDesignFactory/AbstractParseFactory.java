package com.team7.abstractDesignFactory;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import com.team7.interfaces.ParseCsvFiles;
import com.team7.interfaces.ParseTextFile;
import com.team7.interfaces.ParseXml;

/**
 * A factory for creating AbstractParse objects.
 */
public abstract class AbstractParseFactory {

	/**
	 * Gets the xml.
	 *
	 * @param xml the xml
	 * @return the xml
	 * @throws JAXBException the JAXB exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract ParseXml getXml(String xml) throws JAXBException, SQLException, IOException;
	
	/**
	 * Gets the csv.
	 *
	 * @param csv the csv
	 * @return the csv
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	public abstract ParseCsvFiles getCsv(String csv) throws IOException, SQLException;
	
	/**
	 * Gets the text.
	 *
	 * @param txt the txt
	 * @return the text
	 */
	public abstract ParseTextFile getText(String txt);
	
}
