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

public class Edit_User extends JFrame {
	
	Connection koneksi;
	
	Container kontainer = getContentPane();
	
	JPanel panel_edituser = new JPanel();
	JPanel panel_judul = new JPanel();
	JLabel judul = new JLabel("EDIT USER");
	JLabel lbl_username = new JLabel("Username : ");
	JLabel lbl_password = new JLabel("Password : ");
	JLabel lbl_akses = new JLabel("Hak Akses : ");
	JTextField txt_username = new JTextField(20);
	JTextField txt_password = new JTextField(20);
	String [] akses = {"Administrator","Kasir"};
	JComboBox cbo_akses = new JComboBox(akses);
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	
	public Edit_User(String title, String idx, JTable table, int row){
		super(title);
		super.setSize(540, 480);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_edituser.setLayout(new GridBagLayout());
		panel_judul.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		kontainer.setLayout(new FlowLayout());
		
		panel_edituser.setBorder(BorderFactory.createTitledBorder(""));
		
		cbo_akses.setSelectedItem(2);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_judul.add(judul, gbc);
		panel_judul.setBackground(Color.BLUE);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_edituser.add(lbl_username, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel_edituser.add(txt_username, gbc);
		txt_username.enable(false);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel_edituser.add(lbl_password, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel_edituser.add(txt_password, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel_edituser.add(lbl_akses, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel_edituser.add(cbo_akses, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		save.setPreferredSize(new Dimension(170, 30));
		panel_edituser.add(save, gbc);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUser();
				table.setValueAt(txt_password.getText(), row, 1);
				table.setValueAt(cbo_akses.getSelectedItem().toString(), row, 2);
				
				txt_username.setText("");
				txt_password.setText("");
			}
		});
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		cancel.setPreferredSize(new Dimension(170, 30));
		panel_edituser.add(cancel, gbc);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_username.setText("");
				txt_password.setText("");
				setVisible(false);
			}
		});
		
		kontainer.add(panel_judul);
		kontainer.add(panel_edituser);
		tampilkanData(idx);
	}
	
	public void updateUser() {
		String username = txt_username.getText();
		String password = txt_password.getText();
		String akses = cbo_akses.getSelectedItem().toString();

		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (username.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(null, "Data Masih Ada Yang Kosong!");
		} else {

			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt
						.executeQuery("UPDATE ADMIN SET password='"+password+"', hak_akses='"+akses+"' WHERE username='"+username+"'");
				stmt.close();
				JOptionPane.showMessageDialog(null, "Berhasil Mengubah Data!");
			}

			catch (SQLException x) {

				x.printStackTrace();
			}

		}
	}
	
	public void tampilkanData(String idx) {
		String username="";
		String password;
		String akses;
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
					.executeQuery("SELECT * FROM ADMIN WHERE username ='" + idx
							+ "'");
			while (rs.next()) {
				username = rs.getString(1);
				password = rs.getString(2);
				akses = rs.getString(3);

			}
			txt_username.setText(username);
			stmt.close();
		}

		catch (SQLException x) {

			x.printStackTrace();

		}

	}

}
