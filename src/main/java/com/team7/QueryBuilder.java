package com.team7;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryBuilder {

	// createQuery method receives a List for search parameters
	// Returns a List of queries which will be joined for the final result
	public List<String> createQuery(List<SearchParameter> searchParam);

	//validate the query and look for evil inputs for enhanced security 	
	public boolean validateQuery(List<SearchParameter> searchParam);

	// send the query to Database
	public ResultSet sendQuery(String searchQuery) throws SQLException,IOException;
}
