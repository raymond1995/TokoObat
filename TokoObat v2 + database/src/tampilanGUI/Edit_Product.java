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

public class Edit_Product extends JFrame {

	Connection koneksi;

	Container kontainer = getContentPane();

	JPanel panel_editproduct = new JPanel();
	JPanel panel_judul = new JPanel();

	JLabel judul = new JLabel("<html><font color='white'>EDIT PRODUCT</font></html>");
	JLabel lbl_id = new JLabel("ID Product : ");
	JLabel lbl_nama = new JLabel("Nama Product : ");
	JLabel lbl_idsup = new JLabel("ID Supplier : ");
	JLabel lbl_harga = new JLabel("Harga : ");
	JLabel lbl_stock = new JLabel("Stock : ");
	JTextField txt_id = new JTextField(20);
	JTextField txt_nama = new JTextField(20);
	JTextField txt_idsup = new JTextField(20);
	JTextField txt_harga = new JTextField(20);
	JTextField txt_stock = new JTextField(5);
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");

	public Edit_Product(String title, String idx, JTable table, int row) {
		super(title);
		super.setSize(540, 480);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel_editproduct.setLayout(new GridBagLayout());
		panel_judul.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		kontainer.setLayout(new FlowLayout());

		panel_editproduct.setBorder(BorderFactory.createTitledBorder(""));

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_judul.add(judul, gbc);
		panel_judul.setBackground(Color.BLUE);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_editproduct.add(lbl_id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panel_editproduct.add(txt_id, gbc);
		txt_id.setEnabled(false);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel_editproduct.add(lbl_nama, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel_editproduct.add(txt_nama, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel_editproduct.add(lbl_idsup, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel_editproduct.add(txt_idsup, gbc);
		txt_idsup.setEnabled(false);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel_editproduct.add(lbl_harga, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel_editproduct.add(txt_harga, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panel_editproduct.add(lbl_stock, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		panel_editproduct.add(txt_stock, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		save.setPreferredSize(new Dimension(170, 30));
		panel_editproduct.add(save, gbc);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProduct();
				table.setValueAt(txt_nama.getText(), row, 1);
				table.setValueAt(txt_harga.getText(), row, 3);
				table.setValueAt(txt_stock.getText(), row, 4);
				txt_id.setText("");
				txt_nama.setText("");
				txt_idsup.setText("");
				txt_harga.setText("");
				txt_stock.setText("");
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 5;
		cancel.setPreferredSize(new Dimension(170, 30));
		panel_editproduct.add(cancel, gbc);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setText("");
				txt_nama.setText("");
				txt_idsup.setText("");
				txt_harga.setText("");
				txt_stock.setText("");
				setVisible(false);
			}
		});

		kontainer.add(panel_judul);
		kontainer.add(panel_editproduct);
		tampilkanData(idx);
	}

	public void updateProduct() {
		String id_product = txt_id.getText();
		String nama_product = txt_nama.getText();
		String id_supplier = txt_idsup.getText();
		//int harga = Integer.parseInt(txt_harga.getText());
		//int stock = Integer.parseInt(txt_stock.getText());
		String harga = txt_harga.getText();
		String stock = txt_stock.getText();

		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (id_product.equals("") || nama_product.equals("")
				|| id_supplier.equals("") || harga.equals("") || stock.equals("")) {
			JOptionPane.showMessageDialog(null, "Data Masih Ada Yang Kosong!");
		} else {

			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt
						.executeQuery("UPDATE PRODUCT SET nama='"+nama_product+"', harga='"+harga+"', stock='"+stock+"' WHERE id='"+id_product+"'");
				stmt.close();
				JOptionPane.showMessageDialog(null, "Berhasil Mengubah Data");
			}

			catch (SQLException x) {

				x.printStackTrace();
			}

		}
	}

	public void tampilkanData(String idx) {
		String id="";
		String nama;
		int harga;
		String idsup="";
		int stock;
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
					.executeQuery("SELECT * FROM PRODUCT WHERE id ='" + idx
							+ "'");
			while (rs.next()) {
				id = rs.getString(1);
				nama = rs.getString(2);
				idsup = rs.getString(3);
				harga = rs.getInt(4);
				stock = rs.getInt(5);
			}
			txt_id.setText(id);
			txt_idsup.setText(idsup);
			stmt.close();
		}

		catch (SQLException x) {

			x.printStackTrace();

		}

	}
}
