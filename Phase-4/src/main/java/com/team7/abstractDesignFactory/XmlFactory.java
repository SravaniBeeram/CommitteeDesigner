package com.team7.abstractDesignFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import com.team7.interfaces.ParseCsvFiles;
import com.team7.interfaces.ParseTextFile;
import com.team7.interfaces.ParseXml;
import com.team7.parsing.ImplementParseDatabase;

/**
 * A factory for creating Xml objects.
 */
public class XmlFactory extends AbstractParseFactory {

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getXml(java.lang.String)
	 */
	public ParseXml getXml(String xml) throws JAXBException, SQLException, IOException {
		
		if (xml == null) {
			return null;
		}
		
		//first condition is for parsing into database and second one is for testing
		if (xml.equals("input/dblp.xml") || xml.equals("msdxml.xml")) {
			
			return new ImplementParseDatabase(new File (xml));			
		}
		
		return null;
	}

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getCsv(java.lang.String)
	 */
	public ParseCsvFiles getCsv(String csv) {
		return null;
	}


	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getText(java.lang.String)
	 */
	public ParseTextFile getText(String txt) {
		return null;
	}
}
