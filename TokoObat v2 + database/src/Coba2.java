import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Coba2 extends JFrame {

	private JPanel panelCari = new JPanel();
	private JLabel lblCari = new JLabel("Username : ");
	private JTextField txtCari = new JTextField(15);
	private JButton btnCari = new JButton("Cari");
	private JButton btnEdit = new JButton("Edit");
	private JButton btnHps = new JButton("Hapus");
	private JPanel panelTable = new JPanel();
	public String username="";
	// private Object[] row = {"Hak Akses", "Nama", "Username"};
	private DefaultTableModel dataModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			if (column >= 4) {
				return true;
			} else {
				return false;
			}

		}
	};
	private JTable table = new JTable(dataModel);
	private JScrollPane scroll = new JScrollPane(table);

	private Connection koneksi;

	private int id;
	private String akses;
	private String nama;
	private String user;
	
	public Coba2(){}

	public Coba2(String title) {
		super(title);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(600, 500);
		super.setLocationRelativeTo(null);
		super.setResizable(false);

		Container kontainer = getContentPane();
		kontainer.setLayout(new FlowLayout());

		panelCari.setLayout(new GridBagLayout());
		panelTable.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panelCari.setBorder(BorderFactory.createTitledBorder("Cari Data"));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		panelCari.add(lblCari, c);

		c.gridx = 1;
		c.gridy = 0;
		panelCari.add(txtCari, c);

		c.gridx = 2;
		c.gridy = 0;
		panelCari.add(btnCari, c);

		scroll.setPreferredSize(new Dimension(500, 300));
		c.gridx = 0;
		c.gridy = 0;
		panelTable.add(scroll, c);

		dataModel.addColumn("ID");
		dataModel.addColumn("Hak Akses");
		dataModel.addColumn("Nama User");
		dataModel.addColumn("Username");
		dataModel.addColumn("Edit User");
		dataModel.addColumn("Hapus User");

		table.setModel(dataModel);
		table.getColumnModel().getColumn(4)
				.setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumnModel().getColumn(5)
				.setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(5)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scroll.setVisible(true);

		//isiTable();

		kontainer.add(panelCari);
		kontainer.add(panelTable);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coba2 edit = new Coba2("Manage User");
		edit.setVisible(true);

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
	
	class ButtonEditor extends DefaultCellEditor {
		protected JButton button;

		private String label;

		private boolean isPushed;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// fireEditingStopped();
					// System.out.println("andhika");
				}
			});
		}
}
}