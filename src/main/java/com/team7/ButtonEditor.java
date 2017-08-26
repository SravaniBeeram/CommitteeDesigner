package com.team7;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

// This class renders buttons into the table
public class ButtonEditor extends DefaultCellEditor {

	protected JButton button;

	private String label;

	// A set which stores names of authors who have been saved or selected
	static Set<String> savedAuthors = new HashSet<String>();

	String data;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

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

	// adds the names of the authors in the set, corresponding to the button selected
	public Set<String> makeList() {
		// TODO Auto-generated method stub
		savedAuthors.add(data);
		return savedAuthors;

	}

	public Object getCellEditorValue() {
		if (isPushed) {}
		return new String(label);
	}

}
