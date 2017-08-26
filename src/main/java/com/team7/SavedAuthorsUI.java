package com.team7;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SavedAuthorsUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Set<String> sendMail = new HashSet<String>();
	JButton btnNewButton;
	JButton btnSearch;
	JButton btnSendEmail;

	/**
	 * Create the frame.
	 * @throws SQLException  
	 */
	public SavedAuthorsUI(ResultSet result) {

		setVisible(true);
		setTitle("SAVED AUTHORS");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -22, 933, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 921, 101);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSavedAuthors = new JLabel("Saved Authors");
		lblSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSavedAuthors.setBounds(345, 17, 206, 16);
		panel.add(lblSavedAuthors);

		JLabel lblNewLabel = new JLabel("To select authors for the committee, click \"select\" beside the row");
		lblNewLabel.setBounds(242, 68, 433, 16);
		panel.add(lblNewLabel);

		btnSearch = new JButton("Search UI");
		btnSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearch.setBounds(620, 0, 117, 34);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonEditor.savedAuthors.clear();
				dispose();
				SearchUI search = new SearchUI();
				search.setVisible(true);
				search.setSize(1000, 600);
				search.setLocationRelativeTo(null);				
			}
		});

		btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonEditor.savedAuthors.clear();
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();
				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);
				log.setSize(1000,600);
				log.setLocationRelativeTo(null);
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBounds(749, 0, 117, 34);
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 119, 921, 357);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JTable table;
		try {
			table = new JTable(buildTableModel(result));
			JTableHeader header = table.getTableHeader();
			header.setDefaultRenderer(new HeaderRenderer(table));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);


			table.getColumn("Select").setCellRenderer(new JTableButtonRenderer());
			table.getColumn("Select").setCellEditor(
					new ButtonEditor(new JCheckBox()));

			table.setPreferredScrollableViewportSize(new Dimension(650, 350));
			JScrollPane scroll = new JScrollPane(table);
			setVisible(true);

			panel_1.add(scroll);
		} catch (SQLException e2) {
			
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 488, 921, 63);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		btnSendEmail = new JButton("Send Email");
		btnSendEmail.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMail = ButtonEditor.savedAuthors;
				Boolean flag = true;

				if (sendMail.size() == 0) {
					LoginUI log = new LoginUI();
					log.messageShow("Please select some authors for your committee");
					flag = false;
				}
				
				ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
				if (flag == true) {
					try {
						String res = searchDisplay.sendEmail(sendMail, LoginUI.currentUser);
                        if(res == "success"){
        					LoginUI log = new LoginUI();
        					log.messageShow("Email sent successfully");
                        }
					}  catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						
					}
				}
			}
		});
		btnSendEmail.setBounds(383, 13, 155, 29);
		panel_2.add(btnSendEmail);


	}

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();

		int columnCount = metaData.getColumnCount();

		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		columnNames.add("Select");

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));     
			}
			vector.add("select");
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);
	}
}
