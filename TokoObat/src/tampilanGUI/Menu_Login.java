package tampilanGUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu_Login extends JFrame {
	
	Connection koneksi; 
	
	JPanel panel_login = new JPanel();
	
	Container kontainer = getContentPane();
	 JLabel jl_user = new JLabel("Username : ");
	JLabel jl_pass = new JLabel("Password : ");
	public JTextField txt_user = new JTextField(20);
	JPasswordField txt_pass = new JPasswordField(20);
	JButton masuk = new JButton("Login");
	JButton batal = new JButton("Cancel");
	public  String user;
	
	
	public String getUser(){
		return user;
	}
	
	
	public Menu_Login(String title){
		super(title);
		super.setSize(430, 170);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txt_pass.setEchoChar('*');
		
		panel_login.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
	
		kontainer.setLayout(new FlowLayout());
		panel_login.setBackground(Color.CYAN);
		
		panel_login.setBorder(BorderFactory.createTitledBorder(""));
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel_login.add(jl_user, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel_login.add(txt_user, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel_login.add(jl_pass, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel_login.add(txt_pass, gbc);
		txt_pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cekLogin();
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		masuk.setPreferredSize(new Dimension(150, 25));
		panel_login.add(masuk, gbc);
		masuk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cekLogin();
			}

		});
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		batal.setPreferredSize(new Dimension(150, 25));
		panel_login.add(batal, gbc);
		batal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_user.setText("");
				txt_pass.setText("");
				//System.exit(0);
			}
		});
		kontainer.add(panel_login);
	}
		
	public static void main(String[] args) {
		Menu_Login ml = new Menu_Login("Login");
	}
	
	public void cekLogin(){
		 int id;
		 
		 String user = "";
		 String nama = "";
		 String pass="";
		 String akses = "";
		 
		try {
			Class.forName(Config.DATABASE_DRIVER).newInstance();
			koneksi=DriverManager.getConnection(Config.URL,Config.USERNAME,Config.PASSWORD);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		try{
			Statement stmt=koneksi.createStatement();
			@SuppressWarnings("deprecation")
			ResultSet rs=stmt.executeQuery("SELECT * FROM ADMIN where username = '" +txt_user.getText() + "' and password ='" + txt_pass.getText() + "'" );
			while(rs.next()){
				
				 user=rs.getString(1);
				 nama=rs.getString(2);
				 pass=rs.getString(3);
				 akses=rs.getString(4);
			}
			
			if ( (user.equals("")) && (pass.equals("")) ) {
				JOptionPane.showMessageDialog(null, "Username atau Password Salah", "Login Failed!", JOptionPane.ERROR_MESSAGE);
				txt_user.setText("");
				txt_pass.setText("");
				txt_user.grabFocus();
			}
			
			else{
				if( akses.equals("Administrator") ){
					this.setVisible(false);
					Menu_Admin ma = new Menu_Admin ("Dashboard Admin");
					ma.setVisible(true);
				}
				
				else if( akses.equals("Kasir")){
					this.setVisible(false);
					Menu_Kasir mk = new Menu_Kasir ("Dashboard Kasir");
					mk.setVisible(true);
					mk.nama = txt_user.getText();
					
				}
			}
			
			
			stmt.close();
		}catch(SQLException x){
			x.printStackTrace();
		}
			
	}

}
