package com.team7.abstractDesignFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.team7.interfaces.ParseCsvFiles;
import com.team7.interfaces.ParseTextFile;
import com.team7.interfaces.ParseXml;
import com.team7.parsing.ImplementAuthorAffData;
import com.team7.parsing.ImplementHomePageData;
import com.team7.parsing.ImplementUniCountryData;
import com.team7.parsing.ImplementUniversityAuthorData;

/**
 * A factory for creating Csv objects.
 */
public class CsvFactory extends AbstractParseFactory {

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getXml(java.lang.String)
	 */
	@Override
	public
	ParseXml getXml(String xml) {		
		return null;
	}

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getCsv(java.lang.String)
	 */
	@Override
	public
	ParseCsvFiles getCsv(String csv) throws IOException, SQLException {
		if (csv == null) {
			return null;
		}
		
		//first condition is for parsing into database and second one is for testing
		if (csv.equals("input/generate-author-info.csv") || csv.equals("input/testUni.csv")) {
			return new ImplementUniversityAuthorData(new File(csv));			
			
		}
		//first condition is for parsing into database and second one is for testing
		else if (csv.equals("input/country-info.csv") || csv.equals("input/testCountry.csv")) {
			
			return new ImplementUniCountryData(new File(csv));
			
		}
		//first condition is for parsing into database and second one is for testing
		else if (csv.equals("input/faculty-affiliations.csv") || csv.equals("input/testAff.csv")) {
			
			return new ImplementAuthorAffData(new File(csv));			
		}
		//first condition is for parsing into database and second one is for testing
		else if (csv.equals("input/homepages.csv") || csv.equals("input/testHome.csv")) {
			
			return new ImplementHomePageData( new File (csv));
			
		}
		return null;
	}

	/* 
	 * @see com.team7.abstractDesignFactory.AbstractParseFactory#getText(java.lang.String)
	 */
	@Override
	public ParseTextFile getText(String txt) {
		
		return null;
	}


}
