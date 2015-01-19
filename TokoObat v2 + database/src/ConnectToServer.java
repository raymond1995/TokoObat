
import java.sql.*;

public class ConnectToServer {
	
	public static void main (String [] args) throws SQLException {
	String url = "jdbc:mckoi://localhost/";
	
	String username = "Raymond";
	String password = "5onyxperiamir0";
		
	//2. Buat Connection
	Connection conn;
	try{
		conn = DriverManager.getConnection(url, username, password);
		//3 Buat Statement
		Statement stmt = conn.createStatement();
		//4. Lakukan proses data query/update
		ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
		while(rs.next()){
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
		}
		
	}
	catch (SQLException e){
		System.out.println(
				"Unable to make a connection to the database.\n" +
				"The reason : " + e.getMessage());
		System.exit(1);
		return;
		}
	}
}
