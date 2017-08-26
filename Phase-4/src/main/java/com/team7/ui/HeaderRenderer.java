package com.team7.ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * The Class HeaderRenderer.
 * This class is used to render the headers of the table in the center
 */
public class HeaderRenderer implements TableCellRenderer {

	/** The renderer. */
	DefaultTableCellRenderer renderer;

	/**
	 * Instantiates a new header renderer.
	 *
	 * @param table the table
	 */
	public HeaderRenderer(JTable table) {
		renderer = (DefaultTableCellRenderer)
				table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
	}


	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return renderer.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, column);
	}

}
