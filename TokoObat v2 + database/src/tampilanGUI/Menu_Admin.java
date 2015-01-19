package tampilanGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.*;

import tampilanGUI.Config;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Menu_Admin extends JFrame {
	// Deklarasi Variabel
	Connection koneksi;
	private int rowx;
	private int columnx;
	private String namaTable = "";
	public boolean DeletedAdmin;
	public boolean DeletedProduct;
	public boolean DeletedSupplier;
	public String idx = "";
	String usernameAdmin = "";
	String idprodukxxx = "";
	String idsupxxx = "";
	
	// ----------- TABEL -----------------------//
	DefaultTableModel dataTabelEA = new DefaultTableModel();
	DefaultTableModel dataTabelEP = new DefaultTableModel();
	DefaultTableModel dataTabelES = new DefaultTableModel();
	DefaultTableModel dataTabelVR = new DefaultTableModel();

	JMenuBar menuBar = new JMenuBar();

	JMenu MenuAdmin = new JMenu("User");
	JMenu MenuProduct = new JMenu("Product");
	JMenu MenuSupplier = new JMenu("Supplier");
	JMenu MenuStock = new JMenu("Stock");
	JMenu MenuReport = new JMenu("Report");

	JMenuItem newMenuAdmin = new JMenuItem("New User");
	JMenuItem editMenuAdmin = new JMenuItem("Edit User");
	JMenuItem miLogOut = new JMenuItem("Log Out");

	JMenuItem newMenuProduct = new JMenuItem("New Product");
	JMenuItem editMenuProduct = new JMenuItem("Edit Product");

	JMenuItem newMenuSupplier = new JMenuItem("New Supplier");
	JMenuItem editMenuSupplier = new JMenuItem("Edit Supplier");

	JMenuItem newMenuStock = new JMenuItem("New Stock");

	JMenuItem viewMenuReport = new JMenuItem("View Report");

	Container kontainer = getContentPane();

	// ----------- PANEL TABEL -----------------------//
	JPanel panelTabelEA = new JPanel();
	JPanel panelTabelEP = new JPanel();
	JPanel panelTabelES = new JPanel();
	JPanel panelTabelVR = new JPanel();

	// ----------- PANEL MENU ADMINISTRATOR -----------------------//
	JPanel panelTambahAdmin = new JPanel();
	JPanel panelEditAdmin = new JPanel();

	// ----------- PANEL MENU PRODUCT -----------------------//
	JPanel panelTambahProduct = new JPanel();
	JPanel panelEditProduct = new JPanel();

	// ----------- PANEL MENU SUPPLIER -----------------------//
	JPanel panelTambahSupplier = new JPanel();
	JPanel panelEditSupplier = new JPanel();

	// ----------- PANEL MENU REPORT -------------------------//
	JPanel panelReport = new JPanel();
	JPanel panelInfoReport = new JPanel();

	// ----------- PANEL MENU STOCK -----------------------//
	JPanel panelTambahStock = new JPanel();

	JTable tabelEA = new JTable(dataTabelEA);
	JTable tabelEP = new JTable(dataTabelEP);
	JTable tabelES = new JTable(dataTabelES);
	JTable tabelVR = new JTable(dataTabelVR);
	JScrollPane scrollEA = new JScrollPane(tabelEA);
	JScrollPane scrollEP = new JScrollPane(tabelEP);
	JScrollPane scrollES = new JScrollPane(tabelES);
	JScrollPane scrollVR = new JScrollPane(tabelVR);

	// ----------- ISI PANEL TAMBAH ADMIN -----------------------//
	JLabel newadmin_user = new JLabel("Username : ");
	JLabel newadmin_nama = new JLabel("Nama : ");
	JLabel newadmin_pass = new JLabel("Password : ");
	JLabel newadmin_akses = new JLabel("Hak Akses : ");
	JTextField txt_newadmin_user = new JTextField(20);
	JTextField txt_newadmin_nama = new JTextField(20);
	JTextField txt_newadmin_pass = new JTextField(20);
	String[] newakses = { "Administrator", "Kasir" };
	JComboBox cbo_newadmin_akses = new JComboBox(newakses);
	JButton newadmin_save = new JButton("Save");
	JButton newadmin_cancel = new JButton("Cancel");

	// ----------- ISI PANEL EDIT ADMIN -----------------------//
	JLabel editadmin_user = new JLabel("Username : ");
	JTextField txt_editadmin_user = new JTextField(20);
	JButton editadmin_cari = new JButton("Cari");
	JButton editadmin_cancel = new JButton("Cancel");

	// ----------- ISI PANEL TAMBAH PRODUCT -----------------------//
	JLabel newproduct_id = new JLabel("ID Product : ");
	JLabel newproduct_nama = new JLabel("Nama Product : ");
	JLabel newproduct_idsup = new JLabel("ID Supplier : ");
	JLabel newproduct_harga = new JLabel("Harga Product : ");
	JLabel newproduct_stock = new JLabel("Stock :");
	JTextField txt_newproduct_id = new JTextField(20);
	JTextField txt_newproduct_nama = new JTextField(20);
	JComboBox cbo_newproduct_supplier = new JComboBox();
	JTextField txt_newproduct_harga = new JTextField(20);
	JTextField txt_newproduct_stock = new JTextField(20);
	JButton newproduct_save = new JButton("Save");
	JButton newproduct_cancel = new JButton("Cancel");

	// ----------- ISI PANEL EDIT PRODUCT -----------------------//
	JLabel editProduct_id = new JLabel("ID Product : ");
	JTextField txt_editProduct_id = new JTextField(20);
	JButton editProduct_cari = new JButton("Cari");

	// ----------- ISI PANEL TAMBAH SUPPLIER -----------------------//
	JLabel newSupplier_id = new JLabel("ID Supplier : ");
	JLabel newSupplier_nama = new JLabel("Nama Supplier : ");
	JTextField txt_newSupplier_id = new JTextField(20);
	JTextField txt_newSupplier_nama = new JTextField(20);
	JButton newSupplier_save = new JButton("Save");
	JButton newSupplier_cancel = new JButton("Cancel");

	// ----------- ISI PANEL EDIT SUPPLIER -----------------------//
	JLabel editSupplier_id = new JLabel("ID Supplier : ");
	JTextField txt_editSupplier_id = new JTextField(20);
	JButton editSupplier_cari = new JButton("Cari");

	// ----------- ISI PANEL TAMBAH STOCK -----------------------//
	JLabel newStock_id = new JLabel("ID Product : ");
	JLabel newStock_nama = new JLabel("Nama Product : ");
	JLabel newStock_sisa = new JLabel("Stock sisa : ");
	JLabel newStock_tambah = new JLabel(
			"Jumlah stock yang ingin ditambahkan : ");
	JComboBox cbo_newStock_id = new JComboBox();
	JTextField txt_newStock_nama = new JTextField(20);
	JTextField txt_newStock_sisa = new JTextField(20);
	JTextField txt_newStock_tambah = new JTextField(20);
	JButton newStock_save = new JButton("Save");
	JButton newStock_cancel = new JButton("Cancel");

	// ----------- ISI PANEL LIHAT STOCK -----------------------//
	JLabel lihatStock_id = new JLabel("ID Product : ");
	JTextField txt_lihatStock_id = new JTextField(20);
	JButton lihatStock_cari = new JButton("Cari");

	// ----------- ISI PANEL REPORT --------------------------//
	JLabel lblreport = new JLabel("Nama Kasir : ");
	JComboBox cboreport = new JComboBox();
	
	JLabel lbltext = new JLabel();
	int omsetkasir = 0;
	int omsettotal = 0;

	public Menu_Admin(String title) {
		super(title);
		super.setSize(540, 480);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		panelTambahAdmin.setVisible(false);
		panelEditAdmin.setVisible(false);
		panelTabelEA.setVisible(false);

		panelTambahProduct.setVisible(false);
		panelEditProduct.setVisible(false);
		panelTabelEP.setVisible(false);

		panelTambahSupplier.setVisible(false);
		panelEditSupplier.setVisible(false);
		panelTabelES.setVisible(false);

		panelTambahStock.setVisible(false);

		panelReport.setVisible(false);
		panelTabelVR.setVisible(false);
		panelInfoReport.setVisible(false);

		// G-R-I-D-B-A-G
		panelTambahAdmin.setLayout(new GridBagLayout());
		panelEditAdmin.setLayout(new GridBagLayout());
		panelTabelEA.setLayout(new GridBagLayout());

		panelTambahProduct.setLayout(new GridBagLayout());
		panelEditProduct.setLayout(new GridBagLayout());
		panelTabelEP.setLayout(new GridBagLayout());

		panelTambahSupplier.setLayout(new GridBagLayout());
		panelEditSupplier.setLayout(new GridBagLayout());
		panelTabelES.setLayout(new GridBagLayout());

		panelTambahStock.setLayout(new GridBagLayout());

		panelReport.setLayout(new GridBagLayout());
		panelTabelVR.setLayout(new GridBagLayout());
		panelInfoReport.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints(); // Komponen Pasti
															// GridBag

		kontainer.setLayout(new FlowLayout());

		menuBar.add(MenuAdmin);
		menuBar.add(MenuProduct);
		menuBar.add(MenuSupplier);
		menuBar.add(MenuStock);
		menuBar.add(MenuReport);

		newMenuProduct.setMnemonic(KeyEvent.VK_N);
		editMenuProduct.setMnemonic(KeyEvent.VK_E);
		newMenuSupplier.setMnemonic(KeyEvent.VK_N);
		editMenuSupplier.setMnemonic(KeyEvent.VK_E);
		newMenuStock.setMnemonic(KeyEvent.VK_N);
		viewMenuReport.setMnemonic(KeyEvent.VK_V);
		newMenuAdmin.setMnemonic(KeyEvent.VK_N);
		editMenuAdmin.setMnemonic(KeyEvent.VK_E);

		MenuAdmin.add(newMenuAdmin);
		MenuAdmin.add(editMenuAdmin);
		MenuAdmin.add(miLogOut);

		MenuProduct.add(newMenuProduct);
		MenuProduct.add(editMenuProduct);

		MenuSupplier.add(newMenuSupplier);
		MenuSupplier.add(editMenuSupplier);

		MenuStock.add(newMenuStock);
		MenuReport.add(viewMenuReport);

		super.setJMenuBar(menuBar);

		// Action
		tambahAdmin();
		editAdmin();
		tambahProduct();
		editProduct();
		tambahSupplier();
		editSupplier();
		tambahStock();
		viewReport();
		LogOut();

		// Panel Tambah User
		panelTambahAdmin.setBorder(BorderFactory
				.createTitledBorder("Tambah User")); // Menampilkan
														// Garis
														// Panel
														// dan
														// Judul
		cbo_newadmin_akses.setSelectedItem(2);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTambahAdmin.add(newadmin_user, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelTambahAdmin.add(txt_newadmin_user, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelTambahAdmin.add(newadmin_nama, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelTambahAdmin.add(txt_newadmin_nama, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelTambahAdmin.add(newadmin_pass, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panelTambahAdmin.add(txt_newadmin_pass, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelTambahAdmin.add(newadmin_akses, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panelTambahAdmin.add(cbo_newadmin_akses, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.ipadx = 3;
		newadmin_save.setPreferredSize(new Dimension(170, 30));
		panelTambahAdmin.add(newadmin_save, gbc);
		newadmin_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tambahTableAdmin();

				for (int i = tabelEA.getRowCount() - 1; i > -1; i--) {
					dataTabelEA.removeRow(i);
				}
				;

				isiTableEA();
				txt_newadmin_user.setText("");
				txt_newadmin_pass.setText("");
				txt_newadmin_nama.setText("");
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 4;
		newadmin_cancel.setPreferredSize(new Dimension(170, 30));
		panelTambahAdmin.add(newadmin_cancel, gbc);
		newadmin_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_newadmin_user.setText("");
				txt_newadmin_pass.setText("");
				txt_newadmin_nama.setText("");
				panelTambahAdmin.setVisible(false);
			}
		});

		// Panel Edit Admin
		panelEditAdmin.setBorder(BorderFactory.createTitledBorder("Edit User")); // Menampilkan
																					// Garis
																					// Panel
																					// dan
																					// Judul
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelEditAdmin.add(editadmin_user, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelEditAdmin.add(txt_editadmin_user, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		panelEditAdmin.add(editadmin_cari, gbc);
		editadmin_cari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cariTableEA();
				txt_editadmin_user.setText("");
			}
		});

		scrollEA.setPreferredSize(new Dimension(500, 300));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTabelEA.add(scrollEA, gbc);

		dataTabelEA.addColumn("Username");
		dataTabelEA.addColumn("Nama");
		dataTabelEA.addColumn("Password");
		dataTabelEA.addColumn("Hak Akses");
		dataTabelEA.addColumn("Edit");
		dataTabelEA.addColumn("Hapus");

		tabelEA.setModel(dataTabelEA);

		tabelEA.getColumnModel().getColumn(4)
				.setCellRenderer(new ButtonRenderer());
		tabelEA.getColumnModel().getColumn(4)
				.setCellEditor(new ButtonEditor(new JCheckBox()));
		tabelEA.getColumnModel().getColumn(5)
				.setCellRenderer(new ButtonRenderer());
		tabelEA.getColumnModel().getColumn(5)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		/*
		 * tabelEA.getColumnModel().getColumn(0).setPreferredWidth(10);
		 * tabelEA.getColumnModel().getColumn(1).setPreferredWidth(50);
		 * tabelEA.getColumnModel().getColumn(2).setPreferredWidth(60);
		 */

		scrollEA.setVisible(true);
		isiTableEA();

		// Panel Tambah Product
		panelTambahProduct.setBorder(BorderFactory
				.createTitledBorder("Tambah Product")); // Menampilkan
		// Garis
		// Panel
		// dan
		// Judul

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTambahProduct.add(newproduct_id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelTambahProduct.add(txt_newproduct_id, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelTambahProduct.add(newproduct_nama, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelTambahProduct.add(txt_newproduct_nama, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelTambahProduct.add(newproduct_idsup, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panelTambahProduct.add(cbo_newproduct_supplier, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelTambahProduct.add(newproduct_harga, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panelTambahProduct.add(txt_newproduct_harga, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelTambahProduct.add(newproduct_stock, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		panelTambahProduct.add(txt_newproduct_stock, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.ipadx = 3;
		newproduct_save.setPreferredSize(new Dimension(170, 30));
		panelTambahProduct.add(newproduct_save, gbc);
		newproduct_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tambahTableProduct();

				for (int i = tabelEP.getRowCount() - 1; i > -1; i--) {
					dataTabelEP.removeRow(i);
				}
				;

				isiTableEP();

				txt_newproduct_id.setText("");
				txt_newproduct_nama.setText("");
				cbo_newproduct_supplier.setSelectedItem("");
				txt_newproduct_harga.setText("");
				txt_newproduct_stock.setText("");
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 5;
		newproduct_cancel.setPreferredSize(new Dimension(170, 30));
		panelTambahProduct.add(newproduct_cancel, gbc);
		newproduct_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_newproduct_id.setText("");
				txt_newproduct_nama.setText("");
				cbo_newproduct_supplier.setSelectedItem("");
				txt_newproduct_harga.setText("");
				txt_newproduct_stock.setText("");
				panelTambahProduct.setVisible(false);
			}
		});

		// Panel Edit Produk
		panelEditProduct.setBorder(BorderFactory
				.createTitledBorder("Edit Product")); // Menampilkan
		// Garis
		// Panel
		// dan
		// Judul
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelEditProduct.add(editProduct_id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelEditProduct.add(txt_editProduct_id, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		panelEditProduct.add(editProduct_cari, gbc);
		editProduct_cari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cariTableEP();
				txt_newSupplier_id.setText("");
				txt_newSupplier_nama.setText("");
			}
		});

		scrollEP.setPreferredSize(new Dimension(500, 300));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTabelEP.add(scrollEP, gbc);

		dataTabelEP.addColumn("ID");
		dataTabelEP.addColumn("Nama Product");
		dataTabelEP.addColumn("ID Supplier");
		dataTabelEP.addColumn("Harga");
		dataTabelEP.addColumn("Stock");
		dataTabelEP.addColumn("Edit");
		dataTabelEP.addColumn("Hapus");

		tabelEP.setModel(dataTabelEP);
		tabelEP.getColumnModel().getColumn(5)
				.setCellRenderer(new ButtonRenderer());
		tabelEP.getColumnModel().getColumn(5)
				.setCellEditor(new ButtonEditor(new JCheckBox()));
		tabelEP.setModel(dataTabelEP);
		tabelEP.getColumnModel().getColumn(6)
				.setCellRenderer(new ButtonRenderer());
		tabelEP.getColumnModel().getColumn(6)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		tabelEP.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabelEP.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabelEP.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabelEP.getColumnModel().getColumn(4).setPreferredWidth(40);

		scrollEP.setVisible(true);
		isiTableEP();

		// Panel Tambah Supplier
		panelTambahSupplier.setBorder(BorderFactory
				.createTitledBorder("Tambah Supplier")); // Menampilkan Garis
															// Panel dan Judul
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTambahSupplier.add(newSupplier_id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelTambahSupplier.add(txt_newSupplier_id, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelTambahSupplier.add(newSupplier_nama, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelTambahSupplier.add(txt_newSupplier_nama, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipadx = 3;
		panelTambahSupplier.add(newSupplier_save, gbc);
		newSupplier_save.setPreferredSize(new Dimension(170, 30));
		newSupplier_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tambahTableSupplier();

				for (int i = tabelES.getRowCount() - 1; i > -1; i--) {
					dataTabelES.removeRow(i);
				}
				;

				isiTableES();

				txt_newSupplier_id.setText("");
				txt_newSupplier_nama.setText("");
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 2;
		panelTambahSupplier.add(newSupplier_cancel, gbc);
		newSupplier_cancel.setPreferredSize(new Dimension(170, 30));
		newSupplier_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_newSupplier_id.setText("");
				txt_newSupplier_nama.setText("");
				panelTambahSupplier.setVisible(false);
			}
		});

		// Panel Edit Supplier
		panelEditSupplier.setBorder(BorderFactory
				.createTitledBorder("Edit Supplier")); // Menampilkan Garis
														// Panel dan Judul
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelEditSupplier.add(editSupplier_id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelEditSupplier.add(txt_editSupplier_id, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		panelEditSupplier.add(editSupplier_cari, gbc);
		editSupplier_cari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cariTableES();
			}
		});

		scrollES.setPreferredSize(new Dimension(500, 300));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTabelES.add(scrollES, gbc);

		dataTabelES.addColumn("ID Supplier");
		dataTabelES.addColumn("Nama Supplier");
		dataTabelES.addColumn("Edit");
		dataTabelES.addColumn("Hapus");

		tabelES.setModel(dataTabelES);
		tabelES.getColumnModel().getColumn(2)
				.setCellRenderer(new ButtonRenderer());
		tabelES.getColumnModel().getColumn(2)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		tabelES.getColumnModel().getColumn(3)
				.setCellRenderer(new ButtonRenderer());
		tabelES.getColumnModel().getColumn(3)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		scrollES.setVisible(true);
		isiTableES();

		// Panel New Stock
		panelTambahStock.setBorder(BorderFactory
				.createTitledBorder("Tambah Stock")); // Menampilkan Garis
														// Panel dan Judul
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTambahStock.add(newStock_id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelTambahStock.add(cbo_newStock_id, gbc);
		cbo_newStock_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NamaProductDanSisaStock();
				// JOptionPane.showMessageDialog(null,
				// "Data Berhasil Disimpan");
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelTambahStock.add(newStock_nama, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelTambahStock.add(txt_newStock_nama, gbc);
		txt_newStock_nama.setEnabled(false);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelTambahStock.add(newStock_sisa, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panelTambahStock.add(txt_newStock_sisa, gbc);
		txt_newStock_sisa.setEnabled(false);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelTambahStock.add(newStock_tambah, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panelTambahStock.add(txt_newStock_tambah, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.ipadx = 3;
		newStock_save.setPreferredSize(new Dimension(170, 30));
		panelTambahStock.add(newStock_save, gbc);
		newStock_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stocktotal();
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 4;
		newStock_cancel.setPreferredSize(new Dimension(170, 30));
		panelTambahStock.add(newStock_cancel, gbc);
		newStock_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_newStock_sisa.setText("");
				txt_newStock_tambah.setText("");
				panelTambahStock.setVisible(false);
			}
		});

		panelReport.setBorder(BorderFactory.createTitledBorder("Report View")); // Menampilkan
																				// Garis
																				// Panel
																				// dan
																				// Judul
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelReport.add(lblreport, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelReport.add(cboreport, gbc);
		cboreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cariTableVR();
				omsetKasirbyNama();
				lbltext.setText("Total hasil penjualan "+cboreport.getSelectedItem()+" sebesar Rp. "+omsetkasir);
			}
		});

		scrollVR.setPreferredSize(new Dimension(500, 250));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTabelVR.add(scrollVR, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelInfoReport.add(lbltext, gbc);

		dataTabelVR.addColumn("ID Transaksi");
		dataTabelVR.addColumn("Nama Kasir");
		dataTabelVR.addColumn("Nama Product");
		dataTabelVR.addColumn("Harga");
		dataTabelVR.addColumn("Terjual");
		dataTabelVR.addColumn("Sub Total");
		tabelVR.setModel(dataTabelVR);

		tabelVR.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabelVR.getColumnModel().getColumn(4).setPreferredWidth(30);
		tabelVR.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		scrollVR.setVisible(true);
		isiTableVR();

		kontainer.add(panelReport);
		kontainer.add(panelTabelVR);
		kontainer.add(panelInfoReport);

		kontainer.add(panelTambahStock);

		kontainer.add(panelTambahSupplier);
		kontainer.add(panelEditSupplier);
		kontainer.add(panelTabelES);

		kontainer.add(panelTambahProduct);
		kontainer.add(panelEditProduct);
		kontainer.add(panelTabelEP);

		kontainer.add(panelTambahAdmin);
		kontainer.add(panelEditAdmin);
		kontainer.add(panelTabelEA);

	}

	// Tombol Tambah Admin
	public void tambahAdmin() {
		newMenuAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tampilkan panel
				panelTambahAdmin.setVisible(true);
				panelEditAdmin.setVisible(false);
				panelTabelEA.setVisible(false);

				panelTambahProduct.setVisible(false);
				panelEditProduct.setVisible(false);
				panelTabelEP.setVisible(false);

				panelTambahSupplier.setVisible(false);
				panelEditSupplier.setVisible(false);
				panelTabelES.setVisible(false);

				panelTambahStock.setVisible(false);

				panelReport.setVisible(false);
				panelTabelVR.setVisible(false);
			}
		});
	}

	// Tombol Edit Admin
	public void editAdmin() {
		editMenuAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namaTable = "TabelEA";
				panelTambahAdmin.setVisible(false);
				panelEditAdmin.setVisible(true);
				panelTabelEA.setVisible(true);

				panelTambahProduct.setVisible(false);
				panelEditProduct.setVisible(false);
				panelTabelEP.setVisible(false);

				panelTambahSupplier.setVisible(false);
				panelEditSupplier.setVisible(false);
				panelTabelES.setVisible(false);

				panelTambahStock.setVisible(false);

				panelReport.setVisible(false);
				panelTabelVR.setVisible(false);
				panelInfoReport.setVisible(false);
			}
		});
	}

	// Tombol Tambah Product
	public void tambahProduct() {
		newMenuProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idsupplier();
				panelTambahAdmin.setVisible(false);
				panelEditAdmin.setVisible(false);
				panelTabelEA.setVisible(false);

				panelTambahProduct.setVisible(true);
				panelEditProduct.setVisible(false);
				panelTabelEP.setVisible(false);

				panelTambahSupplier.setVisible(false);
				panelEditSupplier.setVisible(false);
				panelTabelES.setVisible(false);

				panelTambahStock.setVisible(false);

				panelReport.setVisible(false);
				panelTabelVR.setVisible(false);
				panelInfoReport.setVisible(false);
			}
		});
	}

	// Tombol Edit Product
	public void editProduct() {
		editMenuProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namaTable = "TabelEP";
				panelTambahAdmin.setVisible(false);
				panelEditAdmin.setVisible(false);
				panelTabelEA.setVisible(false);

				panelTambahProduct.setVisible(false);
				panelEditProduct.setVisible(true);
				panelTabelEP.setVisible(true);

				panelTambahSupplier.setVisible(false);
				panelEditSupplier.setVisible(false);
				panelTabelES.setVisible(false);

				panelTambahStock.setVisible(false);

				panelReport.setVisible(false);
				panelTabelVR.setVisible(false);
				panelInfoReport.setVisible(false);
			}
		});
	}

	// Tombol Tambah Supplier
	public void tambahSupplier() {
		newMenuSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelTambahAdmin.setVisible(false);
				panelEditAdmin.setVisible(false);
				panelTabelEA.setVisible(false);

				panelTambahProduct.setVisible(false);
				panelEditProduct.setVisible(false);
				panelTabelEP.setVisible(false);

				panelTambahSupplier.setVisible(true);
				panelEditSupplier.setVisible(false);
				panelTabelES.setVisible(false);

				panelTambahStock.setVisible(false);

				panelReport.setVisible(false);
				panelTabelVR.setVisible(false);
				panelInfoReport.setVisible(false);
			}
		});
	}

	// Tombol Edit Supplier
	public void editSupplier() {
		editMenuSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namaTable = "TabelES";
				panelTambahAdmin.setVisible(false);
				panelEditAdmin.setVisible(false);
				panelTabelEA.setVisible(false);

				panelTambahProduct.setVisible(false);
				panelEditProduct.setVisible(false);
				panelTabelEP.setVisible(false);

				panelTambahSupplier.setVisible(false);
				panelEditSupplier.setVisible(true);
				panelTabelES.setVisible(true);

				panelTambahStock.setVisible(false);

				panelReport.setVisible(false);
				panelTabelVR.setVisible(false);
				panelInfoReport.setVisible(false);
			}
		});
	}

	// Tombol Tambah Stock
	public void tambahStock() {
		newMenuStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idproduct();
				panelTambahAdmin.setVisible(false);
				panelEditAdmin.setVisible(false);
				panelTabelEA.setVisible(false);

				panelTambahProduct.setVisible(false);
				panelEditProduct.setVisible(false);
				panelTabelEP.setVisible(false);

				panelTambahSupplier.setVisible(false);
				panelEditSupplier.setVisible(false);
				panelTabelES.setVisible(false);

				panelTambahStock.setVisible(true);

				panelReport.setVisible(false);
				panelTabelVR.setVisible(false);
				panelInfoReport.setVisible(false);
			}
		});
	}

	// Panel View Report
	public void viewReport() {
		viewMenuReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tampilkan panel
				namakasir();
				isiTableVR();
				omsetseluruh();
				lbltext.setText("Total hasil penjualan keseluruhan sebesar Rp. "+omsettotal);
				panelTambahAdmin.setVisible(false);
				panelEditAdmin.setVisible(false);
				panelTabelEA.setVisible(false);

				panelTambahProduct.setVisible(false);
				panelEditProduct.setVisible(false);
				panelTabelEP.setVisible(false);

				panelTambahSupplier.setVisible(false);
				panelEditSupplier.setVisible(false);
				panelTabelES.setVisible(false);

				panelTambahStock.setVisible(false);

				panelReport.setVisible(true);
				panelTabelVR.setVisible(true);
				panelInfoReport.setVisible(true);
			}
		});
	}

	// Tombol Log Out
	public void LogOut() {
		miLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Menu_Login ml = new Menu_Login("Login");
				ml.setVisible(true);

			}
		});
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());

			return this;
		}

	}

	public class ButtonEditor extends DefaultCellEditor {
		protected JButton button;
		private String label;
		private boolean isPushed;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			isPushed = true;
			// mengambil baris dan kolom yang diklik
			rowx = row;
			columnx = column;
			idx = table.getValueAt(row, 0).toString();
			return button;
		}

		public Object getCellEditorValue() {
			if (isPushed) {
				showEdit(columnx, namaTable);
			}
			isPushed = false;
			return new String(label);
		}

		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped() {
			super.fireEditingStopped();
			if (DeletedAdmin) {
				DefaultTableModel tabelModel = (DefaultTableModel) tabelEA
						.getModel();
				tabelModel.removeRow(tabelEA.getSelectedRow());
				tabelEA.addNotify();
			} else if (DeletedProduct) {
				DefaultTableModel tabelModel = (DefaultTableModel) tabelEP
						.getModel();
				tabelModel.removeRow(tabelEP.getSelectedRow());
				tabelEP.addNotify();
			} else if (DeletedSupplier) {
				DefaultTableModel tabelModel = (DefaultTableModel) tabelES
						.getModel();
				tabelModel.removeRow(tabelES.getSelectedRow());
				tabelES.addNotify();
			}
			DeletedAdmin = false;
			DeletedProduct = false;
			DeletedSupplier = false;
		}

		public void showEdit(int column, String nama) {

			if ((column == 4) && (nama.equalsIgnoreCase("TabelEA"))) {
				Edit_User eu = new Edit_User("Edit User", idx, tabelEA, rowx);
				eu.setVisible(true);
			}

			else if ((column == 5) && (nama.equalsIgnoreCase("TabelEA"))) {
				int cek = JOptionPane.showConfirmDialog(null,
						"Yakin Hapus user ?", "Delete User ?",
						JOptionPane.YES_NO_OPTION);
				if (cek == 0) {
					hapusAdmin();
					DeletedAdmin = true;
				}
			} else if ((column == 5) && (nama.equalsIgnoreCase("TabelEP"))) {
				Edit_Product ep = new Edit_Product("Edit Product", idx,
						tabelEP, rowx);
				ep.setVisible(true);
			} else if ((column == 6) && (nama.equalsIgnoreCase("TabelEP"))) {
				int cek = JOptionPane.showConfirmDialog(null,
						"Yakin Hapus Product ?", "Delete User ?",
						JOptionPane.YES_NO_OPTION);
				if (cek == 0) {
					hapusProduct();
					DeletedProduct = true;
				}
			} else if ((column == 2) && (nama.equalsIgnoreCase("TabelES"))) {
				Edit_Supplier es = new Edit_Supplier("Edit Supplier", idx,
						tabelES, rowx);
				es.setVisible(true);
			} else if ((column == 3) && (nama.equalsIgnoreCase("TabelES"))) {
				int cek = JOptionPane.showConfirmDialog(null,
						"Yakin Hapus user ?", "Delete User ?",
						JOptionPane.YES_NO_OPTION);
				if (cek == 0) {
					hapusSupplier();
					DeletedSupplier = true;
				}
			} else {
				System.out.println("Gagal");
			}
		}
	}

	// ///////////// ----------- ISI TABEL ADMIN
	// ------------////////////////////////////
	public void tambahTableAdmin() {
		String username = txt_newadmin_user.getText();
		String nama = txt_newadmin_nama.getText();
		String password = txt_newadmin_pass.getText();
		String akses = cbo_newadmin_akses.getSelectedItem().toString();

		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (username.equals("") || nama.equals("") || password.equals("")
				|| akses.equals("")) {
			JOptionPane.showMessageDialog(null, "Data Masih Ada Yang Kosong!");
		} else if (username.equals(usernameAdmin)) {
			JOptionPane.showMessageDialog(null, "Username telah dipakai!");
		}else{ 
			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt.executeQuery("INSERT INTO ADMIN VALUES ('"
						+ username + "','" + nama + "','" + password + "','"
						+ akses + "');");
				stmt.close();
				JOptionPane.showMessageDialog(null, "Berhasil Menambah Data");
			}

			catch (SQLException x) {

				x.printStackTrace();
			}

		}
	}

	public void isiTableEA() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelEA.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ADMIN");
			while (rs.next()) {
				String username = rs.getString(1);
				String nama = rs.getString(2);
				String password = rs.getString(3);
				String hak_akses = rs.getString(4);
				Object[] a = { username, nama, password, hak_akses, "Edit",
						"Hapus" };
				usernameAdmin = username;
				dataTabelEA.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	public void hapusAdmin() {
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
					.executeQuery("DELETE FROM ADMIN WHERE username = '" + idx
							+ "'");
			stmt.close();
			JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
		}

		catch (SQLException x) {

			x.printStackTrace();
		}

	}

	public void cariTableEA() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelEA.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM ADMIN WHERE username LIKE '"+ txt_editadmin_user.getText() +"%' ");

			for (int i = tabelEA.getRowCount() - 1; i > -1; i--) {
				dataTabelEA.removeRow(i);
			}
			;
			while (rs.next()) {
				String username = rs.getString(1);
				String nama = rs.getString(2);
				String password = rs.getString(3);
				String hak_akses = rs.getString(4);
				Object[] a = { username, nama, password, hak_akses, "Edit",
						"Hapus" };
				dataTabelEA.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	// /////////////// ----------- ISI TABEL PRODUCT ------------
	// ////////////////////////////
	public void tambahTableProduct() {
		String id_product = txt_newproduct_id.getText();
		String nama_product = txt_newproduct_nama.getText();
		String id_supplier = cbo_newproduct_supplier.getSelectedItem().toString();
		String harga = txt_newproduct_harga.getText();
		String stock = txt_newproduct_stock.getText();

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
		} else if (id_product.equals(idprodukxxx)) {
			JOptionPane.showMessageDialog(null, "ID telah digunakan!");
		}else{
			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt
						.executeQuery("INSERT INTO PRODUCT VALUES ('"
								+ id_product + "','" + nama_product + "','"
								+ id_supplier + "', '" + harga + "', '" + stock
								+ "');");
				stmt.close();
				JOptionPane.showMessageDialog(null, "Berhasil Menambah Data");
			}

			catch (SQLException x) {

				x.printStackTrace();
			}

		}
	}

	public void isiTableEP() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		int row = dataTabelEP.getRowCount();
		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
			while (rs.next()) {
				String id = rs.getString(1);
				String nama = rs.getString(2);
				String idsup = rs.getString(3);
				int harga = rs.getInt(4);
				int stock = rs.getInt(5);
				Object[] a = { id, nama, idsup, harga, stock, "Edit", "Hapus" };
				idprodukxxx = id;
				dataTabelEP.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	public void hapusProduct() {
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
			ResultSet rs = stmt.executeQuery("DELETE FROM PRODUCT WHERE id = '"
					+ idx + "'");
			stmt.close();
			JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
		}

		catch (SQLException x) {

			x.printStackTrace();
		}

	}

	public void cariTableEP() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelEP.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM PRODUCT WHERE id LIKE'"
							+ txt_editProduct_id.getText() + "%'");
			for (int i = tabelEP.getRowCount() - 1; i > -1; i--) {
				dataTabelEP.removeRow(i);
			}
			;
			while (rs.next()) {
				String id = rs.getString(1);
				String nama = rs.getString(2);
				String idsup = rs.getString(3);
				int harga = rs.getInt(4);
				int stock = rs.getInt(5);
				Object[] a = { id, nama, idsup, harga, stock, "Edit", "Hapus" };
				dataTabelEP.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	// /////////////// ----------- ISI TABEL SUPPLIER ------------
	// ////////////////////////////
	public void tambahTableSupplier() {
		String id = txt_newSupplier_id.getText();
		String nama = txt_newSupplier_nama.getText();

		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (id.equals("") || nama.equals("")) {
			JOptionPane.showMessageDialog(null, "Data Masih Ada Yang Kosong!");
		} else if (id.equals(idsupxxx)) {
			JOptionPane.showMessageDialog(null, "ID telah digunakan!");
		}else{
			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt
						.executeQuery("INSERT INTO SUPPLIER VALUES ('" + id
								+ "','" + nama + "');");
				stmt.close();
				JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data");
			}

			catch (SQLException x) {

				x.printStackTrace();
			}

		}
	}

	public void isiTableES() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelES.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUPPLIER");
			while (rs.next()) {
				String id = rs.getString(1);
				String nama = rs.getString(2);
				Object[] a = { id, nama, "Edit", "Hapus" };
				idsupxxx = id;
				dataTabelES.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	public void hapusSupplier() {
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
					.executeQuery("DELETE FROM SUPPLIER WHERE id = '" + idx
							+ "'");
			stmt.close();
			JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
		}

		catch (SQLException x) {

			x.printStackTrace();
		}

	}

	public void cariTableES() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelEA.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM SUPPLIER WHERE id LIKE '"
							+ txt_editSupplier_id.getText() + "%'");

			for (int i = tabelES.getRowCount() - 1; i > -1; i--) {
				dataTabelES.removeRow(i);
			}
			;
			while (rs.next()) {
				String id = rs.getString(1);
				String nama = rs.getString(2);
				Object[] a = { id, nama, "Edit", "Hapus" };
				dataTabelES.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	// ----------- ISI TABEL REPORT ------------ //

	public void isiTableVR() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelVR.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT idtrans, namakasir, namaproduct, hargaproduct, kuantitas, total FROM PENJUALAN");
			for (int i = tabelVR.getRowCount() - 1; i > -1; i--) {
				dataTabelVR.removeRow(i);
			}
			;
			while (rs.next()) {
				String id = rs.getString(1);
				String namakasir = rs.getString(2);
				String nama = rs.getString(3);
				int harga = rs.getInt(4);
				int kuantitas = rs.getInt(5);
				int total = rs.getInt(6);
				Object[] a = { id, namakasir, nama, harga, kuantitas, total };
				dataTabelVR.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	public void cariTableVR() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelVR.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT idtrans, namakasir, namaproduct, hargaproduct, kuantitas, total FROM PENJUALAN WHERE namakasir = '"
							+ cboreport.getSelectedItem().toString() + "'");

			for (int i = tabelVR.getRowCount() - 1; i > -1; i--) {
				dataTabelVR.removeRow(i);
			};
			while (rs.next()) {
				String id = rs.getString(1);
				String namakasir = rs.getString(2);
				String nama = rs.getString(3);
				int harga = rs.getInt(4);
				int kuantitas = rs.getInt(5);
				int total = rs.getInt(6);
				Object[] a = { id, namakasir, nama, harga, kuantitas, total };
				dataTabelVR.addRow(a);
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}
	
	public void omsetKasirbyNama() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelVR.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT SUM(total) FROM PENJUALAN WHERE namakasir = '"
							+ cboreport.getSelectedItem().toString() + "' GROUP BY namakasir");

			/*for (int i = tabelVR.getRowCount() - 1; i > -1; i--) {
				dataTabelVR.removeRow(i);
			};*/
			while (rs.next()) {
				int omset = rs.getInt(1);
				omsetkasir = omset;
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}
	
	public void omsetseluruh() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelVR.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT SUM(total) FROM PENJUALAN");

			/*for (int i = tabelVR.getRowCount() - 1; i > -1; i--) {
				dataTabelVR.removeRow(i);
			};*/
			while (rs.next()) {
				int omset = rs.getInt(1);
				omsettotal = omset;
			}
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	// ----------- COMBO BOX ID SUPPLIER, ID PRODUCT ------------ //
	public void idsupplier() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelES.getRowCount();

		try {
			ArrayList<String> groupNames = new ArrayList<String>();
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT distinct id FROM SUPPLIER");

			while (rs.next()) {
				String isi = rs.getString(1);
				groupNames.add(isi);

			}
			stmt.close();
			DefaultComboBoxModel model = new DefaultComboBoxModel(
					groupNames.toArray());
			cbo_newproduct_supplier.setModel(model);
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	public void idproduct() {
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelEP.getRowCount();

		try {
			ArrayList<String> groupNames = new ArrayList<String>();
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT distinct id FROM PRODUCT");

			while (rs.next()) {
				String isi = rs.getString(1);
				groupNames.add(isi);

			}
			stmt.close();
			DefaultComboBoxModel model = new DefaultComboBoxModel(
					groupNames.toArray());
			cbo_newStock_id.setModel(model);
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	public void NamaProductDanSisaStock() {
		String nama = "";
		int sisastock = 0;
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int row = dataTabelEP.getRowCount();

		try {
			Statement stmt = koneksi.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT nama,stock FROM PRODUCT WHERE id='"
							+ cbo_newStock_id.getSelectedItem().toString()
							+ "'");

			while (rs.next()) {
				nama = rs.getString(1);
				sisastock = rs.getInt(2);
			}
			txt_newStock_nama.setText(nama);
			txt_newStock_sisa.setText("" + sisastock);
			stmt.close();
		}

		catch (SQLException x) {
			x.printStackTrace();
		}

	}

	public void stocktotal() {
		int stockawal;
		int stockpenambah;
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi = DriverManager.getConnection(Config.URL, Config.USERNAME,
					Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		int row = dataTabelEP.getRowCount();
		if (txt_newStock_tambah.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Data Masih Ada Yang Kosong !");
			txt_newStock_sisa.setText("");
			txt_newStock_tambah.setText("");
			txt_newStock_nama.setText("");
		} else {
			stockpenambah = Integer.parseInt(txt_newStock_tambah.getText());
			stockawal = Integer.parseInt(txt_newStock_sisa.getText());
			int stocktotal = stockawal + stockpenambah;
			try {
				Statement stmt = koneksi.createStatement();
				ResultSet rs = stmt.executeQuery("UPDATE PRODUCT SET stock = "
						+ stocktotal + " WHERE id='"
						+ cbo_newStock_id.getSelectedItem().toString() + "'");
				stmt.close();
				for (int i = tabelEP.getRowCount() - 1; i > -1; i--) {
					dataTabelEP.removeRow(i);
				}
				;
				isiTableEP();
				JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
				txt_newStock_sisa.setText("");
				txt_newStock_tambah.setText("");
				txt_newStock_nama.setText("");
			}

			catch (SQLException x) {
				x.printStackTrace();
			}
		}

	}

	public void namakasir() {
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
			ResultSet rs = stmt
					.executeQuery("SELECT distinct namakasir FROM PENJUALAN");

			while (rs.next()) {
				String isicbo = rs.getString(1);
				groupNames.add(isicbo);
			}
			stmt.close();
			DefaultComboBoxModel model = new DefaultComboBoxModel(
					groupNames.toArray());
			cboreport.setModel(model);
		}

		catch (SQLException x) {
			x.printStackTrace();
		}
	}

}
