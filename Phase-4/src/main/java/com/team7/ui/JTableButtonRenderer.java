package com.team7.ui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * The Class JTableButtonRenderer.
 * Renders each cell at the center
 */ 
public class JTableButtonRenderer extends JButton implements TableCellRenderer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new j table button renderer.
	 */
	public JTableButtonRenderer() {
		setOpaque(true);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (isSelected) {
		      setForeground(table.getSelectionForeground());
		      setBackground(table.getSelectionBackground());
		      setHorizontalAlignment(JLabel.CENTER);
		    } else {
		      setForeground(table.getForeground());
		      setBackground(UIManager.getColor("Button.background"));
		      setHorizontalAlignment(JLabel.CENTER);
		    }
		    setText((value == null) ? "" : value.toString());
		    return this;
	}

}
