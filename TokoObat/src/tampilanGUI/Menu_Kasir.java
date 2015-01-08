package tampilanGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu_Kasir extends JFrame {
	int a, b, c, d, e;
	int f = 0 ;
	int stockdb;
	int sisastock;

	Connection koneksi;

	JMenuBar menuBar = new JMenuBar();

	JMenu menuKasir = new JMenu("Transaksi");

	JMenuItem miTambahTransaksi = new JMenuItem("Tambah Transaksi");
	JMenuItem miLogOut = new JMenuItem("Log Out");

	Container kontainer = getContentPane();

	JPanel panelTransaksi = new JPanel();

	GridBagConstraints gbc = new GridBagConstraints();
	public String nama;
	JLabel judul = new JLabel("<html><h1>TOKO OBAT JAYA</h1></html>");
	JLabel lbluangkembalian = new JLabel();
	JLabel lblbayar = new JLabel("Uang Yang Dibayarkan : ");
	JLabel lblkembalian = new JLabel("Uang Kembalian : ");
	JLabel lbltotalakhir = new JLabel("Total Bayar: ");
	JLabel lbltotalawal = new JLabel("Sub Total : ");
	JLabel lblidtrans = new JLabel("ID Transaksi");
	JLabel lblnamakasir = new JLabel("Nama Kasir : ");
	JLabel lblidproduct = new JLabel("ID Product : ");
	JLabel lblnamaproduct = new JLabel("Nama Product : ");
	JLabel lblharga = new JLabel("Harga Satuan : ");
	JLabel lblkuantitas = new JLabel("Kuantitas : ");
	JTextField txtbayar = new JTextField(20);
	JTextField txtkembalian = new JTextField(20);
	JTextField txtidtrans = new JTextField(20);
	JTextField txtnamakasir = new JTextField(20);
	JComboBox cboidproduct = new JComboBox();
	JTextField txtnamaproduct = new JTextField(20);
	JTextField txtharga = new JTextField(20);
	JTextField txtkuantitas = new JTextField(20);
	JTextField txttotalawal = new JTextField(20);
	JTextField txttotalakhir = new JTextField(20);
	JButton btnaddtocart = new JButton("Add To Cart");
	JButton btnbuy = new JButton("Buy");
	JButton btncancel = new JButton("Cancel");

	public Menu_Kasir(String title) {
		super(title);
		super.setSize(882, 421);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelTransaksi.setVisible(false);
		panelTransaksi.setLayout(new GridBagLayout());
		

		kontainer.setLayout(new FlowLayout());

		menuBar.add(menuKasir);
		menuKasir.add(miTambahTransaksi);
		menuKasir.add(miLogOut);

		super.setJMenuBar(menuBar);
		addTrans();
		LogOut();
		idproduct();

		panelTransaksi.setBorder(BorderFactory
				.createTitledBorder(""));

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 20, 10, 30);
		panelTransaksi.setBackground(Color.CYAN);
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelTransaksi.add(judul,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelTransaksi.add(lblidtrans, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelTransaksi.add(txtidtrans, gbc);
		txtidtrans.setEnabled(false);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelTransaksi.add(lblnamakasir, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panelTransaksi.add(txtnamakasir, gbc);
		txtnamakasir.setEnabled(false);

		gbc.gridx = 2;
		gbc.gridy = 1;
		panelTransaksi.add(lblidproduct, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		panelTransaksi.add(cboidproduct, gbc);
		cboidproduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tampildatabyID();
			}
		});

		gbc.gridx = 2;
		gbc.gridy = 2;
		panelTransaksi.add(lblnamaproduct, gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		panelTransaksi.add(txtnamaproduct, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		panelTransaksi.add(lblharga, gbc);

		gbc.gridx = 3;
		gbc.gridy = 3;
		panelTransaksi.add(txtharga, gbc);

		gbc.gridx = 2;
		gbc.gridy = 4;
		panelTransaksi.add(lblkuantitas, gbc);

		gbc.gridx = 3;
		gbc.gridy = 4;
		panelTransaksi.add(txtkuantitas, gbc);

		gbc.gridx = 2;
		gbc.gridy = 5;
		panelTransaksi.add(lbltotalawal, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		panelTransaksi.add(lblbayar, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		panelTransaksi.add(txtbayar, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		panelTransaksi.add(lblkembalian, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		panelTransaksi.add(txtkembalian, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 5;
		panelTransaksi.add(txttotalawal, gbc);
		txttotalawal.setBackground(Color.BLACK);
		txttotalawal.setForeground(Color.ORANGE);

		gbc.gridx = 3;
		gbc.gridy = 6;
		panelTransaksi.add(txttotalakhir, gbc);
		txttotalakhir.setBackground(Color.BLACK);
		txttotalakhir.setForeground(Color.YELLOW);

		gbc.gridx = 2;
		gbc.gridy = 6;
		panelTransaksi.add(lbltotalakhir, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		panelTransaksi.add(btnaddtocart, gbc);
		btnaddtocart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cekStock();
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 7;
		panelTransaksi.add(btnbuy, gbc);
		btnbuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uangkembalian();
				JOptionPane.showMessageDialog(null,
						"Total Kembalian Rp. "+lbluangkembalian.getText(), "Success",
						JOptionPane.INFORMATION_MESSAGE);
				txtkuantitas.setText("");
				txttotalawal.setText("");
				int x = JOptionPane.showConfirmDialog(null,
						"Apakah Anda Ingin Menambah Transaksi ?",
						"Tambah Transaksi", JOptionPane.YES_NO_OPTION);
				if (x == 0) {
					idTr();
					panelTransaksi.setVisible(true);
					txtnamaproduct.setText("");
					txtharga.setText("");
					txtkuantitas.setText("");
					txttotalawal.setText("");
					txttotalakhir.setText("");
					txtbayar.setText("");
					txtkembalian.setText("");
				} else {
					panelTransaksi.setVisible(false);
					txtnamaproduct.setText("");
					txtharga.setText("");
					txtkuantitas.setText("");
					txttotalawal.setText("");
					txttotalakhir.setText("");
					txtbayar.setText("");
					txtkembalian.setText("");
				}

			}
		});

		gbc.gridx = 3;
		gbc.gridy = 7;
		panelTransaksi.add(btncancel, gbc);
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelTransaksi.setVisible(false);
				txtnamaproduct.setText("");
				txtharga.setText("");
				txtkuantitas.setText("");
				txttotalawal.setText("");
				txttotalakhir.setText("");
				txtbayar.setText("");
				txtkembalian.setText("");
			}
		});
		kontainer.add(panelTransaksi);
	}

	public void simpanTransaksi() {
		String idTr = txtidtrans.getText();
		String namakasir = txtnamakasir.getText();
		String idProduct = cboidproduct.getSelectedItem().toString();
		String namaProduct = txtnamaproduct.getText();
		String hargasatuan = txtharga.getText();
		String kuantitas = txtkuantitas.getText();
		String totalharga = txttotalawal.getText();

		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (idTr.equals("") || namakasir.equals("") || namaProduct.equals("")
				|| hargasatuan.equals("") || kuantitas.equals("")) {
			JOptionPane.showMessageDialog(null, "Data Masih Ada Yang Kosong!");
		} else {

			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt
						.executeQuery("INSERT INTO PENJUALAN VALUES ('" + idTr
								+ "','" + namakasir + "','" + idProduct + "','"
								+ namaProduct + "', '" + hargasatuan + "', '"
								+ kuantitas + "', '" + totalharga + "');");
				stmt.close();
			}

			catch (SQLException x) {

				x.printStackTrace();
			}

		}
	}

	public void hitung() {
		a = Integer.parseInt(txtharga.getText());
		b = Integer.parseInt(txtkuantitas.getText());
		c = a * b;
		d = c + d;
		txttotalawal.setText("" + c);
		txttotalakhir.setText("" + d);
	}
	
	public void uangkembalian(){
		e = Integer.parseInt(txtbayar.getText());
		f = e - d;
		lbluangkembalian.setText(""+f);
		txtkembalian.setText(""+f);
	}

	public void namakasir(String username) {
		String nama = "";
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
					.executeQuery("SELECT nama FROM ADMIN WHERE username='"
							+ username + "'");

			while (rs.next()) {
				nama = rs.getString(1);
			}
			txtnamakasir.setText(nama);
			stmt.close();
		}

		catch (SQLException x) {
			x.printStackTrace();
		}
	}

	public void addTrans() {
		miTambahTransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelTransaksi.setVisible(true);
				// idTr();
				idTr();
				namakasir(nama);
				// txtnamakasir.setText(ml.txt_user.getText());

			}
		});
	}

	public void LogOut() {
		miLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Menu_Login ml = new Menu_Login("Login");
				ml.setVisible(true);
			}
		});
	}

	public void idproduct() {
		String nama = "";
		int harga = 0;
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			ArrayList<String> groupNames = new ArrayList<String>();
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT distinct id FROM PRODUCT");

			while (rs.next()) {
				String isicbo = rs.getString(1);
				groupNames.add(isicbo);
			}
			stmt.close();
			DefaultComboBoxModel model = new DefaultComboBoxModel(
					groupNames.toArray());
			cboidproduct.setModel(model);
		}

		catch (SQLException x) {
			x.printStackTrace();
		}
	}

	public void tampildatabyID() {
		String nama = "";
		int harga = 0;
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
					.executeQuery("SELECT nama,harga FROM PRODUCT WHERE id='"
							+ cboidproduct.getSelectedItem().toString() + "'");

			while (rs.next()) {
				nama = rs.getString(1);
				harga = rs.getInt(2);
			}
			txtnamaproduct.setText(nama);
			txtharga.setText("" + harga);
			stmt.close();
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}
	public void cekStock() {
		int stock = Integer.parseInt(txtkuantitas.getText());
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
					.executeQuery("SELECT stock FROM PRODUCT WHERE id ='"
							+ cboidproduct.getSelectedItem().toString() + "'");

			while (rs.next()) {
				stockdb = rs.getInt(1);
			}
			if (stock > stockdb) {
				JOptionPane.showMessageDialog(null, "Stock Tidak Mencukupi!",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				txtkuantitas.setText("");
				txttotalawal.setText("");
				txtnamaproduct.setText("");
				txtharga.setText("");
			} else {
				hitung();
				simpanTransaksi();
				penguranganstock();
				updateStock();
				JOptionPane.showMessageDialog(null,
						"Data Berhasil Ditambahkan!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				txtkuantitas.setText("");
				txttotalawal.setText("");
				txtnamaproduct.setText("");
				txtharga.setText("");
			}
			stmt.close();
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}
	
	public void penguranganstock() {
		int stockdb = 0;
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
					.executeQuery("SELECT stock FROM PRODUCT WHERE id='"
							+ cboidproduct.getSelectedItem().toString() + "'");

			while (rs.next()) {
				stockdb = rs.getInt(1);
			}
			sisastock = stockdb - Integer.parseInt(txtkuantitas.getText());
			stmt.close();
		}

		catch (SQLException x) {
			x.printStackTrace();
		}
	}
	
	public void updateStock() {
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
					.executeQuery("UPDATE PRODUCT SET stock = '"+sisastock+"' WHERE id = '"+cboidproduct.getSelectedItem().toString()+"'");
			stmt.close();
		}

		catch (SQLException x) {
			x.printStackTrace();
		}
	}


	public void idTr() {
		int urut = 1;
		String idCek = txtidtrans.getText();
		String idtTrans = "IDX" + urut;
		String cekId = "";
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
					.executeQuery("SELECT idtrans FROM PENJUALAN WHERE idtrans = '"
							+ idtTrans + "'");
			while (rs.next()) {
				cekId = rs.getString(1);
			}

			if (cekId.equals("")) {
				txtidtrans.setText(idtTrans);
			}

			else {
				rs = stmt
						.executeQuery("SELECT idtrans FROM PENJUALAN WHERE idtrans = '"
								+ idCek + "'");
				while (rs.next()) {
					cekId = rs.getString(1);
				}
				if (!cekId.equals("")) {
					boolean benar = false;
					do {
						urut += 1;
						idtTrans = "IDX" + urut;
						rs = stmt
								.executeQuery("SELECT idtrans FROM PENJUALAN WHERE idtrans = '"
										+ idtTrans + "'");
						while (rs.next()) {
							cekId = rs.getString(1);
						}
						if (cekId.equals(idtTrans)) {
							benar = true;
						} else {
							benar = false;
							txtidtrans.setText(idtTrans);
						}
					} while (benar != false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
