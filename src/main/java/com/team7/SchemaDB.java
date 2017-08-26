package com.team7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface SchemaDB {


	// Creates database and tables
	public void dbSetUp() throws ClassNotFoundException, SQLException,IOException;

	// Create connection to RDBMS 
	public Connection getConnection() throws IOException;

	//Insert the objects created to Database
	public boolean insertData(Object object_name) throws SQLException,IOException;

}
