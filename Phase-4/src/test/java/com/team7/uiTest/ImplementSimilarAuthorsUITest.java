package com.team7.uiTest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.team7.queryEngine.ImplementQueryBuilder;
import com.team7.ui.SimilarAuthorsUI;
import com.team7.ui.UIConstants;

/**
 * The Class ImplementSimilarAuthorsUITest.
 */
public class ImplementSimilarAuthorsUITest {
	
	/** The u. */
	UIConstants u  = new UIConstants("shahbiyanta@gmail.com", "Conference Chair", "ECOOP");
	
	/** The similar. */
	SimilarAuthorsUI similar;
	
	/**
	 * Test sim auth.
	 * testing insertion of authors into similar authors table
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testSimAuth() throws SQLException, IOException {
		List<String> similarAuth = new ArrayList<String>();
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForSimilarAuthors("Aniruddha S. Gokhale");
		ResultSet similarResultSet = queryBuilderObject.sendQuery(query);
		
		while (similarResultSet.next()) {
			similarAuth.add(similarResultSet.getString(1));
		}
		
		Set<String> authors = new TreeSet<String>(similarAuth);
		similar = new SimilarAuthorsUI(authors);
	}
	

}
