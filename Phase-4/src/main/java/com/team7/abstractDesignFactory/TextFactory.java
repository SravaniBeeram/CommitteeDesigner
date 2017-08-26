package com.team7.abstractDesignFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import com.team7.interfaces.ParseCsvFiles;
import com.team7.interfaces.ParseTextFile;
import com.team7.interfaces.ParseXml;
import com.team7.parsing.ImplementCommittees;


/**
 * A factory for creating Text objects.
 */
public class TextFactory extends AbstractParseFactory {

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getXml(java.lang.String)
	 */
	@Override
	public ParseXml getXml(String xml) throws JAXBException, SQLException, IOException {
		return null;
	}

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getCsv(java.lang.String)
	 */
	@Override
	public ParseCsvFiles getCsv(String csv) throws IOException, SQLException {
		return null;
	}

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getText(java.lang.String)
	 */
	@Override
	public ParseTextFile getText(String txt) {
		
		if (txt == null) {
			return null;
		}
		
		//first condition is for parsing into database and second one is for testing
		if (txt.equals("input/committees/") || txt.equals("input/committes")) {			
			return new ImplementCommittees(new File(txt));			
		}
		
		return null;
	}

}
