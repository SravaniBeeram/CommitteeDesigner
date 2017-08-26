package com.team7.ui;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;


/**
 * The Class ButtonEditor.
 * This class renders buttons into the table
 */
public class ButtonEditor extends DefaultCellEditor {

	/** The button. */
	protected JButton button;

	/** The label. */
	private String label;

	/** The saved authors. */
	// A set which stores names of authors who are selected to view publication details
	static Set<String> savedAuthors = new HashSet<String>();

	/** The data. */
	String data;

	/** The is pushed. */
	private boolean isPushed;

	/**
	 * Instantiates a new button editor.
	 *
	 * @param checkBox the check box
	 */
	public ButtonEditor(JCheckBox checkBox) {
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
		makeList();

		button.setText(label);
		isPushed = true;
		return button;
	}

	/**
	 * Make list.
	 *
	 * @return the sets the
	 */
	// adds the names of the authors in the set, corresponding to the button selected
	public Set<String> makeList() {
		savedAuthors.add(data);
		return savedAuthors;

	}
	
	/* (non-Javadoc)
	 * @see javax.swing.DefaultCellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		if (isPushed) {}
		return new String(label);
	}

}
