package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The Interface ParseTextFile.
 * An interface for the parsing of the files in the committees folder.
 */
public interface ParseTextFile {

	/**
	 * Parses the text.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	public String parseText() throws IOException,SQLException;
}
