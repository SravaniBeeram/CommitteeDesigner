package com.team7.uiTest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;

import org.junit.Test;

import com.team7.queryEngine.ImplementQueryBuilder;
import com.team7.ui.AuthorPublicationDetailsUI;
import com.team7.ui.ButtonEditor;
import com.team7.ui.UIConstants;

/**
 * The Class ImplementPubDetailsUITest.
 */
public class ImplementPubDetailsUITest {

	/** The u. */
	UIConstants u  = new UIConstants("shah.bi@husky.neu.edu", "Program Chair", "ECOOP");

	/** The authors. */
	Set<String> authors = new HashSet<String> (Arrays.asList("Shahar Maoz"));

	/** The saved. */
	AuthorPublicationDetailsUI saved;

	/** The button. */
	ButtonEditor button = new ButtonEditor(new JCheckBox());

	/**
	 * Test logout button.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testLogoutButton() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);

		saved = new AuthorPublicationDetailsUI(rs);

		saved.btnNewButton.doClick();
		saved.dispose();

	}

	/**
	 * Test search button.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testSearchButton() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);

		saved = new AuthorPublicationDetailsUI(rs);		
		saved.btnSearch.doClick();
		saved.dispose();
	}

	/**
	 * Test fav list.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFavList() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);

		saved = new AuthorPublicationDetailsUI(rs);
		saved.btnFav.doClick();
		saved.dispose();
	}

	/**
	 * Test candidate list.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testCandidateList() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);

		saved = new AuthorPublicationDetailsUI(rs);
		saved.btnCandidatesList.doClick();
		saved.dispose();
	}
}
