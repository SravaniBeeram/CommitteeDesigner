package com.team7;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

// An interface for the parsing of the files in the committees folder.
public interface Committees {

	public String ParseFiles(File textFile) throws IOException,SQLException;
}
