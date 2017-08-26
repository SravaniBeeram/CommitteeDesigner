package com.team7;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface SearchDisplay {
	
	// sends these parameters to the query builder
	public List<String> search(List<SearchParameter> searchFilter) throws SQLException,IOException;
	
	// gets a list of authors and sends email ID's to all users , 
	// with the list of the final committee
	public String sendEmail(Set<String> authors, String userName) throws SQLException,IOException;

	// details of the Author
	public ResultSet candidateDetails(Set<String> authors) throws SQLException,IOException;
}
