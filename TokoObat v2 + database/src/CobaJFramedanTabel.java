import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class CobaJFramedanTabel extends JFrame {

	JMenuBar menuBar = new JMenuBar();

	JMenu Admin = new JMenu("Admin");

	JMenuItem miAdmin = new JMenuItem("Edit Admin");

	JPanel panelEditAdmin = new JPanel();
	JPanel panelTabel = new JPanel();

	Container kontainer = getContentPane();

	private DefaultTableModel dataModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			if (column >= 5) {
				return true;
			} else {
				return false;
			}

		}
	};

	public CobaJFramedanTabel(String title){
		super(title);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(600,500);
		super.setLocationRelativeTo(null);
		super.setResizable(true);
		
		panelEditAdmin.setVisible(false);
		panelTabel.setVisible(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		kontainer.setLayout(new FlowLayout());
		
		JTable table = new JTable(dataModel);
		JScrollPane scroll = new JScrollPane(table);
		
		panelEditAdmin.setLayout(new GridBagLayout());
		panelTabel.setLayout(new GridBagLayout());
		
		menuBar.add(Admin);
		Admin.add(miAdmin);
		
		super.setJMenuBar(menuBar);
		
		editAdmin();
		
		panelEditAdmin.setBorder(BorderFactory.createTitledBorder("Edit Admin"));
		JLabel jl_idadmin = new JLabel ("ID Admin");
		JTextField txt_idadmin = new JTextField(20);
		JButton btn_cari = new JButton ("Cari");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelEditAdmin.add(jl_idadmin, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelEditAdmin.add(txt_idadmin, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelEditAdmin.add(btn_cari, gbc);
		
		scroll.setPreferredSize(new Dimension(500, 300));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTabel.add(scroll, gbc);
		
		dataModel.addColumn("ID Product");
		dataModel.addColumn("Nama Product");
		dataModel.addColumn("ID Supplier");
		dataModel.addColumn("Harga");
		dataModel.addColumn("Stock");
		
		table.setModel(dataModel);
		table.getColumnModel().getColumn(3)
				.setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(3)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumnModel().getColumn(4)
				.setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(4)
				.setCellEditor(new ButtonEditor(new JCheckBox()));

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scroll.setVisible(true);
		
		kontainer.add(panelEditAdmin);
		kontainer.add(panelTabel);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CobaJFramedanTabel edit = new CobaJFramedanTabel("Manage User");
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
	public void editAdmin() {
		miAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelEditAdmin.setVisible(true);
				panelTabel.setVisible(true);
			}
		});
	}

}
