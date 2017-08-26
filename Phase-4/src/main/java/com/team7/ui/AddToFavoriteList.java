package com.team7.ui;

import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.team7.parsing.ImplementSchemaDB;

/**
 * The Class AddToFavoriteList.
 * Adding each author selected to the favorite list
 */ 
public class AddToFavoriteList extends DefaultCellEditor {

	/** The button. */
	protected JButton button;

	/** The label. */
	private String label;

	/** The log. */
	LoginUI log = new LoginUI();
	
	/** The data. */
	String data;

	/** The is pushed. */
	private boolean isPushed;

	/**
	 * Instantiates a new adds the to favorite list.
	 *
	 * @param checkBox the check box
	 */
	public AddToFavoriteList(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
	}
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
 
	/* (non-Javadoc)
	 * @see javax.swing.DefaultCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	// extracts information about the row where the button is clicked
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();

		Object rowData = table.getValueAt(row, 0);
		data = rowData.toString();
		
		try {
			// adding the selected author to the favorites list
			ImplementSchemaDB db = new ImplementSchemaDB();
			db.insertIntoFavList(UIConstants.currentUser, UIConstants.currentUserConf, data);

		} catch (IOException | SQLException e) {
			log.messageShow("Already included in the list");
			return null;
		}
		button.setText(label);
		isPushed = true;
		return button;
	}


	/* (non-Javadoc)
	 * @see javax.swing.DefaultCellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		if (isPushed) {}
		return new String(label);
	}


}
