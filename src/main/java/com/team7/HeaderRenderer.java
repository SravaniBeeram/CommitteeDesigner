package com.team7;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

// This class is used to render the headers of the table in the center
public class HeaderRenderer implements TableCellRenderer {

	DefaultTableCellRenderer renderer;

	public HeaderRenderer(JTable table) {
		renderer = (DefaultTableCellRenderer)
				table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
	}


	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return renderer.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, column);
	}

}
