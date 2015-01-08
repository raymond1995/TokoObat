package tampilanGUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Edit_Supplier extends JFrame {
	
	Connection koneksi;
	
	Container kontainer = getContentPane();
	
	JPanel panel_editsupplier = new JPanel();
	JPanel panel_judul = new JPanel();
	
	JLabel judul = new JLabel("EDIT SUPPLIER");
	JLabel lbl_id = new JLabel("ID Supplier : ");
	JLabel lbl_nama = new JLabel("Nama Supplier : ");
	JTextField txt_id = new JTextField(20);
	JTextField txt_nama = new JTextField(20);
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	
	public Edit_Supplier(String title, String idx, JTable table, int row){
		super(title);
		super.setSize(540, 480);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_editsupplier.setLayout(new GridBagLayout());
		panel_judul.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		kontainer.setLayout(new FlowLayout());
		
		panel_editsupplier.setBorder(BorderFactory.createTitledBorder(""));
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_judul.add(judul, gbc);
		panel_judul.setBackground(Color.BLUE);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_editsupplier.add(lbl_id, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel_editsupplier.add(txt_id, gbc);
		txt_id.setEnabled(false);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel_editsupplier.add(lbl_nama, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel_editsupplier.add(txt_nama, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		save.setPreferredSize(new Dimension(170, 30));
		panel_editsupplier.add(save, gbc);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSupplier();
				table.setValueAt(txt_nama.getText(), row, 1);
				
				txt_id.setText("");
				txt_nama.setText("");
			}
		});
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		cancel.setPreferredSize(new Dimension(170, 30));
		panel_editsupplier.add(cancel, gbc);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setText("");
				txt_nama.setText("");
				setVisible(false);
			}
		});
		
		kontainer.add(panel_judul);
		kontainer.add(panel_editsupplier);
		tampilkanData(idx);
	}
	
	public void updateSupplier() {
		String idsup = txt_id.getText();
		String namasup = txt_nama.getText();

		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (idsup.equals("") || namasup.equals("")) {
			JOptionPane.showMessageDialog(null, "Data Masih Ada Yang Kosong!");
		} else {

			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt
						.executeQuery("UPDATE SUPPLIER SET nama='"+namasup+"' WHERE id = '"+idsup+"'");
				stmt.close();
				JOptionPane.showMessageDialog(null, "Berhasil Mengubah Data!");
			}

			catch (SQLException x) {

				x.printStackTrace();
			}

		}
	}
	
	public void tampilkanData(String idx) {
		String idsup="";
		String namasup;
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM SUPPLIER WHERE id ='" + idx
							+ "'");
			while (rs.next()) {
				idsup = rs.getString(1);
				namasup = rs.getString(2);

			}
			txt_id.setText(idsup);
			stmt.close();
		}

		catch (SQLException x) {

			x.printStackTrace();

		}

	}

}

